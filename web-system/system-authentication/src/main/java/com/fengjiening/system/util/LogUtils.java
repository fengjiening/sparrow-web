package com.fengjiening.system.util;

import org.slf4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName: LogUtils
 * @Description: 打印输出日志
 * @Date: 2021/8/4 10:34
 * @Author: fengjiening::joko
 * @Version: 0.0
 */
public class LogUtils {

    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally
        {
            pw.close();
        }
    }


    /**
     * 输出错误日志记录
     * @param log
     * @param e
     */
    public static void logErrInfo(Logger log, Exception e) {
        StackTraceElement s= e.getStackTrace()[0];//数组长度为 1
        log.error("\n\n-----------------"+
                "\n报错文件名:"+s.getFileName()+
                "\n报错的类："+s.getClassName()+
                "\n报错方法：："+s.getMethodName()+
                "\n报错的行："+ s.getLineNumber()+
                "\n报错的message："+ e.getMessage()+
                "\n错误堆栈：\n"+getStackTrace(e)+
                "\n------------------\n\n");
    }
}
