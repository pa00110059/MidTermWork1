package Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    HikariDataSource hikariDataSource = null;
    public static Connection getHikariConnection() throws SQLException {
        HikariDataSource hikariDataSource = null;

        try {
            // 加載配置檔案
            Properties properties = new Properties();
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("hikari.properties");
            if (inputStream == null) {
                throw new IOException("配置檔案 hikari.properties 未找到！");
            }
            properties.load(inputStream);

            // 初始化 HikariCP
            HikariConfig hikariConfig = new HikariConfig(properties);
            hikariDataSource = new HikariDataSource(hikariConfig);

            // 嘗試取得連線
            try (Connection connection = hikariDataSource.getConnection()) {
                System.out.println("連線成功: " + connection);
                // 在此進行 SQL 操作
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return hikariDataSource.getConnection();
    }

    //靜態方法 開啟連線
    public static Connection getConnection() {
        Connection connection = null;
        FileInputStream fileInputStream = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("src/jdbc.properties");
            properties.load(fileInputStream);
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            connection = DriverManager.getConnection(url, user, password);
            boolean status = !connection.isClosed();
            System.out.println("連線狀態:" + status);
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    //關閉方法
    public static void closeResource(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(Connection connection, Statement statement) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

