package com.zcmzjp.wx.override;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.SingleLineFormat;
import org.apache.log4j.Logger;

/**
 * Created by Onion on 2017/2/23 0023.
 */
public class P6LogFormat extends SingleLineFormat {

    private static SQLFormatter formatter = new SQLFormatter();

    private static Logger logger = Logger.getLogger(P6LogFormat.class);

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        Thread t = Thread.currentThread();
        sql = formatter.format(P6Util.singleLine(sql));
        logger.info(sql);
        return now + "|" + t.getName() + "|" +
                "" + elapsed + "|" +
                "" + category + "|connection " +
                "" + connectionId + "|" +
               // "\nAfter Prepared SQL:" + formatter.format(P6Util.singleLine(prepared)) +
                "\nBefore Prepared SQL:" + sql;
    }
}






