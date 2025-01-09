package Demo;

import Modal.DependencyMaterialities;
import dao.DependencyMaterialitiesDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DemoScanner {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DependencyMaterialitiesDao dao = new DependencyMaterialitiesDao();

        while (true) {
            System.out.println("""
                    請輸入你想執行的操作：
                    1. 增加 (Create)
                    2. 刪除 (Delete)
                    3. 修改 (Update)
                    4. 查詢 (Read)
                    5. 結束程序 (Exit)
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine(); // 吸收換行符

            switch (choice) {
                case 1 -> { // 增加
                    System.out.println("請輸入以下資訊：");
                    System.out.print("Process: ");
                    String process = scanner.nextLine();

                    System.out.print("Ecosystem_Service: ");
                    String ecosystemService = scanner.nextLine();

                    System.out.print("Rating: ");
                    String rating = scanner.nextLine();

                    System.out.print("Justification: ");
                    String justification = scanner.nextLine();

                    DependencyMaterialities newItem = new DependencyMaterialities(process, ecosystemService, rating, justification);
                    dao.addDependencyMaterialities(newItem);
                }

                case 2 -> { // 刪除
                    System.out.println("""
                            刪除方式：
                            1. 刪除該Process全部資料
                            2. 根據 Process 和 Ecosystem_Service 刪除單筆資料
                            """);
                    int readDeleteChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (readDeleteChoice == 1) {
                        System.out.print("請輸入 Process: ");
                        String allDeleteProcess = scanner.nextLine();
                        dao.deleteAllDependencyMaterialitiesByProcess(allDeleteProcess);
                    } else if (readDeleteChoice == 2) {
                        System.out.print("請輸入 Process: ");
                        String deleteProcess = scanner.nextLine();

                        System.out.print("請輸入 Ecosystem_Service: ");
                        String deleteEcosystemService = scanner.nextLine();
                        dao.deleteDependencyMaterialitiesByProcessAndEcosystemService(deleteProcess, deleteEcosystemService);
                    }else {
                        System.out.println("無效的選項！");
                    }
                }
                case 3 -> { // 修改
                    System.out.println("請輸入以下資訊：");
                    System.out.print("要修改的 Process: ");
                    String oldProcess = scanner.nextLine();

                    System.out.print("要修改的 Ecosystem_Service: ");
                    String oldEcosystemService = scanner.nextLine();

                    System.out.print("新 Process: ");
                    String newProcess = scanner.nextLine();

                    System.out.print("新 Ecosystem_Service: ");
                    String newEcosystemService = scanner.nextLine();

                    System.out.print("新 Rating: ");
                    String newRating = scanner.nextLine();

                    System.out.print("新 Justification: ");
                    String newJustification = scanner.nextLine();

                    DependencyMaterialities updatedItem = new DependencyMaterialities(newProcess, newEcosystemService, newRating, newJustification);
                    dao.updateDependencyMaterialities(updatedItem, oldProcess, oldEcosystemService);
                }

                case 4 -> { // 查詢
                    System.out.println("""
                            查詢方式：
                            1. 查詢全部
                            2. 根據 Process 和 Ecosystem_Service 查詢單筆資料
                            """);
                    int readChoice = scanner.nextInt();
                    scanner.nextLine(); // 吸收換行符

                    if (readChoice == 1) {
                        List<DependencyMaterialities> list = dao.findAllDependencyMaterialities();
                    } else if (readChoice == 2) {
                        System.out.print("請輸入 Process: ");
                        String process = scanner.nextLine();

                        System.out.print("請輸入 Ecosystem_Service: ");
                        String ecosystemService = scanner.nextLine();

                        DependencyMaterialities item = dao.findDependencyMaterialities(process, ecosystemService);
                        if (item != null) {
                            System.out.println(item);
                        } else {
                            System.out.println("查無資料！");
                        }
                    } else {
                        System.out.println("無效的選項！");
                    }
                }

                case 5 -> { // 結束程序
                    System.out.println("程序結束！");
                    scanner.close();
                    System.exit(0);
                }

                default -> System.out.println("請重新輸入！");
            }
        }
    }
}
