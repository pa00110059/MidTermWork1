package dataimport;

import Utils.JDBCUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVToDatabase {
    //指定輸入檔案路徑
    private static final String CSV_FILE_PATH = "C:\\Users\\User\\Desktop\\MyMidtermWork\\dependency_materialities.csv";

    public static void main(String[] args) {
        CSVToDatabase csvToDatabase = new CSVToDatabase();
        csvToDatabase.importCSVToDatabase();
    }

    public void importCSVToDatabase() {
        // SQL 插入語句
        String sql = """
                INSERT INTO dependency_materialities (
                    Process,
                    Ecosystem_Service,
                    Rating,
                    Justification
                ) VALUES (?, ?, ?, ?);
                """;

        try (
                // 建立檔案讀取器與 CSV 解析器
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader() // 自動讀取 CSV 標題列
                        .withIgnoreHeaderCase() // 忽略標題大小寫
                        .withTrim()); // 自動移除多餘空白
                Connection connection = JDBCUtils.getConnection(); // 獲取資料庫連線
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            // 逐行解析 CSV 檔案
            for (CSVRecord record : csvParser) {
                try {
                    String process = record.get("Process");
                    String ecosystemService = record.get("Ecosystem Service");
                    String rating = record.get("Rating");
                    String justification = record.get("Justification");

                    // 設置參數
                    preparedStatement.setString(1, process);
                    preparedStatement.setString(2, ecosystemService);
                    preparedStatement.setString(3, rating);
                    preparedStatement.setString(4, justification);

                    // 執行插入操作
                    preparedStatement.execute();
                } catch (IllegalArgumentException | SQLException e) {
                    System.err.println("資料格式錯誤，跳過：" + record);
                    e.printStackTrace();
                }
            }

            System.out.println("CSV 資料已成功匯入資料庫！");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}