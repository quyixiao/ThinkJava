package com.mysql.generator.mysql;

import com.api.common.utils.MysqlUtil;
import com.api.common.utils.StringUtil;

import java.util.List;

public class TablesBean {
    private String tableName;
    private String javaName;
    private String spaceName;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private List<FieldBean> fieldList;

    public TablesBean(String tableName) {
        this.tableName = tableName;

        this.spaceName = MysqlUtil.tableName2SpaceName(tableName);


        if(StringUtil.isNoneBlank( this.spaceName)&& this.spaceName.length() > 0){
            this.javaName = this.spaceName.substring(0, 1).toLowerCase() + this.spaceName.substring(1);
        }

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public List<FieldBean> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldBean> fieldList) {
        this.fieldList = fieldList;
    }

}
