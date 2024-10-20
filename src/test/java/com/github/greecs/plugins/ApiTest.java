package com.github.greecs.plugins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApiTest {

    public static void main(String[] args) throws Exception {


        // -javaagent:D:\code\plugins\build\libs\plugins-0.0.1-all.jar
        String URL = "jdbc:mysql://172.19.240.1:3306/big_market?characterEncoding=utf-8";
        String USER = "root";
        String PASSWORD = "root";
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "SELECT * FROM award WHERE id = ? ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, 1);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("id") + " " + rs.getString("award_id"));
        }
    }

}
