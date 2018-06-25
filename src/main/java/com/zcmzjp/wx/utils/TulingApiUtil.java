package com.zcmzjp.wx.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.zcmzjp.wx.entity.GlobalParameter;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TulingApiUtil
{
    public static String getTulingResult(String content)
    {
        String apiUrl = "http://www.tuling123.com/openapi/api?key=" + GlobalParameter.APIkey + "&info=";
        String param = "";
        try
        {
            param = apiUrl + URLEncoder.encode(content, "utf-8");
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        HttpGet request = new HttpGet(param);
        String result = "";
        try
        {
            HttpResponse response = HttpClients.createDefault().execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (null == result) {
            return "对不起，您说的话太高深了，俺暂时无法理解呢！";
        }
        try
        {
            JSONObject json = JSONObject.fromObject(result);
            if (100000 == json.getInt("code")) {
                result = json.getString("text");
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
