package dao;

import Modal.DependencyMaterialities;
import Utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DependencyMaterialitiesDao {
    //添加項目
    public void addDependencyMaterialities(DependencyMaterialities dependencyMaterialities) throws SQLException {
        String sql = """
                INSERT INTO dependency_materialities(Process,
                    Ecosystem_Service,
                    Rating,
                    Justification)
                    Values(?,?,?,?);
                """;
        Connection connection = JDBCUtils.getHikariConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dependencyMaterialities.getProcess());
            preparedStatement.setString(2, dependencyMaterialities.getEcosystem_Service());
            preparedStatement.setString(3, dependencyMaterialities.getRating());
            preparedStatement.setString(4, dependencyMaterialities.getJustification());

            preparedStatement.execute();
            System.out.println("新增資料完成");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }

    }

    //刪除項目
    //依照項目的process來刪除
    public void deleteAllDependencyMaterialitiesByProcess(String process) {
        String sql = "DELETE FROM dependency_materialities WHERE process=?";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, process);

            preparedStatement.execute();
            System.out.println("已刪除process為:" + process + "的所有項目");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }
    }

    //依照項目的process及
    public void deleteDependencyMaterialitiesByProcessAndEcosystemService(String process,String ecosystemService) throws SQLException {
        String sql = "DELETE FROM dependency_materialities WHERE process=? AND Ecosystem_Service = ?";
        Connection connection = JDBCUtils.getHikariConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, process);
            preparedStatement.setString(2, ecosystemService);

            preparedStatement.execute();
            System.out.println("process:" + process + ",Ecosystem_Service:" + ecosystemService + "\n已刪除該項目");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }
    }

    //更新項目
    //依照process及Ecosystem_Service尋找項目來更新

    public void updateDependencyMaterialities(DependencyMaterialities dependencyMaterialities, String process, String ecosystemService) throws SQLException {
        String sql = """
                        UPDATE dependency_materialities
                        SET process=?,Ecosystem_Service=?,Rating=?,Justification=?
                        WHERE process=? AND Ecosystem_Service=?;
                """;

        Connection connection = JDBCUtils.getHikariConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dependencyMaterialities.getProcess());
            preparedStatement.setString(2, dependencyMaterialities.getEcosystem_Service());
            preparedStatement.setString(3, dependencyMaterialities.getRating());
            preparedStatement.setString(4, dependencyMaterialities.getJustification());
            preparedStatement.setString(5, process);
            preparedStatement.setString(6, ecosystemService);

            preparedStatement.execute();
            System.out.println("原process:" + dependencyMaterialities.getProcess() + ",\n原Ecosystem_Service為:" + dependencyMaterialities.getEcosystem_Service() + "\n已更新process為:" + process + ",\nEcosystem_Service為:" + ecosystemService);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }
    }


    //查詢全部DependencyMaterialities
    public List<DependencyMaterialities> findAllDependencyMaterialities() throws SQLException {
        ArrayList dependencyMaterialsList = new ArrayList();
        String sql = "SELECT * FROM dependency_materialities";
        Connection connection = JDBCUtils.getHikariConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String process = resultSet.getString("Process");
                String ecosystemService= resultSet.getString("Ecosystem_Service");
                String rating = resultSet.getString("Rating");
                String justification = resultSet.getString("Justification");
                DependencyMaterialities dependencyMaterialities = new DependencyMaterialities(process,ecosystemService,rating,justification);
                dependencyMaterialsList.add(dependencyMaterialities);
            }
            for (Object dependencyMaterialities : dependencyMaterialsList) {
                System.out.println(dependencyMaterialities.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }
        return dependencyMaterialsList;
    }

    //依照process及ecosystemService尋找單筆DependencyMaterialities
    public DependencyMaterialities findDependencyMaterialities(String process,String ecosystemMaterialities) throws SQLException {
        String sql = "SELECT * FROM dependency_materialities WHERE process = ? AND Ecosystem_Service = ?";
        Connection connection = JDBCUtils.getHikariConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DependencyMaterialities dependencyMaterialities = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, process);
            preparedStatement.setString(2, ecosystemMaterialities);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String thisProcess = resultSet.getString("Process");
            String ecosystemService= resultSet.getString("Ecosystem_Service");
            String rating = resultSet.getString("Rating");
            String justification = resultSet.getString("Justification");
            dependencyMaterialities = new DependencyMaterialities(thisProcess,ecosystemService,rating,justification);
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }
        return dependencyMaterialities;
    }
}
