package com.mysql.generator.mysql;


import com.mysql.generator.MysqlMain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class MysqlUtilTable2XML {

    public static String TAB = "    ";

    public static void printXMLForMap(TablesBean bean) {
        String realName = MysqlMain.pre + bean.getSpaceName() + "";
        String realNam2 = MysqlMain.pre + bean.getSpaceName();

        StringBuffer bf = new StringBuffer();

        bf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n");
        bf.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append("\n");
        bf.append("<mapper namespace=\"com.admin.mapper."+realName+"Mapper\">").append("\n");
        bf.append("\n");
        bf.append("    <!-- 通用查询映射结果 -->\n");
        bf.append("    <resultMap id=\"BaseResultMap\" type=\"com.admin.common.entity."+realName+"\">\n");
        List<FieldBean> list = bean.getFieldList();

        int i = 0;
        // 定义get set方法
        for (FieldBean tb : list) {
            if(i == 0 ){
                bf.append("        <id column=\""+tb.getField()+"\" property=\""+tb.getJavaCode()+"\"/>\n");
            }else{
                bf.append("        <result column=\""+tb.getField()+"\" property=\""+tb.getJavaCode()+"\"/>\n");
            }
            i ++;
        }
        bf.append("    </resultMap>\n");
        bf.append("\n");
        bf.append("\n");
        bf.append("    <!-- 通用查询结果列 -->\n");
        bf.append("    <sql id=\"Base_Column_List\">\n");
        // 定义get set方法
        StringBuilder sb = new StringBuilder();
        int j =0;
        for (FieldBean tb : list) {
            sb.append(tb.getField()+" AS " + tb.getJavaCode());
            j ++;
            if(j < list.size()){
                sb.append(", ");
            }
        }
        bf.append("        "+sb.toString()+"\n");
        bf.append("    </sql>\n");
        bf.append("\n");
        bf.append("\n");


        bf.append("\n");
        bf.append("\n");
        // 根据ID删除
        bf.append(TAB).append("<select id=\"select"+realName+"ById\" resultType=\""+bean.getSpaceName()+"\" >").append("\n");
        bf.append(TAB).append(TAB).append("select * from ").append(bean.getTableName()).append("  where id=#{id} and is_delete = 0 limit 1 ").append("\n");
        bf.append(TAB).append("</select>").append("\n");
        bf.append("\n");
        bf.append("\n");

        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        bf.append(TAB).append("<insert id=\"insert"+bean.getSpaceName()+"\" parameterType=\"").append(bean.getSpaceName()).append("\" useGeneratedKeys=\"true\" keyProperty=\"id\" >").append("\n");
        bf.append(TAB).append(TAB).append("insert into ").append(bean.getTableName()).append("(").append("\n");
        for(i =0;i<bean.getFieldList().size();i++) {
            FieldBean obj = bean.getFieldList().get(i);
            if ("id".equals(obj.getField().toLowerCase()) ||"gmt_create".equals(obj.getField()) || "gmt_modified".equals(obj.getField())
                    || "is_delete".equals(obj.getField())) {
                continue;
            }
            bf.append(TAB).append(TAB).append(TAB).append("<if test=\"").append(obj.getJavaCode()).append(" != null\">").append(obj.getField()).append(", </if>").append("\n");
        }

        bf.append(TAB).append(TAB).append(TAB).append("is_delete,").append("\n");
        bf.append(TAB).append(TAB).append(TAB).append("gmt_create,").append("\n");
        bf.append(TAB).append(TAB).append(TAB).append("gmt_modified").append("\n");
        bf.append(TAB).append(TAB).append(")values(").append("\n");
        for(i =0;i<bean.getFieldList().size();i++) {
            FieldBean obj = bean.getFieldList().get(i);
            if ("id".equals(obj.getField().toLowerCase()) ||"gmt_create".equals(obj.getField())
                    || "gmt_modified".equals(obj.getField()) || "is_delete".equals(obj.getField())) {
                continue;
            }
            bf.append(TAB).append(TAB).append(TAB).append("<if test=\"").append(obj.getJavaCode()).append(" != null\">#{ ").append(obj.getJavaCode()).append("}, </if>").append("\n");
        }

        bf.append(TAB).append(TAB).append(TAB).append("0,").append("\n");
        bf.append(TAB).append(TAB).append(TAB).append("now(),").append("\n");
        bf.append(TAB).append(TAB).append(TAB).append("now()").append("\n");
        bf.append(TAB).append(TAB).append(")").append("\n");
        bf.append(TAB).append("</insert>").append("\n");
        bf.append("\n");
        bf.append("\n");

        /////////////////////////////////////////////////////////////////////////////////////////////////////////


        bf.append(TAB).append("<update id=\"update"+bean.getSpaceName()+"ById\" parameterType=\"").append(bean.getSpaceName()).append("\" >").append("\n");
        bf.append(TAB).append(TAB).append("update").append("\n");
        bf.append(TAB).append(TAB).append(TAB).append(bean.getTableName()).append("\n");
        bf.append(TAB).append(TAB).append("<trim prefix=\"set\" suffixOverrides=\",\">").append("\n");
        for(i =0;i<bean.getFieldList().size()-1;i++) {
            FieldBean obj = bean.getFieldList().get(i);
            if ("id".equals(obj.getField().toLowerCase()) || "gmt_modified".equals(obj.getField())) {
                continue;
            }
            bf.append(TAB).append(TAB).append(TAB).append("<if test=\"").append(obj.getJavaCode()).append(" != null\">");
            bf.append(obj.getField()).append(" = #{").append(obj.getJavaCode()).append("},");
            bf.append("</if>").append("\n");
        }

        if( bean.getFieldList().size() <= 0){

            System.out.println("====================>name =" + bean.getJavaName() + ",size=" + bean.getFieldList().size());


            return ;
        }

        FieldBean obj = bean.getFieldList().get(bean.getFieldList().size()-1);
        bf.append(TAB).append(TAB).append(TAB).append("<if test=\"").append(obj.getJavaCode()).append(" != null\">");
        bf.append(obj.getField()).append(" = #{").append(obj.getJavaCode()).append("}");
        bf.append("</if>");
        bf.append(TAB).append(TAB).append(TAB).append(TAB).append("\n");
        bf.append(TAB).append(TAB).append("</trim>").append("\n");
        bf.append(TAB).append(TAB).append(",gmt_modified = now()").append("\n");
        bf.append(TAB).append(TAB).append("where id = #{id}").append("\n");
        bf.append(TAB).append("</update>").append("\n");



        /////////////////////////////////////////////////////////////////////////////////////////////////////////


        bf.append("\n");
        bf.append("\n");

        bf.append(TAB).append("<update id=\"updateCover"+bean.getSpaceName()+"ById\" parameterType=\"").append(bean.getSpaceName()).append("\" >").append("\n");
        bf.append(TAB).append(TAB).append("update").append("\n");
        bf.append(TAB).append(TAB).append(TAB).append(bean.getTableName()).append(TAB).append("\n");
        bf.append(TAB).append(TAB).append("set ").append(TAB).append("\n");

        for(i =0;i<bean.getFieldList().size()-1;i++) {
            obj = bean.getFieldList().get(i);
            if ("id".equals(obj.getField().toLowerCase())|| "gmt_modified".equals(obj.getField())) {
                continue;
            }
            bf.append(TAB).append(TAB).append(TAB).append(obj.getField()).append(" = #{").append(obj.getJavaCode()).append("},\n");
        }

        obj = bean.getFieldList().get(bean.getFieldList().size()-1);
        bf.append(TAB).append(TAB).append(TAB).append(obj.getField()).append(" = #{").append(obj.getJavaCode()).append("}");
        bf.append(TAB).append(TAB).append(TAB).append(TAB).append("\n");
        bf.append(TAB).append(TAB).append(",gmt_modified = now()").append("\n");
        bf.append(TAB).append(TAB).append("where id = #{id}").append("\n");
        bf.append(TAB).append("</update>").append("\n");


        bf.append("\n");
        bf.append("\n");
        // 根据ID删除
        bf.append(TAB).append("<update id=\"delete"+realName+"ById\" parameterType=\"java.lang.Long\">").append("\n");
        bf.append(TAB).append(TAB).append("update ").append(bean.getTableName()).append(" set is_delete = 1 where id=#{id} limit 1  ").append("\n");
        bf.append(TAB).append("</update>").append("\n");
        bf.append("\n");


        bf.append("</mapper>").append("\n");
        bf.append("\n");



        String fileName = MysqlMain.save_path + "/" + realNam2 + "Mapper.xml";
        try {

            FileOutputStream fos = new FileOutputStream(fileName);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(bf.toString());
            out.close();
            fos.close();

            System.out.println("===" + realName + "Mapper.xml" + "生成");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getXmlType(String type) {
        if ("date".equals(type.toLowerCase())) {
            return "java.util." + type;
        } else {
            return "java.lang." + type;
        }

    }
}
