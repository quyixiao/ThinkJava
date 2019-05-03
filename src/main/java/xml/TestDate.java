package xml;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {


    public static void main(String[] args) throws Exception{
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf3.parse("2019-05-28 23:59:59");
        //距离天数
        int a = (int)((date1.getTime() - new Date().getTime()) / (24 * 60 * 60 * 1000));
        System.out.println(a);




        Date date2= sdf3.parse("2019-05-29 00:00:00");
        //距离天数
        int b = (int)((date2.getTime() - new Date().getTime()) / (24 * 60 * 60 * 1000));
        System.out.println(b);


    }
}
