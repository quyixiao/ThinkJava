package arrays;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/****
 * 日志工具类，升级版
 */
public class LoggerUtils {



    /**
     * 不需要传递参数
     * @param msg
     */
    public static void info(String msg) {
        Throwable throwable = new Throwable();
        StringBuffer sb = getStringBuffer(msg,throwable,getLevel(throwable));
        System.out.println(sb.toString());
    }

    /**
     * 不需要传递参数
     *
     * @param msg
     */
    public static void info(String msg,int level) {
        Throwable throwable = new Throwable();
        StringBuffer sb = getStringBuffer(msg,throwable,level);
         System.out.println(sb.toString());
    }

    public static void error(Exception e) {
        Throwable throwable = new Throwable();
        StringBuffer sb = getStringBuffer("",throwable,getLevel(throwable));
         System.out.println(sb.append("error=").toString() );
    }

    public static void error(String msg, Exception e) {
        Throwable throwable = new Throwable();
        StringBuffer sb = getStringBuffer(msg, throwable, getLevel(throwable));
         System.out.println(sb.append("error=").toString() );
    }

    public static int getLevel(Throwable throwable) {
        return throwable.getStackTrace().length -1;
    }

    private static StringBuffer getStringBuffer(String msg,Throwable throwable, int level) {
        long start = System.currentTimeMillis();
        StringBuilder cml = getRelate(throwable,level);

        long end = System.currentTimeMillis();
        SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sb = appendSb(myFmt2.format(new Date()),
                "	", "relate=" + cml.toString(),
                "	", "exet=" + (end - start),
                "	",msg
        );
        return sb;
    }

    private static StringBuilder getRelate(Throwable throwable,int level) {
        StringBuilder cml = new StringBuilder();
        if (throwable.getStackTrace().length >= 8 && level >= 7) {

            cml.append(getClassName(throwable.getStackTrace()[7].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[7].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[7].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[6].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[6].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[6].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[5].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[5].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[5].getLineNumber());
            cml.append("->");


            cml.append(getClassName(throwable.getStackTrace()[4].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[4].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[4].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[3].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[3].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[3].getLineNumber());
            cml.append("->");
            cml.append(getClassName(throwable.getStackTrace()[2].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[2].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[2].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[1].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[1].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[1].getLineNumber());
        } else if (throwable.getStackTrace().length >= 7 && level ==6) {

            cml.append(getClassName(throwable.getStackTrace()[6].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[6].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[6].getLineNumber());
            cml.append("->");


            cml.append(getClassName(throwable.getStackTrace()[5].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[5].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[5].getLineNumber());
            cml.append("->");


            cml.append(getClassName(throwable.getStackTrace()[4].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[4].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[4].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[3].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[3].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[3].getLineNumber());
            cml.append("->");
            cml.append(getClassName(throwable.getStackTrace()[2].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[2].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[2].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[1].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[1].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[1].getLineNumber());
        } else if (throwable.getStackTrace().length >= 6 && level == 5) {
            cml.append(getClassName(throwable.getStackTrace()[5].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[5].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[5].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[4].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[4].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[4].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[3].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[3].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[3].getLineNumber());
            cml.append("->");
            cml.append(getClassName(throwable.getStackTrace()[2].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[2].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[2].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[1].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[1].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[1].getLineNumber());
        } else if (throwable.getStackTrace().length >= 5 && level == 4) {
            cml.append(getClassName(throwable.getStackTrace()[4].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[4].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[4].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[3].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[3].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[3].getLineNumber());
            cml.append("->");
            cml.append(getClassName(throwable.getStackTrace()[2].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[2].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[2].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[1].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[1].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[1].getLineNumber());
        } else if (throwable.getStackTrace().length >= 4 && level == 3) {
            cml.append(getClassName(throwable.getStackTrace()[3].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[3].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[3].getLineNumber());
            cml.append("->");
            cml.append(getClassName(throwable.getStackTrace()[2].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[2].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[2].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[1].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[1].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[1].getLineNumber());

        } else if (throwable.getStackTrace().length >= 3 && level == 2) {
            cml.append(getClassName(throwable.getStackTrace()[2].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[2].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[2].getLineNumber());
            cml.append("->");

            cml.append(getClassName(throwable.getStackTrace()[1].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[1].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[1].getLineNumber());
        } else if (throwable.getStackTrace().length >= 2 && level ==1) {
            cml.append(getClassName(throwable.getStackTrace()[1].getClassName()));
            cml.append(".");
            cml.append(throwable.getStackTrace()[1].getMethodName());
            cml.append(":");
            cml.append(throwable.getStackTrace()[1].getLineNumber());
        }
        return cml;
    }


    public static String dealException(Exception e) {
        StringWriter sw = null;
        String str = null;
        try {
            e.printStackTrace();
            sw = new StringWriter();
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(new PrintWriter(sw, true));
            str = sw.toString();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return str;
    }


    public static void test1() {
        try {
            info("我要测试");

        } catch (Exception e) {
            error(e);
        }
    }

    public static void test2() {
        try {
            //info("我要{}测{}试",new String []{"value",""},new Object[]{1,2});
            int i = 0;
            int b = 0;
            int c = i / b;
        } catch (Exception e) {
            error(e);
        }
    }


    public static String getClassName(String className) {
        if (isNotBlank(className) && className.contains(".")) {
            String classNames[] = className.split("\\.", className.length());
            if (classNames != null && classNames.length > 0) {
                return classNames[classNames.length - 1];
            }
        }
        return "";
    }


    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        if (str == null)
            return true;
        if (str.length() == 0 || str.equals("null"))
            return true;
        return false;
    }


    /**
     * 通过StringBuffer来组装字符串
     *
     * @param strings
     * @return
     */
    public static StringBuffer appendSb(Object... strings) {
        StringBuffer sb = new StringBuffer();
        for (Object str : strings) {
            sb.append(str);
        }
        return sb;
    }


    public static void test3() {
        test1();
    }

    public static void main(String[] args) {
        test3();
    }


}
