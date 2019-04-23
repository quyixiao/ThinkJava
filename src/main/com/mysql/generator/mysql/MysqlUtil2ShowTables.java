package com.mysql.generator.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlUtil2ShowTables {
    public static List<TablesBean> showTableNameList() {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://121.43.74.40:3300/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
        String user = "root";
        String password = "quyixiao";

        List<TablesBean> list = new ArrayList<TablesBean>();

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn.isClosed()) {
                return null;
            }
            Statement statement = conn.createStatement();
            list = getTables(conn);   // 获取所有表名
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static String convertDatabaseCharsetType(String in, String type) {
        String dbUser;
        if (in != null) {
            if (type.equals("oracle")) {
                dbUser = in.toUpperCase();
            } else if (type.equals("postgresql")) {
                dbUser = "public";
            } else if (type.equals("mysql")) {
                dbUser = null;
            } else if (type.equals("mssqlserver")) {
                dbUser = null;
            } else if (type.equals("db2")) {
                dbUser = in.toUpperCase();
            } else {
                dbUser = in;
            }
        } else {
            dbUser = "public";
        }
        return dbUser;
    }

    private static List<TablesBean> getTables(Connection conn) throws SQLException {
        List<TablesBean> list = new ArrayList<TablesBean>();
        DatabaseMetaData dbMetData = conn.getMetaData();
        ResultSet rs = dbMetData.getTables(null,
                convertDatabaseCharsetType("root", "mysql"), null,
                new String[]{"TABLE", "VIEW"});

        while (rs.next()) {
            if (rs.getString(4) != null
                    && (rs.getString(4).equalsIgnoreCase("TABLE") || rs
                    .getString(4).equalsIgnoreCase("VIEW"))) {
                String tableName = rs.getString(3).toLowerCase();
                TablesBean bean = new TablesBean(tableName);
                list.add(bean);
            }
        }

        return list;
    }

}
