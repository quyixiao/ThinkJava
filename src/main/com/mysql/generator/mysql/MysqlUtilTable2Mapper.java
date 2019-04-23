package com.mysql.generator.mysql;


import com.mysql.generator.MysqlMain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MysqlUtilTable2Mapper {

    public static String TAB = "	";

    public static void printDao(TablesBean tableBean) {

        String realName = MysqlMain.pre + tableBean.getSpaceName();

        String fileName = MysqlMain.save_path + "/" + realName + "Mapper.java";


        try {

            String content = "package com.admin.mapper;\n";


            content += "/**\n";
            content += "* <p>\n";
            content += "* " + tableBean.getComment() + " 服务类\n";
            content += "* </p>\n";
            content += "*\n";
            content += "* @author quyixiao\n";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            content += "* @since " + format.format(new Date()) + "\n";
            content += "*/\n";


            content += "public interface " + realName + "Mapper extends BaseMapper<" + realName + "> {";
            content += "\n";
            content += "\n";
            content += "\n";


            content += TAB + realName + " select" + realName + "ById(@Param(\"id\")Long id);";
            content += "\n";
            content += "\n";
            content += "\n";

            content += TAB + "Long insert" + realName + "(" + realName + " " + tableBean.getJavaName() + ");";
            content += "\n";
            content += "\n";
            content += "\n";

            content += TAB + "int update" + realName + "ById(" + realName + " " + tableBean.getJavaName() + ");";
            content += "\n";
            content += "\n";
            content += "\n";

            content += TAB + "int updateCover" + realName + "ById(" + realName + " " + tableBean.getJavaName() + ");";
            content += "\n";
            content += "\n";
            content += "\n";

            content += TAB + "int delete" + tableBean.getSpaceName() + "ById(@Param(\"id\")Long id);";
            content += "\n";
            content += "\n";
            content += "\n";

            content += "}";

            FileOutputStream fos = new FileOutputStream(fileName);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(content);
            out.close();
            fos.close();

            System.out.println("===" + realName + "Mapper.java" + "生成");
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            // FileWriter writer = new FileWriter(fileName, false);
            // writer.write(content);
            // writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
