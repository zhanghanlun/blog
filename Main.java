package com.vivo.internet.gamecontentsupport.service.gamecontent.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description 测试函数
 * @Author 11084850
 * @Create 2018-06-25 9:25
 **/
public class Main {
    private final static String QUEUE_NAME = "hello";
    private static Connection conn;

    public static void main(String[] args) {


        ComboPooledDataSource ds = new ComboPooledDataSource("test");
        System.out.println(ds);

        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(ds);
        String sql = "SELECT PositionType,AveSalary FROM job WHERE PositionType = '.NET';";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("close");
        }

    }
}
