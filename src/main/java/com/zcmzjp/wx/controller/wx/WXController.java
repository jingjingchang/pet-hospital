package com.zcmzjp.wx.controller.wx;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.dto.*;
import com.zcmzjp.wx.entity.*;
import com.zcmzjp.wx.service.*;
import com.zcmzjp.wx.utils.DateUtils;
import com.zcmzjp.wx.utils.SendSMSUtil;
import com.zcmzjp.wx.utils.WeixinUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2017-07-25.
 *
 * 微信网页展示Cnotroller
 */
@RequestMapping("/wx")
@Controller
public class WXController {

    @Autowired
    MemberService memberService;

    @Autowired
    WXGraphicService wxGraphicService;

    @Autowired
    OrderService orderService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    OrderTimeService orderTimeService;

    @Autowired
    OrderStudentService orderStudentService;

    @Autowired
    PartTimeJobService partTimeJobService;

    @Autowired
    EvaluateService evaluateService;

    @Autowired
    PassService passService;

    @Autowired
    UnitService unitService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    ExaminationAnswerService examinationAnswerService;

    @Autowired
    BuildBuildingInfoService buildBuildingInfoService;

    @RequestMapping("/userInfo")
    public String userInfo(Model view){
        Member member =  memberService.getById(1);
        view.addAttribute(member);
        return "wx/userApplyForm";
    }

    @RequestMapping("/news/{id}")
    public String  news(Model view,@PathVariable Integer id){
        WxGraphic wxGraphic = wxGraphicService.getById(id);
        view.addAttribute("news",wxGraphic);
        return "wx/news";
    }

    @ResponseBody
    @RequestMapping("/news/addReadNum")
    public JsonResult addReadNum(Integer id){
        Integer num = wxGraphicService.addReadNum(id);
        return new JsonResult(true,num);
    }

    @RequestMapping("/test")
    public String test(Model view,Integer id, HttpServletRequest request){
        Student student = studentService.getStudentDtoById(1);
        List<PassDto> passDtoList = passService.getListByStuId(student.getId());
        view.addAttribute("student",student);
        view.addAttribute("passList",passDtoList);
        return "wx/personnalPage";
    }

    @ResponseBody
    @RequestMapping("/getOrderAndChildren")
    public List<OrderDto> getOrderAndChildren(){
        return orderService.getOrderAndChildren();
    }

    @ResponseBody
    @RequestMapping("/getStudentByPhone")
    public JsonResult getStudentByPhone(String phone){
        Boolean flag = false;
        Student stu = studentService.getStudentDtoByPhone(phone);
        if(stu!=null){
            flag=true;
        }
        return new JsonResult(flag);
    }

    @ResponseBody
    @RequestMapping("/addEvaluate")
    public JsonResult addEvaluate(Evaluate evaluate){
        boolean flag;
        if(evaluate.getId()==null){
            flag = evaluateService.add(evaluate);
        }else{
            flag = evaluateService.update(evaluate);
        }
        return new JsonResult(flag,flag?"评论成功！":"评论失败！");

    }

    @RequestMapping("/getTeacherList")
    public String getTeacherList(Model view){
        List<Teacher> teachers = teacherService.getAllTeachers();
        view.addAttribute("teachers",teachers);
        return "wx/teacherList";
    }

    @RequestMapping("/getDetailPage/{id}")
    public String getDetailPage(Model view,HttpServletRequest request,@PathVariable Integer id){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        Teacher t = teacherService.getById(id);
        List<EvaluateDto> evaluates = evaluateService.getEvaluateByTeaId(id);
        view.addAttribute("teacher",t);
        view.addAttribute("evaluates",evaluates);
        if(member==null){
            return "wx/teacherEvaluateN";
        }else if(member.getMobile()!=null){
            Student student = studentService.getStudentDtoByPhone(member.getMobile());
            if(student==null){
                return "wx/teacherEvaluateN";
            }else{
                Evaluate myEvaluate = evaluateService.getEvaluateByStuIdAndTeaId(student.getId(),id);
                view.addAttribute("member",member);
                view.addAttribute("student",student);
                view.addAttribute("myEvaluate",myEvaluate);
                return "wx/teacherEvaluate";
            }
        }else{
            return "wx/teacherEvaluateN";
        }
    }

    @RequestMapping("/getAllTeachers/{id}")
    public String getAllTeachers(Model view,@PathVariable Integer id){
        List<Teacher> teachers = teacherService.getAllTeachers();
        view.addAttribute("odId",id);
        view.addAttribute("teachers",teachers);
        return "wx/teachers";
    }

    //堆楼活动中奖，进入填写中奖信息页面
    @RequestMapping("/getLuckInfoPage")
    public String getOrderPage(Model view,HttpServletRequest request,HttpServletResponse response,String wxopenid,String buildId){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        if(member==null){
            try {
                session.setAttribute("url","/wx/getLuckInfoPage?wxopenid="+wxopenid+"&buildId="+buildId);
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+GlobalParameter.AppID+"&redirect_uri="+GlobalParameter.sessionURL+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{

            if(member.getWxopenid().equals(wxopenid)){
                view.addAttribute("member",member);
                return "wx/luckInfoForm";
            }else{
                //非中奖用户无法填写
                return "wx/notLuckMember";
            }
        }

    }
    @ResponseBody
    @RequestMapping("/sendToNoOrderStudent")
    public JsonResult sendToNoOrderStudent(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        int num = 0;
        OrderStudent orderStudent;
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher==null){
            return new JsonResult(false,"推送成功！");
        }else{
            try{
                System.out.println("执行推送任务："+ new Date(System.currentTimeMillis()));
                List<OrderStudentDto> orderStudentDtos = orderStudentService.getOrderStudentBySmsStatus(null,teacher.getId());
                for(OrderStudentDto o:orderStudentDtos) {
                    WeixinUtil.sendMessage(o.getWxOpenId(),"text","【征诚驾培】提醒您\n截止："+ DateUtils.getNow()+"\n尊贵的征诚驾培学员"+o.getStuName()+"您好:\n师傅根据对您的教学安排，今天没有受理您的预约练车，请复习学习过的知识，欢迎下次预约！\n");
                    orderStudent = new OrderStudent();
                    orderStudent.setId(o.getId());
                    orderStudent.setSms(2);
                    orderStudentService.updatePart(orderStudent);
                    num = num+1;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return new JsonResult(true,"推送成功:"+num+"条消息");
        }
    }

    @ResponseBody
    @RequestMapping("/addOrderStudent/create")
    public JsonResult addOrderStudent(OrderStudent obj){
        String subType = "";
        Boolean flag =false;

        OrderStudentDto orderStudentDto = orderStudentService.getOrderStudentByStuId(obj.getStuId());
        Student student = studentService.getById(obj.getStuId());
        if(student.getStudyStatus()==2){
            subType="科目二";
        }else if(student.getStudyStatus()==3){
            subType="科目三";
        }

        if(orderStudentDto!=null){
            return  new JsonResult(flag,"您已预约了"+orderStudentDto.getTeachName()+" 师傅，明天："+orderStudentDto.getoTime()+"的"+subType+"教学，确定重新提交预约吗？","",orderStudentDto.getId().toString());
        }else{
            obj.setSubType(student.getStudyStatus());
            obj.setSms(0);
            flag = orderStudentService.add(obj);
            String str = flag?"操作成功":"操作失败--";
            return  new JsonResult(flag,str);
        }
    }

    @ResponseBody
    @RequestMapping("/deleteOrderStudent/delete")
    public JsonResult deleteOrderStudent(Integer id){
            boolean flag = orderStudentService.deleteById(id);
            String str = flag?"操作成功":"操作失败";
            return  new JsonResult(flag,str);
    }

    @RequestMapping("/getPartTimeJobList")
    public String getPartTimeJobList(Model view){
        List<PartTimeJob> list = partTimeJobService.getPartTimeJobList();
        view.addAttribute("jobs",list);
        return "wx/partTimeJobList";

    }

    @RequestMapping("/getPartTimeJobDetail/{id}")
    public String getPartTimeJobDetail(Model view,@PathVariable Integer id){
        PartTimeJob partTimeJob = partTimeJobService.getById(id);
        view.addAttribute("job",partTimeJob);
        TeacherDto st = new TeacherDto();
        return "wx/partTimeJobDetail";
    }

    @RequestMapping("/orderStudent/list")
    public String getOrderStudentsPage(Model view,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null){
            List<Unit> units = unitService.getAllUnit();
            List<OrderDetailDto> orderDetails = orderDetailService.getOrderDetailList();
            view.addAttribute("units",units);
            view.addAttribute("orderDetails",orderDetails);
            return "orderStudentList";
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @RequestMapping("/getStudentListPage")
    public String getStudentListPage(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null){
            return "studentListPage";
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/student/listByPage")
    public Object listByPage(HttpServletRequest request, HttpServletResponse response,Page page){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null){
            Map<String, Object> map = new HashMap();
            map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
            map.put("pageSize", Integer.valueOf(page.getLimit()));
            map.put("keyword", page.getSearch());
            PageInfo info = studentService.listByPage(map);

            Map<String, Object> jsonMap = new HashMap();
            jsonMap.put("rows", info.getList());
            jsonMap.put("total", Long.valueOf(info.getTotal()));
            return jsonMap;
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @RequestMapping("/studentEditPage/{id}")
    public String getStudentListPage(Model view,HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null){
            List<Unit> list = unitService.getAllUnit();
            Student student = studentService.getStudentDtoById(id);
            view.addAttribute("units",list);
            view.addAttribute("student",student);
            return "studentEditPage";
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @RequestMapping({"/orderStudent/newListByPage"})
    @ResponseBody
    public Object listPage(Page page,String dateTime,HttpServletRequest request, HttpServletResponse response,String subType,String unitId,String orderDetail)
    {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null){
            Map<String, Object> map = new HashMap();
            map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
            map.put("pageSize", Integer.valueOf(page.getLimit()));
            map.put("keyword", page.getSearch());
            map.put("dateTime", dateTime);
            map.put("subType",subType);
            map.put("unitId",unitId);
            map.put("orderDetail",orderDetail);
            map.put("teacherId",teacher.getId());
            PageInfo info = orderStudentService.listByPage(map);

            Map<String, Object> jsonMap = new HashMap();
            jsonMap.put("rows", info.getList());
            jsonMap.put("total", Long.valueOf(info.getTotal()));
            return jsonMap;
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/pass/getPassListByStuId")
    public List<PassDto> getPassListByStuId(Integer id,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null){
            List<PassDto> passList = passService.getListByStuId(id);
            return passList;
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public Message getMessage(boolean status)
    {
        Message msg = new Message();
        msg.setStatus(status);
        if (status) {
            msg.setMessage("success");
        } else {
            msg.setMessage("error");
        }
        return msg;
    }

    @PostMapping({"/pass/create"})
    @ResponseBody
    public Message create(Pass obj,HttpServletRequest request, HttpServletResponse response) throws Exception{

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null) {
            Student student = new Student();
            student.setId(obj.getStuId());
            if (obj.getIsPass()==1) {
                if(obj.getSubType()==4){
                    student.setStudyStatus(5);
                }else{
                    student.setStudyStatus(obj.getSubType()+1);
                }
            }else{
                student.setStudyStatus(obj.getSubType());
            }
            studentService.updatePart(student);
            boolean ret = passService.add(obj);
            return getMessage(ret);
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @PostMapping({"/pass/remove"})
    @ResponseBody
    public Message remove(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null) {
            boolean ret = passService.deleteById(id);
            return getMessage(ret);
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @ResponseBody
    @RequestMapping("/teacher/getCurrentTeacherInfo")
    public JsonResult getCurrentTeacherInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher!=null) {
            return new JsonResult(true,teacher);
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    @ResponseBody
    @RequestMapping("/sendSMS")
    public JsonResult sendSMS(Integer id,String pickupTime,String finalPickup,String finalTime,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        finalPickup = (finalPickup==null||finalPickup=="")?"指定":finalPickup;
        if(teacher!=null) {
            boolean flag = false;
            OrderStudentDto o = orderStudentService.getOrderStudentByStuId(id);
            if(o==null){
                flag = false;
                return new JsonResult(flag,"对不起，只能只能给明天学车的学员发送短信！");
            }
            try {
                SendSmsResponse smsResponse = SendSMSUtil.sendOrderSms(o.getStuName(),o.getSmobile(),o.getTeachName(),o.getTmobile(),finalTime,pickupTime,finalPickup);
                if (smsResponse.getCode().equals("OK")){
                    o.setSms(1);
                    o.setFinalPickup(finalPickup);
                    o.setFinalTime(finalTime);
                    o.setPickupTime(pickupTime);
                    flag = true;
                    orderStudentService.update(o);
                }
                WeixinUtil.sendMessage(o.getWxOpenId(),"text","【征诚驾培】提醒您\n学员："+o.getStuName()+"\n您已成功预约\n师傅："+o.getTeachName()+"，电话："+o.getTmobile()+"\n明天："+finalTime+"的学车服务\n请在："+pickupTime+"前达到"+finalPickup+"接送点");
            } catch (ClientException e) {
                e.printStackTrace();
            }
            return new JsonResult(flag,flag?"短信发送成功！":"短信发送失败！");
        }else{
            try{
                response.sendRedirect( "/teacherLoginPage");
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    //添加问卷答案信息
    @ResponseBody
    @RequestMapping("/addExaminationAnswer")
    public JsonResult addExaminationAnswer(String answerList,Integer studentId){
        List<ExaminationAnswer> array = JSON.parseArray(answerList,ExaminationAnswer.class);
        ExaminationAnswer answer = examinationAnswerService.getByLatestOrderStuId(studentId);
        if(answer!=null){
            return new JsonResult(false,"您已提交答案请勿重复提交！");
        }else{
            Boolean flag = false;
            for(ExaminationAnswer ea:array) {
                flag = examinationAnswerService.add(ea);
            }
            return new JsonResult(flag,flag?"提交成功！":"提交失败，请重试！");
        }
    }


}
