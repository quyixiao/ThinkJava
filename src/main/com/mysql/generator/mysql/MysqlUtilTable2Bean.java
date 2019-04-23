package com.mysql.generator.mysql;


import com.mysql.generator.MysqlMain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MysqlUtilTable2Bean {
    /**
     * 打印entity的信息
     *
     */
    public static void printEntity(TablesBean tableBean) {

        boolean hasDate = false;

        List<FieldBean> list = tableBean.getFieldList();

        StringBuffer bf = new StringBuffer();

        String realName = MysqlMain.pre + tableBean.getSpaceName() + "";
        String fileName = MysqlMain.save_path + "/" + realName + ".java";

        int i = 0 ;
        // 定义声明
        for (FieldBean tb : list) {
            String temp = "";
            temp += "    //" + tb.getComment() + "\n";
            if(i ==0){
                temp += "    @TableId(value = \""+tb.getField()+"\", type = IdType.AUTO)\n";
            }
            temp += "    private " + tb.getJavaType() + " " + tb.getJavaCode() + ";";
            i ++;
            // System.out.println(temp);
            bf.append(temp).append("\n");


            if (!hasDate && "Date".equals(tb.getJavaType())) {
                hasDate = true;
            }
        }



        StringBuilder sb = new StringBuilder();

        sb.append("    @Override\n");
        sb.append("    public String toString() {\n");
        sb.append("        return \""+realName+"{\" +\n");

        for (FieldBean tb : list) {
            String temp = "";
            sb.append("                \","+tb.getJavaCode()+"=\" + "+tb.getJavaCode()+" +\n");
        }
        sb.append("                \"}\";\n");
        sb.append("    }\n");
        try {

            String content = "package " + MysqlMain.package_name + ";\n";
            content += "import com.baomidou.mybatisplus.annotations.TableId;\n";
            content += "import com.baomidou.mybatisplus.annotations.TableName;\n";
            content += "import com.baomidou.mybatisplus.enums.IdType;\n";
            content += "import lombok.Data;\n";


            if (hasDate) {
                content += "import java.util.Date;" + "\n";
            }




            content += "/**\n";
            content += "* <p>\n";
            content += "* 菜单权限表\n";
            content += "* </p>";
            content += "*"+tableBean.getComment()+"\n";
            content += "* @author quyixiao\n";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            content += "* @since " + format.format(new Date()) + "\n";
            content +="*/\n";

            content += "\n";
            content += "@Data\n";
            content += "@TableName(" + "\"" + tableBean.getTableName() + "\")\n";
            content += "public class " + realName + " implements java.io.Serializable {\n" + bf.toString();
            content += sb.toString();
            content += "}";

            FileOutputStream fos = new FileOutputStream(fileName);

            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(content);
            out.close();
            fos.close();
            System.out.println("===" + realName + ".java" + "生成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
