package com.tracer.welcomesystem.repositories;
import java.sql.*;
public class demo1 {
    //驱动路径
    private static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //数据库地址
    private static final String DBURL = "jdbc:sqlserver://localhost:1434;DataBaseName=Student2;Encrypt=false;trustServerCertificate=false";    //数据库登录用户名
    private static final String DBUSER = "sa";
    //数据库用户密码
    private static final String DBPASSWORD = "123";
    //数据库连接
    public static Connection conn = null;
    //程序入口函数
    public static void main(String[] args) {
        try {
            //加载驱动程序
            Class.forName(DBDRIVER);
            //连接数据库
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
