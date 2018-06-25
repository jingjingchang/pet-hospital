package com.zcmzjp.wx.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcmzjp.wx.entity.GlobalParameter;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-05 20:58
 * Description:
 */
public class SendSMSUtil {


    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = GlobalParameter.AccessKey;
    static final String accessKeySecret = GlobalParameter.AccessKeySecret;


    /**
     * 测试方法
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse sendSms() throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers("18080332897");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("征诚驾培");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_126356603");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //亲爱的征诚驾培${tname}师傅您好: 学员：${sname},电话：${smobile} 预约了${otime}的学车服务，请您注意安排学车。
        request.setTemplateParam("{\"tname\":\"周红星\", \"sname\":\"张静\",\"smobile\":\"13258179872\",\"otime\":\"6:00-7:00\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    //报名

    /**
     *
     * @param rname 驾校负责人名称
     * @param rmobile 负责人电话
     * @param sname 学员名称
     * @param gender 学员性别
     * @param smobile 学员电话
     * @param type 报名类型
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse sendApplySms(String rname,String rmobile,String sname,String gender,String smobile,String area,String type) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(rmobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("征诚驾培");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_127163083");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //亲爱的的征诚驾培负责人${tname}您好：用户${sname}，性别：${gender},电话：${smobile}，所属区域：${area},在${type}报名了学车服务，请您及时与用户联系。
//        ObjectMapper jsonMapper = new ObjectMapper();
//        jsonMapper.writeValueAsString()
        request.setTemplateParam("{\"tname\":'"+rname+"', \"sname\":'"+sname+"',\"gender\":'"+gender+"',\"smobile\":'"+smobile+"',\"area\":'"+area+"',\"type\":'"+type+"'}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + sendSmsResponse.getCode());
        System.out.println("Message=" + sendSmsResponse.getMessage());
        System.out.println("RequestId=" + sendSmsResponse.getRequestId());
        System.out.println("BizId=" + sendSmsResponse.getBizId());

        return sendSmsResponse;
    }


    //预约成功通知

    /**
     *
     * @param sname
     * @param smobile
     * @param tname
     * @param tmobile
     * @param otime
     * @param atime
     * @param pickup
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse sendOrderSms(String sname,String smobile,String tname,String tmobile,String otime,String atime,String pickup) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(smobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("征诚驾培");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_127152358");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //亲爱的征诚驾培学员${sname}: 您已成功预约了${tname}师傅${otime}的学车服务，请您在${atime}前到达接送点，征诚驾培祝您学车愉快
        request.setTemplateParam("{\"sname\":'"+sname+"',\"tname\":'"+tname+"',\"tmobile\":'"+tmobile+"', \"otime\":'"+otime+"', \"atime\":'"+atime+"', \"pickup\":'"+pickup+"'}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + sendSmsResponse.getCode());
        System.out.println("Message=" + sendSmsResponse.getMessage());
        System.out.println("RequestId=" + sendSmsResponse.getRequestId());
        System.out.println("BizId=" + sendSmsResponse.getBizId());

        return sendSmsResponse;
    }
}
