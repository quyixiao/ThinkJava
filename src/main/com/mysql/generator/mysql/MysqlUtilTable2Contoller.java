package com.mysql.generator.mysql;


import com.mysql.generator.MysqlMain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class MysqlUtilTable2Contoller {
    /**
     * 打印entity的信息
     *
     */
    public static void printController(TablesBean tableBean) {

        boolean hasDate = false;

        List<FieldBean> list = tableBean.getFieldList();

        StringBuffer bf = new StringBuffer();

        String realName = MysqlMain.pre + tableBean.getSpaceName() + "";
        String fileName = MysqlMain.save_path + "/" + realName + "Controller.java";

        try {
            StringBuilder content = new StringBuilder();
            content.append("package com.api.admin.controller;\n");
            content.append("\n");
            content.append("\n");
            content.append("\n");
            content.append("\n");
            content.append("@RestController\n");
            content.append("@RequestMapping(\"/"+tableBean.getJavaName()+"\")\n");
            content.append("public class "+tableBean.getSpaceName()+"Controller {\n");
            content.append("\n");
            content.append("\n");
            content.append("    @Autowired\n");
            content.append("    private "+tableBean.getSpaceName()+"service "+tableBean.getJavaName()+"service;\n");
            content.append("\n");
            content.append("\n");
            content.append("    @RequestMapping(\"/list\")\n");
            content.append("     public R list(@RequestBody String body) {\n");
            content.append("        Map<String,Object> params = new HashMap<>();\n");
            content.append("        if(StringUtil.isNotBlank(body)){\n");
            content.append("            params = JSONObject.parseObject(body,Map.class);\n");
            content.append("        }\n");
            content.append("        PageUtils page = "+tableBean.getJavaName()+"service.queryPage(params);\n");
            content.append("        return R.ok().put(\"page\", page);\n");
            content.append("     }\n");
            content.append("\n");
            content.append("\n");



            content.append("     @RequestMapping(\"/getById\")\n");
            content.append("     public R getById(@RequestBody " + tableBean.getSpaceName() + " " + tableBean.getJavaName() + ") {\n");
            content.append("        " + tableBean.getJavaName() + " = " + tableBean.getJavaName() + "service.select" + tableBean.getSpaceName() + "ById(" + tableBean.getJavaName() + ".getId());\n");
            content.append("        return R.ok().put(\"" + tableBean.getJavaName() + "\"," + tableBean.getJavaName() + ");\n");

            content.append("     }\n");


            content.append("\n");
            content.append("\n");
            content.append("     @RequestMapping(\"/update\")\n");
            content.append("     public R update(@RequestBody "+tableBean.getSpaceName()+" "+tableBean.getJavaName()+") {\n");
            content.append("        "+tableBean.getJavaName()+"service.update"+tableBean.getSpaceName()+"ById("+tableBean.getJavaName()+");\n");
            content.append("        return R.ok();\n");
            content.append("     }\n");
            content.append("\n");
            content.append("\n");
            content.append("     @RequestMapping(\"/save\")\n");
            content.append("        public R save(@RequestBody "+tableBean.getSpaceName()+" "+tableBean.getJavaName()+") {\n");
            content.append("        "+tableBean.getJavaName()+"service.insert"+tableBean.getSpaceName()+"("+tableBean.getJavaName()+");\n");
            content.append("        return R.ok();\n");
            content.append("     }\n");
            content.append("\n");
            content.append("\n");
            content.append("    @RequestMapping(\"/delete\")\n");
            content.append("    public R list(@MultiRequestBody Long id) {\n");
            content.append("        "+tableBean.getJavaName()+"service.delete"+tableBean.getSpaceName()+"ById(id);\n");
            content.append("        return R.ok();\n");
            content.append("     }\n");
            content.append("}\n");

            FileOutputStream fos = new FileOutputStream(fileName);

            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(content.toString());
            out.close();
            fos.close();
            System.out.println("===" + realName + ".java" + "生成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
