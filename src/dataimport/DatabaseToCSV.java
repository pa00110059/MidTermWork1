package dataimport;

import Utils.JDBCUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DatabaseToCSV {
    //指定檔案輸出路徑
    private static final String OUTPUT_CSV_FILE = "C:\\Users\\User\\Desktop\\output.csv";

    public static void main(String[] args) {
        exportTableToCSV("MidtermProject1", "dependency_materialities");
    }


    public static void exportTableToCSV(String databaseName, String tableName) {
        String sql = "SELECT * FROM " + tableName;
        try (
                Connection connection = JDBCUtils.getConnection(); // 獲取資料庫連線
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                FileWriter fileWriter = new FileWriter(OUTPUT_CSV_FILE);
                CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(resultSet)); // 動態生成標題
        ) {
            // ResultSet，逐行寫入 CSV
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                csvPrinter.printRecord(rowData);
            }
            System.out.println("資料表已成功匯出為 CSV 檔案：" + OUTPUT_CSV_FILE);
        } catch (SQLException e) {
            System.err.println("SQL 執行錯誤：" + e.getMessage());
        } catch (IOException e) {
            System.err.println("CSV 檔案寫入錯誤：" + e.getMessage());
        }
    }
}