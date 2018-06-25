package com.zcmzjp.wx.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);


    public static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 得到一个月的天数
     */
    public static int GetdayofManth() {
        Calendar a = Calendar.getInstance(Locale.CHINA);
        int day = a.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * 得到当前年月份
     */
    public static String getNow(String format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String date = formatter.format(currentTime);
        return date;
    }

    /**
     * 得到当前年月份
     */
    public static String getYearMonth() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String date = formatter.format(currentTime);
        return date;
    }

    /**
     * 得到当前月份
     */
    public static String getMonth() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        String date = formatter.format(currentTime);
        return date;
    }

    /**
     * 得到当前年份
     */
    public static String getYear() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String date = formatter.format(currentTime);
        return date;
    }

    /**
     * String转Date
     *
     * @param date
     * @return Date
     */
    public static Date toStandarDate(String date) {
        try {
            SimpleDateFormat standarDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return standarDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * String转Date
     *
     * @param date
     * @param format
     * @return Date
     */
    public static Date toStandarDate(String date, String format) {
        try {
            SimpleDateFormat standarDateFormat = new SimpleDateFormat(format);
            return standarDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static Date toStandarDatehms(String date) {
        try {
            SimpleDateFormat standarDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return standarDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    static class StandardDateFormatter {
        private static SimpleDateFormat INSTANCE = null;

        public static SimpleDateFormat getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new SimpleDateFormat("yyyy-MM-dd");
            }
            return INSTANCE;
        }
    }

    /**
     * 得到当前年月日时分秒（String）
     *
     * @return
     */
    public static String getNow() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(currentTime);
        return date;
    }

    /**
     * 得到当前日期（Date）
     *
     * @return
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        return currentTime;
    }


    /**
     * 转换年月日（Date=》String）
     *
     * @param date
     * @return
     */
    public static String stringToDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = formatter.format(date);
        return date1;
    }

    /**
     * 获取两个日期中的所有日期
     *
     * @param startdate
     * @param enddate
     * @return String
     */
    public static List<String> findalldates(String startdate, String enddate) {
        List<String> list = new ArrayList<String>();
        list.add(startdate);
        Calendar calstart = Calendar.getInstance();
        calstart.setTime(toStandarDate(startdate));
        Calendar calend = Calendar.getInstance();
        calend.setTime(toStandarDate(enddate));
        while (toStandarDate(enddate).after(calstart.getTime())) {
            calstart.add(Calendar.DAY_OF_MONTH, 1);
            list.add(stringToDate(calstart.getTime()));

        }
        return list;
    }

    /**
     * 获得当前周的每一天
     *
     * @param mString
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<String> dateEveyweek(String mString) {
        Date mdate = toStandarDate(mString);
        int b = mdate.getDay();
        Date fDate;
        List<String> list = new ArrayList<String>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a < 8; a++) {
            fDate = new Date();
            fDate.setTime(fTime + (a * 24 * 3600000));
            list.add(stringToDate(fDate));
        }
        return list;
    }

    /**
     * 获取一周的开头和结尾
     *
     * @param mString
     * @return List<String>
     */
    @SuppressWarnings("deprecation")
    public static List<String> dateToWeek(String mString) {
        Date mdate = toStandarDate(mString);
        int b = mdate.getDay();
        Date fDate;
        List<String> list = new ArrayList<String>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a < 8; a++) {
            fDate = new Date();
            fDate.setTime(fTime + (a * 24 * 3600000));
            if (a == 1) {
                list.add(stringToDate(fDate));
            }
            if (a == 7) {
                list.add(stringToDate(fDate));
            }

        }
        return list;
    }

    public static Map<String, String> getStringParams(HttpServletRequest request) {
        Map<String, String[]> rawParam = request.getParameterMap();
        Map<String, String> keyParam = new HashMap<String, String>();
        for (String key : rawParam.keySet()) {
            if (rawParam.get(key) != null) {
                String[] value = rawParam.get(key);
                if (value != null && value.length == 1) {
                    keyParam.put(key, value[0]);
                }
            }
        }

        return keyParam;
    }

    public static String toJson(HttpServletRequest request) throws Exception {
        int clen = request.getContentLength();
        //过滤json对象中的非法字符串
        if (clen > 0) {
            byte[] buffer = new byte[clen];
            for (int i = 0; i < clen; i++) {
                int readlen = request.getInputStream().read(buffer, i, clen - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            }
            return new String(buffer, request.getCharacterEncoding());
        }
        return null;
    }

    /**
     * 获取Ip地址
     * @return
     */
    public static String getIpAdrress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

    /**
     * 计算两个日期相差的分钟
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static String getMinutes(String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        long from = simpleFormat.parse(startDate).getTime();
        long to = simpleFormat.parse(endDate).getTime();
        return  String.valueOf(((to - from)/(1000 * 60 )));
    }

    //获取明天的开始时间
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }
    //获取当天的开始时间
    public static java.util.Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}






