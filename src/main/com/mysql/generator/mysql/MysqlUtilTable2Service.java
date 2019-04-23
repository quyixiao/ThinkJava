package com.mysql.generator.mysql;


import com.mysql.generator.MysqlMain;
import com.api.common.utils.MysqlUtil;
import com.api.common.utils.StringUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MysqlUtilTable2Service {


    public static String TAB = "	";

    public static void printService(TablesBean tableBean) {
        String realName = MysqlMain.pre + tableBean.getSpaceName();
        String realName2 = MysqlMain.pre + tableBean.getSpaceName() + "";
        String fileName = MysqlMain.save_path + "/" + realName + "service.java";

        try {
            String content = "package com.api.business.service;\n";


            content +="/**\n";
            content +="* <p>\n";
            content +="* "+tableBean.getComment()+" 服务类\n";
            content +="* </p>\n";
            content +="*\n";
            content +="* @author quyixiao\n";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            content += "* @since " + format.format(new Date()) + "\n";
            content +="*/\n";
            content += "public interface "+tableBean.getSpaceName()+"service extends IService<"+tableBean.getSpaceName()+"> {\n";
            content +="\n";
            content +="\n";
            content +="\n";


            content += TAB+realName+" select"+realName+"ById(Long id);";
            content +="\n";
            content +="\n";
            content +="\n";

            content += TAB+"Long insert"+realName+"("+realName+" "+tableBean.getJavaName()+");";
            content +="\n";
            content +="\n";
            content +="\n";

            content += TAB+"int update"+realName+"ById("+realName+" "+tableBean.getJavaName()+");";
            content +="\n";
            content +="\n";
            content +="\n";

            content += TAB+"int updateCover"+realName+"ById("+realName+" "+tableBean.getJavaName()+");";
            content +="\n";
            content +="\n";
            content +="\n";

            content += TAB+"int delete"+tableBean.getSpaceName()+"ById(Long id);";
            content +="\n";
            content +="\n";
            content +="\n";





            content += "}";

            FileOutputStream fos = new FileOutputStream(fileName);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(content);
            out.close();
            fos.close();

            System.out.println("===" + realName + "service.java" + "生成");
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            // FileWriter writer = new FileWriter(fileName, false);
            // writer.write(content);
            // writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String captureName(String name) {
        if(StringUtil.isNoneBlank(name)&& name.length() > 0){
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return name;

    }

    public static void printServiceImpl(TablesBean tableBean) {
        String realName = MysqlMain.pre + tableBean.getSpaceName();

        String realName2 = MysqlMain.pre + tableBean.getSpaceName() + "";

        String javaName = "" + captureName(tableBean.getJavaName());
        String fileName = MysqlMain.save_path + "/" + realName + "ServiceImpl.java";

        try {
            String content = "package com.admin.service.impl;\n";



            content +="/**\n";
            content +="* <p>\n";
            content +="* "+tableBean.getComment()+" 服务类\n";
            content +="* </p>\n";
            content +="*\n";
            content +="* @author quyixiao\n";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            content += "* @since " + format.format(new Date()) + "\n";
            content +="*/\n";

            content += "\n";
            content += "@service\n";
            content += "public class "+tableBean.getSpaceName()+"ServiceImpl extends ServiceImpl<"+tableBean.getSpaceName()+"Mapper, "+tableBean.getSpaceName()+"> implements "+tableBean.getSpaceName()+"service {\n";
            content += "\n";
            content += "\n";
            content += "    @Autowired\n";
            content += "\tprivate " + realName + "Mapper " + MysqlUtil.getFirstToLower(javaName) + "Mapper;" + "\n";
            content += "\n";
            content += "\n";
            content += "\n";

            content += TAB +"@Override\n";
            content += TAB +"public "+ realName + " select" + realName + "ById(Long id){\n";
            content += TAB + TAB + "return " + tableBean.getJavaName() + "Mapper." + "select" + realName + "ById(id);\n";
            content += TAB + "}\n";
            content += "\n";
            content += "\n";
            content += "\n";
            content += TAB +"@Override\n";
            content += TAB + "public Long insert" + realName + "(" + realName + " " + tableBean.getJavaName() + "){\n";
            content += TAB + TAB + "return " + tableBean.getJavaName() + "Mapper." + "insert" + realName + "(" + tableBean.getJavaName() + ");\n";
            content += TAB + "}\n";
            content += "\n";
            content += "\n";
            content += "\n";
            content += TAB +"@Override\n";
            content += TAB + "public int update" + realName + "ById(" + realName + " " + tableBean.getJavaName() + "){\n";
            content += TAB + TAB + "return " + tableBean.getJavaName() + "Mapper." + "update" + realName + "ById(" + tableBean.getJavaName() + ");\n";
            content += TAB + "}\n";

            content += "\n";
            content += "\n";
            content += "\n";
            content += TAB +"@Override\n";
            content += TAB + "public int updateCover" + realName + "ById(" + realName + " " + tableBean.getJavaName() + "){\n";
            content += TAB +TAB+ "return " + tableBean.getJavaName() + "Mapper." + "updateCover" + realName + "ById(" + tableBean.getJavaName() + ");\n";
            content += TAB + "}\n";
            content += "\n";
            content += "\n";
            content += "\n";
            content += TAB +"@Override\n";
            content += TAB + "public int delete" + tableBean.getSpaceName() + "ById(Long id){\n";
            content += TAB +TAB+ "return " + tableBean.getJavaName() + "Mapper." + "delete" + tableBean.getSpaceName() + "ById(id);\n";
            content += TAB + "}\n";
            content +="\n";
            content +="\n";
            content +="\n";


            content += "}" + "\n";
            FileOutputStream fos = new FileOutputStream(fileName);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(content);
            out.close();
            fos.close();

            System.out.println("===" + realName + "ServiceImpl.java" + "生成");
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            // FileWriter writer = new FileWriter(fileName, false);
            // writer.write(content);
            // writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
