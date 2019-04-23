package com.mysql.generator;


import com.mysql.generator.mysql.*;
import com.api.common.utils.StringUtil;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MysqlMain {

    public static String package_name = "com.api.model.entity";
    public static String package_name_model = "com.chengyi.user.dao";

    public static String save_path = "/Users/zhy/logs";


    public static String mysql_url = "jdbc:mysql://101.37.106.150:3306";


    public static String pre = "";



    public static String mysql_dbname = "my_wallet";
    public static String mysql_username = "ldd_biz";
    public static String mysql_password = "Hello1234";




    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        String dir = null;
        if(StringUtil.isNotBlank(path)){
            dir = path.split("target")[0];
        }
        save_path = dir + "src/test/tmp";
        System.out.println(save_path);

        List<TablesBean> list = new ArrayList<TablesBean>();

        list.add(new TablesBean("third_weibo_order_info"));







        List<TablesBean> list2 = new ArrayList<TablesBean>();
        Map<String,String> map = MysqlUtil2ShowCreateTable.getComments();

        for (int i = 0; i < list.size(); i++) {
            TablesBean obj = list.get(i);
            String tableName = list.get(i).getTableName();
            List<FieldBean> itemList = MysqlUtil2ShowCreateTable.readTableDetail(tableName);
            obj.setFieldList(itemList);
            obj.setComment(map.get(tableName));
            list2.add(obj);
        }

        for (int i = 0; i < list2.size(); i++) {
            MysqlUtilTable2Bean.printEntity(list2.get(i));
        }


        for (int i = 0; i < list2.size(); i++) {
            MysqlUtilTable2Contoller.printController(list2.get(i));
        }



        for (int i = 0; i < list2.size(); i++) {
            MysqlUtilTable2Mapper.printDao(list2.get(i));
        }

        for (int i = 0; i < list2.size(); i++) {
            MysqlUtilTable2Service.printService(list2.get(i));
            MysqlUtilTable2Service.printServiceImpl(list2.get(i));
        }


        for (int i = 0; i < list2.size(); i++) {
            MysqlUtilTable2XML.printXMLForMap(list2.get(i));
        }


    }

}
