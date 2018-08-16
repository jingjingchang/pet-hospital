package com.zcmzjp.wx.controller.wx;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.zcmzjp.wx.dto.*;
import com.zcmzjp.wx.entity.*;
import com.zcmzjp.wx.service.*;
import com.zcmzjp.wx.utils.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Chris on 2017-08-10.
 */
@Controller
@RequestMapping("/weChat/wxEvent")
public class WXEventController {
    private static Logger logger = Logger.getLogger(WXEventController.class);
    String AppID = GlobalParameter.AppID;
    String AppSecret = GlobalParameter.AppSecret;

    @Autowired
    MemberService memberService;

    @Autowired
    WxReplyService wxReplyService;

    @Autowired
    WXGraphicService wxGraphicService;

    @Autowired
    ApplyService applyService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderTimeService orderTimeService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    EvaluateService evaluateService;

    @Autowired
    PartTimeJobApplyService partTimeJobApplyService;

    @Autowired
    PassService passService;

    @Autowired
    WxConfigService wxConfigService;

    @Autowired
    OrderStudentService orderStudentService;

    @Autowired
    UnitTeacherService unitTeacherService;

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    ExaminationAnswerService examinationAnswerService;

    @Autowired
    ExaminationPaperService examinationPaperService;

    @Autowired
    ExaminationQuestionService examinationQuestionService;

    //微信配置拦截地址验证
    @RequestMapping(value = "/getEvent",method = RequestMethod.GET)
    public void getEvent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        String Token = "zcmzjp";
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        /*String[] str = { Token, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();

        // 确认请求来至微信
        if (digest.equals(signature)) {
            response.getWriter().print(echostr);
        }*/
        response.getWriter().print(echostr);
    }

    //向session注入用户信息
    @RequestMapping("/userReg")
    public String userReg(Model view, String code, Integer state, HttpServletRequest request){
        String apiUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AppID+"&secret="+ AppSecret+"&code="+code+"&grant_type=authorization_code";
        //发送httpget请求
        HttpGet req = new HttpGet(apiUrl);
        String result = "";
        String openid ;
        Member member ;
        Member mber;
        try {
            HttpResponse response = HttpClients.createDefault().execute(req);
            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        JSONObject json = JSONObject.fromObject(result);
        openid = json.getString("openid");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        member = memberService.getMemberByOpenId(openid);
        HttpSession session = request.getSession();
        String url = (String)session.getAttribute("url");
        if(member==null){
            logger.info("======================member为空");
            Member mem = new Member();
            mem.setWxopenid(openid);
            mem.setAuthFlag(false);
            mem.setCreated(sdf.format(date));
            mem.setStatus(0);
            memberService.add(mem);
            mber = memberService.getMemberByOpenId(openid);
            session.setAttribute("member",mber);
        }else{
            session.setAttribute("member",member);
        }
        logger.info("跳转至:"+url);
        return "redirect:"+url;
    }

    //用户信息完善
    @ResponseBody
    @RequestMapping("/addMemberInfo")
    public JsonResult memberInfo(Member member, HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        boolean ret = false;
        String result = null;
        if(member!=null){
            Member mem = memberService.getById(member.getId());
            member.setWxopenid(mem.getWxopenid());
            member.setCreated(mem.getCreated());
            member.setAuthFlag(true);
            member.setStatus(0);
            ret = memberService.update(member);

            Student student = studentService.getStudentDtoByPhone(member.getMobile());
            if(student!=null){
                student.setmId(member.getId());
                studentService.update(student);
            }
            HttpSession session = request.getSession();
            session.setAttribute("member",member);
        }
        if (ret){
            result = "操作成功";
        }else{
            result="操作失败";
        }
        return new JsonResult(ret,result);
    }

    //学员报名
    @ResponseBody
    @RequestMapping("/addApply")
    public JsonResult addApply(Member mem, HttpServletRequest request, String applyAsk){
        Apply apply = new Apply();
        boolean flag;
        boolean smsFlag = false;
        String str = null;

        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("member");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        if(member==null||mem.getId()==null){
            return new JsonResult(false,"参数错误，请刷新重试！");
        }
        if(member.getMobile()==null||member.getGender()==null||member.getName()==null||member.getEmail()==null){
            memberService.update(mem);
            apply.setName(mem.getName());
            apply.setmId(mem.getId());
            apply.setAddress(mem.getAddress());
            apply.setEmail(mem.getEmail());
            apply.setApplyDate(sdf.format(date));
            apply.setGender(mem.getGender());
            apply.setMobile(mem.getMobile().toString());
            apply.setApplyAsk(applyAsk);
            apply.setStatus(1);
            flag = applyService.add(apply);
        }else{
            apply.setName(mem.getName());
            apply.setmId(mem.getId());
            apply.setEmail(mem.getEmail());
            apply.setAddress(mem.getAddress());
            apply.setApplyDate(sdf.format(date));
            apply.setGender(mem.getGender());
            apply.setMobile(mem.getMobile().toString());
            apply.setApplyAsk(applyAsk);
            apply.setStatus(1);
            flag= applyService.add(apply);
        }
        try {
            SendSmsResponse response = SendSMSUtil.sendApplySms("郭鑫",GlobalParameter.bossMobile,mem.getName(),mem.getGender()==1?"男":"女",mem.getMobile(),mem.getAddress(),"微信");
            if (response.getCode().equals("OK")){
                smsFlag=true;
            }else{
                smsFlag=false;
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(flag && smsFlag){
            str = "报名成功，请保持电话畅通，以便于我们联系您！";
        }else if(flag){
            str = "报名成功，但通知短信发送失败，通知时间可能会稍晚，请保持电话畅通，以便于我们联系您！";
        }

        return new JsonResult(flag,flag?str:"系统错误，请重试，或直接拨打："+GlobalParameter.bossMobile+"联系负责人报名！");
    }

    //添加兼职报名信息
    @ResponseBody
    @RequestMapping("/addJobApply")
    public JsonResult addJobApply(PartTimeJobApply apply){
        PartTimeJobApply jobApply = partTimeJobApplyService.getJobApplyByJobAndMId(apply.getmId(),apply.getjId());
        if(jobApply!=null){
            return new JsonResult(false,"您已报名此兼职请勿重复报名！");
        }else{
            Boolean flag = partTimeJobApplyService.add(apply);
            return new JsonResult(flag,flag?"兼职报名成功，请等待通知！":"报名失败，请重试！");
        }
    }



    //预约页面跳转
    @RequestMapping("/getOrderPage")
    public String getOrderPage(Model view, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        Student student;
        String str;
        if(member==null){
            try {
                session.setAttribute("url","/weChat/wxEvent/getOrderPage");
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppID+"&redirect_uri="+GlobalParameter.sessionURL+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else if(member.getMobile()!=null){
            student = studentService.getStudentDtoByPhone(member.getMobile());
            if(student==null){
                view.addAttribute("member",member);
                str = "wx/notStudent";
            }else if(student.getStudyStatus()!=null){
                SysConfig sysConfig = sysConfigService.getByCode("IsOrderExam");
                if(sysConfig!=null&&sysConfig.getStatus()==1){
                    ExaminationAnswer examinationAnswer = examinationAnswerService.getByLatestOrderStuId(student.getId());
                    if(examinationAnswer==null){
                        ExaminationPaper examinationPaper = examinationPaperService.getByOpenStatus();
                        OrderStudent orderStudent = orderStudentService.getLatestOrderByStudentId(student.getId());
                        List<ExaminationQuestion> list = examinationQuestionService.getPaperQuestionsByPaperId(examinationPaper.getId());
                        view.addAttribute("questions",list);
                        view.addAttribute("examinationPaper",examinationPaper);
                        view.addAttribute("orderStudent",orderStudent);
                        System.out.println("进入评教页面");
                        return "wx/stuexam";
                    }
                }

                if(student.getStudyStatus().equals(2)||student.getStudyStatus().equals(3)){
                    List<OrderDto> list;
                    if(student.getUnitId()!=null){
                        list = orderService.getOrderAndChildrenByUnitId(student.getUnitId());
                    }else{
                        list = orderService.getOrderAndChildren();
                    }

                    OrderTime orderTime = orderTimeService.getById(1);
                    view.addAttribute("orderTime",orderTime);
                    view.addAttribute("orderDto",list);
                    str = "wx/orderPage";
                }else{
                    str = "wx/notRightStudyStatus";
                }
            }else{
                str = "wx/notRightStudyStatus";
            }
        }else if(member.getMobile()==null){
            view.addAttribute("member",member);
            view.addAttribute("url","/weChat/wxEvent/getOrderPage");
            return "wx/memberInfoForm";
        }else{
            view.addAttribute("member",member);
            str = "wx/notStudent";
        }
        return str;
    }

    //获取教练列表
    @RequestMapping("/getAllTeachers/{id}")
    public String getAllTeachers(Model view,@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){
        List<Teacher> teachers = teacherService.getAllTeachers();
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        if(member==null){
            try {
                session.setAttribute("url","/weChat/wxEvent/getAllTeachers/"+id);
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppID+"&redirect_uri="+GlobalParameter.sessionURL+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            Student student = studentService.getStudentDtoByPhone(member.getMobile());
            if (student.getUnitId()!=null){
                List<UnitTeacherDto> teacherDtos = unitTeacherService.getTeacherListByUnitId(student.getUnitId());
                view.addAttribute("teachers",teacherDtos);
            }else{
                view.addAttribute("teachers",teachers);
            }
            view.addAttribute("odId",id);
            view.addAttribute("student",student);
            return "wx/teachers";
        }
    }

    //用户报名学车页面跳转
    @RequestMapping("/getApplyPage")
    public String userApply(Model view, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        String ret;
        if(member==null){
            try {
                session.setAttribute("url","/weChat/wxEvent/getApplyPage");
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppID+"&redirect_uri="+GlobalParameter.sessionURL+"&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("==========member为空");
            ret = null;
        }else{
            view.addAttribute("member",member);
            ret = "wx/userApplyForm";
        }
        return ret;

    }

    //兼职报名页面跳转
    @RequestMapping("/getJobApplyPage/{jId}")
    public String getJobApplyPage(Model view, HttpServletRequest request, HttpServletResponse response,@PathVariable Integer jId){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        String ret;
        if(member==null){
            try {
                session.setAttribute("url","/weChat/wxEvent/getJobApplyPage/"+jId);
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppID+"&redirect_uri="+GlobalParameter.sessionURL+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ret = null;
        }else if(member!=null&&member.getMobile()==null){
            view.addAttribute("member",member);
            ret = "wx/memberInfoForm";
        }else{
            view.addAttribute("member",member);
            view.addAttribute("jId",jId);
            ret = "wx/jobApplyPage";
        }
        return ret;
    }
    //评论页面跳转
    @RequestMapping("/getEvaluatePage/{id}")
    public String getEvaluatePage(Model view, HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        Teacher teacher = teacherService.getById(id);
        List<EvaluateDto> evaluates = evaluateService.getEvaluateByTeaId(id);
        view.addAttribute("evaluates",evaluates);
        Student student;
        String str;
        if(member==null){
            try {
                session.setAttribute("url","/weChat/wxEvent/getEvaluatePage/"+id);
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppID+"&redirect_uri="+GlobalParameter.sessionURL+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else if(member.getMobile()!=null){
            student = studentService.getStudentDtoByPhone(member.getMobile());
            if(student==null){
                view.addAttribute("member",member);
                str = "wx/notStudent";
            }else{
                Evaluate myEvaluate = evaluateService.getEvaluateByStuIdAndTeaId(student.getId(),teacher.getId());
                view.addAttribute("member",member);
                view.addAttribute("teacher",teacher);
                view.addAttribute("student",student);
                view.addAttribute("myEvaluate",myEvaluate);
                str = "wx/teacherEvaluate";
            }
        }else if(member.getMobile()==null){
            view.addAttribute("member",member);
            view.addAttribute("url","/weChat/wxEvent/getEvaluatePage");
            return "wx/memberInfoForm";
        }else{
            view.addAttribute("member",member);
            str = "wx/notStudent";
        }
        return str;
    }

    @RequestMapping("/getPersonnelPage")
    public String getPersonnelPage(Model view, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        String ret = null;
        if(member==null){
            System.out.println("========================================");
            try {
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppID+"&redirect_uri="+GlobalParameter.sessionURL+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
                session.setAttribute("url","/weChat/wxEvent/getPersonnelPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ret = null;
        }else if(member.getMobile()!=null){
            StudentDto studentDto = studentService.getStudentDtoByPhone(member.getMobile());
            if(studentDto==null){
                view.addAttribute("member",member);
                ret = "wx/notStudent";
            }else{
                List<PassDto> passDtoList = passService.getListByStuId(studentDto.getId());
                List<OrderStudentDto> orderStudentDtos = orderStudentService.getOrderListByStudentId(studentDto.getId());
                view.addAttribute("student",studentDto);
                view.addAttribute("passList",passDtoList);
                view.addAttribute("orderList",orderStudentDtos);
                ret = "wx/personnelPage";
            }
        }else{
            view.addAttribute("member",member);
            ret = "wx/memberInfoForm";
        }
        return ret;
    }


    @RequestMapping("/reg")
    public String userRegister(Model view, String code, Integer state, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        String ret = null;
        if(member==null){
            System.out.println("========================================");
            try {
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppID+"&redirect_uri="+GlobalParameter.sessionURL+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
                session.setAttribute("url","/weChat/wxEvent/reg?state=1");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ret = null;
        }else{
            view.addAttribute("member",member);
            ret = "wx/memberInfoForm";
        }
        return ret;
    }

    //微信推送事件处理
    @RequestMapping(value = "/getEvent",method = RequestMethod.POST)
    public void postEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        Map<String, String> map = null;
        try {
            map = MessageUtil.xmlToMap(request);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String tmpDate = sdf.format(date);
        Integer cId = null;
        Member member = memberService.getMemberByOpenId(fromUserName);
        //如果推送的是事件
        if(msgType.equals(GlobalParameter.WX_MSGTYPE_EVENT)) {
            String Event = map.get("Event");
            //获取二维码中自定义的会议ID关键字
            //String EventKey = map.get("EventKey");

            //如果是关注事件
            if (Event.equals(GlobalParameter.WX_EVENT_SUBSCRIBE)) {
                if (member == null) {
                    Member mem = new Member();
                    mem.setWxopenid(fromUserName);
                    mem.setStatus(0);
                    mem.setCreated(tmpDate);
                    mem.setAuthFlag(false);
                    memberService.add(mem);
                 } else {
                    //更新用户再次关注时的信息
                    member.setStatus(0);
                    member.setCancelDate(null);
                    member.setCreated(tmpDate);
                    memberService.update(member);
                }
                WxConfig wxConfig = wxConfigService.getById(1);
                if(wxConfig.getPush()){
                    WxGraphic wxGraphic = wxGraphicService.getById(wxConfig.getGraphicId());
                    List<JSONObject> joList = new ArrayList<>();
                    JSONObject jo = new JSONObject();
                    jo.put("title",wxGraphic.getTitle());
                    jo.put("description",wxGraphic.getBrief());
                    if(wxGraphic.getUrl()!=null&&wxGraphic.getUrl()!=""){
                        jo.put("url","http://"+wxGraphic.getUrl());
                    }else{
                        jo.put("url",GlobalParameter.HOST_ADDRESS+"/wx/news/"+wxGraphic.getId());
                    }
                    jo.put("picurl",GlobalParameter.HOST_ADDRESS+wxGraphic.getImgurl());
                    joList.add(jo);
                    WeixinUtil.sendNewsMessage(fromUserName,joList);
                }else{
                    WeixinUtil.sendMessage(fromUserName, GlobalParameter.WX_REPLY_TYPE_TEXT, wxConfig.getReplyWord());
                }
            }
            //取消关注事件
            else if(Event.equals(GlobalParameter.WX_EVENT_UNSUBSCRIBE)){
                if(member!=null){
                    member.setStatus(1);
                    member.setCancelDate(tmpDate);
                    memberService.update(member);
                    return;
                }else {
                    return;
                }

            }
            //菜单点击事件
            else if(Event.equals(GlobalParameter.WX_EVENT_CLICK)){
                WeixinUtil.sendMessage(fromUserName,GlobalParameter.WX_REPLY_TYPE_TEXT,"对不起，暂未提供点击事件功能！");
            }
            //微信扫码事件
            else if(Event.equals(GlobalParameter.WX_EVENT_SCAN)){
                //获取二维码中关键字Integer.valueOf(EventKey)
                if(member==null){
                    Member mem = new Member();
                    mem.setWxopenid(fromUserName);
                    mem.setStatus(0);
                    mem.setCreated(tmpDate);
                    mem.setAuthFlag(false);
                    memberService.add(mem);
                    WeixinUtil.sendMessage(fromUserName, GlobalParameter.WX_REPLY_TYPE_TEXT, "欢迎关注征诚驾培!\n 我们将竭诚为您提供更优质的服务！");
                    return;
                    }
                }
        }else if(msgType.equals(GlobalParameter.WX_MSGTYPE_TEXT)){
            WxReply wxReply = wxReplyService.getReplyByKeyName(content);
            if(wxReply!=null){
                if(wxReply.getKeyType().equals("text")){
                    WeixinUtil.sendMessage(fromUserName,GlobalParameter.WX_REPLY_TYPE_TEXT,wxReply.getKeyValue());
                }else if(wxReply.getKeyType().equals("graphics")){
                    WxGraphic wxGraphic = wxGraphicService.getById(Integer.valueOf(wxReply.getGraphics()));
                    List<JSONObject> joList = new ArrayList<>();
                    JSONObject jo = new JSONObject();
                    jo.put("title",wxGraphic.getTitle());
                    jo.put("description",wxGraphic.getBrief());
                    if(wxGraphic.getUrl()!=null&&wxGraphic.getUrl()!=""){
                        jo.put("url",wxGraphic.getUrl());
                    }else{
                        jo.put("url",GlobalParameter.HOST_ADDRESS+"/wx/news/"+wxGraphic.getId());
                    }

                    jo.put("picurl",GlobalParameter.HOST_ADDRESS+wxGraphic.getImgurl());
                    joList.add(jo);
                    WeixinUtil.sendNewsMessage(fromUserName,joList);
                }
            }else{
                String str =null;
                str = TulingApiUtil.getTulingResult(content);
                if(str==null){
                    WeixinUtil.sendMessage(fromUserName,GlobalParameter.WX_REPLY_TYPE_TEXT,"对不起，您说的话太高深了，俺暂时无法理解呢");
                }else{
                    WeixinUtil.sendMessage(fromUserName,GlobalParameter.WX_REPLY_TYPE_TEXT,str);
                }
            }
        }else{
            WeixinUtil.sendMessage(fromUserName,GlobalParameter.WX_REPLY_TYPE_TEXT,"对不起，此功能暂未开放");
        }
    }
}