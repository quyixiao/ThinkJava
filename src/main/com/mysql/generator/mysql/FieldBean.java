package com.mysql.generator.mysql;


import com.api.common.utils.MysqlUtil;
import com.api.common.utils.StringUtil;

public class FieldBean {

    private String field;// 数据库字段名
    private String type;// 数据库字段烈性
    private String lenth;// 长度
    private String comment;// 注释

    private String javaCode;// java名
    private String javaType;// java类型
    private String javaTypeFull;// ...



    public FieldBean(String field, String type, String comment) {
        this.field = field;
        this.type = type;
        //this.lenth = lenth;
        this.comment = comment;

        this.javaCode = MysqlUtil.field2JavaCode(this.field);
        this.javaTypeFull = MysqlUtil.type2JavaTypeFull(this.type);
        this.javaType = MysqlUtil.type2JavaType(this.type);
    }

    public String getJavaCodeForGet() {
        return "get" + javaCode.substring(0, 1).toUpperCase()
                + javaCode.substring(1);
    }

    public String getJavaCodeForSet() {
        return "set" + javaCode.substring(0, 1).toUpperCase()
                + javaCode.substring(1);
    }

    // --------------------------------------------------------------------

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLenth() {
        return lenth;
    }

    public void setLenth(String lenth) {
        this.lenth = lenth;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJavaCode() {
        return javaCode;
    }

    public void setJavaCode(String javaCode) {
        this.javaCode = javaCode;
    }

    public String getJavaType() {
        if (StringUtil.isEmpty(javaType)) {
            return "String";
        }
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaTypeFull() {
        return javaTypeFull;
    }

    public void setJavaTypeFull(String javaTypeFull) {
        this.javaTypeFull = javaTypeFull;
    }

}
