package com.zcmzjp.wx.utils;

import com.zcmzjp.wx.entity.AccessToken;
import com.zcmzjp.wx.entity.GlobalParameter;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.List;

/**
 * Created by Chris on 2017-07-13.
 */
public class WeixinUtil {
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //测试access_token是否失效链接
    public static String test_token_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    /**
     * 描述:  发起https请求并获取结果
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)){
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }



    /**
     5. * 获取 access_token
     6. *
     7. * @param appid 凭证
     8. * @param appsecret 密钥
     9. * @return
     10. */
    public static   AccessToken getAccessToken() {
        AccessToken accessToken = new AccessToken();
        String token = null;
        if(EhcacheUtil.get("accessToken")!=null){
            token = EhcacheUtil.get("accessToken").toString();
        }
        if(token!=null){
            String testUrl = test_token_url.replace("ACCESS_TOKEN", token);
            JSONObject testJo = httpRequest(testUrl, "GET", null);
            if(null != testJo){
                System.out.println(testJo.getString("errcode"));
                try {
                    if(testJo.getString("errcode").equals("40001")||testJo.getString("errcode").equals("42001")||testJo.getString("errcode").equals("40014")){
                        System.out.println(getNewAccessToken());
                        accessToken.setToken(getNewAccessToken());
                    }else {
                        accessToken.setToken(token);
                    }
                } catch (JSONException e) {
                    accessToken = null;
                    // 获取 token 失败
                    log.error("获取 token 失 败 errcode:{} errmsg:{}", testJo.getInt("errcode"), testJo.getString("errmsg"));
                }
            }
        }else{
            accessToken.setToken(getNewAccessToken());
        }
         return accessToken;
    }

    private static String getNewAccessToken(){
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", GlobalParameter.AppID).replace("APPSECRET", GlobalParameter.AppSecret);
        JSONObject jsonObject =httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));

                EhcacheUtil.put("accessToken",jsonObject.getString("access_token"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取 token 失败
                log.error("获取 token 失 败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken.getToken();
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @parama ccessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(String  menu) {
        String accessToken = getAccessToken().getToken();
        int result = 0;
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", menu);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error(menu);
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    /**
     *自定义发送内容
     * @param
     * @param jsonParam
     * @return
     */
    public static String sendMessage(JSONObject jsonParam){
        String token = getAccessToken().getToken();
        String result = null;
        try {
            if (jsonParam != null) {
                System.out.println("token = " + token);
                //获取ticket
                String sendMessUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
                result = String.format(sendMessUrl, token);

                HttpClient httpClient = new DefaultHttpClient();
                log.info(jsonParam.toString());
                System.out.println("start to send :"+jsonParam);

                StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");

                // 创建Get方法实例
                HttpPost me = new HttpPost(result);
                me.setEntity(entity);
                HttpResponse hr = httpClient.execute(me);
                String data = EntityUtils.toString(hr.getEntity());
                me.releaseConnection();

                JSONObject userInfo = JSONObject.fromObject(data);
                System.out.println("get backMessage ..." + data);

            }
        } catch (Exception ex) {
            System.out.println("error");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * 发送文本，图片，语音消息接口
     * @param touser
     * @param msgtype
     * @param text
     * @return
     */
    public static boolean sendMessage(String touser,String msgtype,String text){
        String token = getAccessToken().getToken();
        JSONObject jsonParam = new JSONObject();
        boolean flag = false;
        jsonParam.put("touser",touser);
        jsonParam.put("msgtype",msgtype);
        JSONObject type = new JSONObject();

        if(msgtype.equals("text")){
            type.put("content",text);
        }else if(msgtype.equals("image")){
            type.put("media_id",text);
        }else if(msgtype.equals("voice")){
            type.put("media_id",text);
        }
        jsonParam.put(msgtype,type);

        String result = null;
        try {
            if (jsonParam != null) {
                String sendMessUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
                result = String.format(sendMessUrl, token);

                HttpClient httpClient = new DefaultHttpClient();

                System.out.println("start to send :"+jsonParam);
                log.info(jsonParam.toString());
                StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");

                // 创建Get方法实例
                HttpPost me = new HttpPost(result);
                me.setEntity(entity);
                HttpResponse hr = httpClient.execute(me);
                String data = EntityUtils.toString(hr.getEntity());
                me.releaseConnection();

                JSONObject userInfo = JSONObject.fromObject(data);
                System.out.println("get backMessage ..." + data);
                System.out.println("get backMessage ..." + data);
                if(userInfo.getString("errmsg").equals("ok")){
                    flag = true;
                }else{
                    flag = false;
                }
            }
        } catch (Exception ex) {
            System.out.println("error");
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    public static  boolean sendNewsMessage(String touser,List<JSONObject> joList){
        String token = getAccessToken().getToken();
        boolean flag = false;
        JSONObject jsonParam = new JSONObject();
        JSONObject news = new JSONObject();

        jsonParam.put("touser",touser);
        jsonParam.put("msgtype","news");

        news.put("articles",joList);
        jsonParam.put("news",news);
        log.info(jsonParam.toString());
        String result = null;
        try {
            if (jsonParam != null) {
                String sendMessUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
                result = String.format(sendMessUrl, token);

                HttpClient httpClient = new DefaultHttpClient();
                System.out.println("start to send :"+jsonParam);
                StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");

                // 创建Get方法实例
                HttpPost me = new HttpPost(result);
                me.setEntity(entity);
                HttpResponse hr = httpClient.execute(me);
                String data = EntityUtils.toString(hr.getEntity());
                me.releaseConnection();

                JSONObject userInfo = JSONObject.fromObject(data);
                System.out.println("get backMessage ..." + data);
                if(userInfo.getString("errmsg").equals("ok")){
                    flag = true;
                }else{
                    flag = false;
                }
            }
        } catch (Exception ex) {
            System.out.println("error");
            System.out.println(ex.getMessage());
        }
        return flag;
    }

}
