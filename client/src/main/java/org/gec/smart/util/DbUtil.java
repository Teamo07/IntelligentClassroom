package org.gec.smart.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbUtil {

    private static ComboPooledDataSource ds=new ComboPooledDataSource("mysql");


    //注意：一个应用程序只需要一个数据库连接池就足够了。


    public DbUtil(){

    }


    /**
     * 获取数据库的连接
     * @return
     * @throws SQLException
     */
    public static  Connection getConnection() throws SQLException {
        System.out.println("正在获得数据库连接池Connection。。。。。");
        try {
            return ds.getConnection();
        } catch (Exception e) {
            throw new Error("数据库连接出错!", e);
        }
    }

    /**
     * 释放资源
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }





  /*  private static String url = "jdbc:mysql://47.106.10.184:3306/IntelligentClassroom";
    private static String name = "team";
    private static String passwd = "Ruanjian1150+-.";
    private static Connection conn;
    private static DbUtil jdbcUtilSingle = null;

    // Constructor
    private DbUtil() {
    }

    // 在加载类执行，通过静态代码块注册数据库驱动，保证注册驱动只执行1次
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 获取单例 ， 取代构造函数
    public static DbUtil getInitInstance() {
        if (jdbcUtilSingle == null) {
            // 加锁
            synchronized (DbUtil.class) {
                if (jdbcUtilSingle == null) {
                    jdbcUtilSingle = new DbUtil();
                }
            }
        }
        return jdbcUtilSingle;
    }

    // 获得连接
    public Connection getConnection() {
        try {
            conn = (Connection) DriverManager.getConnection(url, name, passwd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭连接
    public void closeConnection(ResultSet rs, Statement statement,Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // 关闭连接
    public void closeConnection(ResultSet rs, PreparedStatement pstm,Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    } */



}
