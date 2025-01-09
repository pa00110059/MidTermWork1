package Demo;

import Modal.DependencyMaterialities;
import Utils.JDBCUtils;
import dao.DependencyMaterialitiesDao;

import java.sql.SQLException;

public class DemoConnection {
    public static void main(String[] args) throws SQLException {
        DependencyMaterialities dependencyMaterialities1 = new DependencyMaterialities("123","456","789","123456789");
        DependencyMaterialities dependencyMaterialities2 = new DependencyMaterialities("123","123456","789","123456789");
        DependencyMaterialities dependencyMaterialities3 = new DependencyMaterialities("456","123456","789","123456789");
        DependencyMaterialities dependencyMaterialities4 = new DependencyMaterialities("789","12345678","789","123456789");


        DependencyMaterialitiesDao dependencyMaterialitiesDao = new DependencyMaterialitiesDao();
        ////添加項目
        dependencyMaterialitiesDao.addDependencyMaterialities(dependencyMaterialities1);
        dependencyMaterialitiesDao.addDependencyMaterialities(dependencyMaterialities2);
        dependencyMaterialitiesDao.addDependencyMaterialities(dependencyMaterialities3);
        dependencyMaterialitiesDao.addDependencyMaterialities(dependencyMaterialities4);


        //依process刪除相同process的項目
        //dependencyMaterialitiesDao.deleteAllDependencyMaterialitiesByProcess("123");

        //更新已添加過的項目
        //dependencyMaterialitiesDao.updateDependencyMaterialities(dependencyMaterialities4,"456","123456");

        //依照process及Ecosystem_Service搜尋項目
        //dependencyMaterialitiesDao.findDependencyMaterialities("Airport services","Climate regulation");


    }
}
