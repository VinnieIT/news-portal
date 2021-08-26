package Dao;

import models.Department;
import models.News;
import models.User;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("unused")

class Sql2oDeptDaoTest {
    private Sql2oDeptDao deptDao = new Sql2oDeptDao();
    private static Sql2oUserDao userDao = new Sql2oUserDao();
    private static Sql2oNewsDao newsDao = new Sql2oNewsDao();

    @BeforeEach
    void init() {
        DBTest databaseRule = new DBTest();

    }

    @AfterEach
    void cleanup() {
        try(Connection conn = DB.sql2o.open()) {
            String deleteNewsQuery = "DELETE FROM news;";
            String deleteDepartmentQuery = "DELETE FROM departments;";
            String deleteUsersQuery = "DELETE FROM users;";
            String deleteDept_Users = "DELETE FROM departments_news;";
            String deleteDept_News = "DELETE FROM departments_users;";

            conn.createQuery(deleteNewsQuery).executeUpdate();
            conn.createQuery(deleteDepartmentQuery).executeUpdate();
            conn.createQuery(deleteUsersQuery).executeUpdate();
            conn.createQuery(deleteDept_Users).executeUpdate();
            conn.createQuery(deleteDept_News).executeUpdate();


        }
    }

    private Department newDept() {
        Department department = new Department("Technology Assuarance", "We evaluate the impact of tech in your organisation");
        deptDao.add(department);
        return department;
    }
    private Department secondDept(){
        Department department = new Department("Technology Audit","Check the it tech used in a company");
        deptDao.add(department);
        return department;
    }

    private User newUser(){
        User user = new User("Vinchez","Developer","Backend","Technology Assuarance");
        userDao.add(user);
        return user;
    }

    private User newUser2(){
        User user = new User("Francis","Developer","frontend","Technology Audit");
        userDao.add(user);
        return user;
    }
    private News news(){
        News news1 = new News("Take all Bugs", "Please make sure that all the Bugs are done");
        newsDao.add(news1);
        return  news1;
    }


    @Test
    void addUserToDept() {
        Department department = newDept();
        User user = newUser();
        deptDao.addUserToDept(department,user);
        assertEquals("Technology Assuarance",user.getUser_department());
    }

    @Test
    void findById() {
        Department department = newDept();
        assertEquals(0, department.getId());


    }
    @Test
    void departmentaddedtodb(){
        Department department = newDept();
        assertNotEquals(0, department.getId());

    }
    @Test
    void getDepartmentbyID(){
        Department department = newDept();
        System.out.println(deptDao.allDepartments());

        assertTrue(deptDao.allDepartments().contains(department));

    }

    @Test
    void allDepartmentEmployees() {
        Department department = newDept();
        User user = newUser();
        User user2 = newUser2();
        deptDao.addUserToDept(department,user);
        deptDao.addUserToDept(department,user2);
        int posOneId = deptDao.allDepartmentEmployees(department.getId()).get(0).getId();
        assertEquals(user.getId(),posOneId);
    }


    @Test
    void deleteDepartmentById() {
        Department department = newDept();
        Department department2= secondDept();
        deptDao.deleteDepartmentById(department.getId());
        assertEquals(1,deptDao.allDepartments().size());
    }

    @Test
    void deleteEmployeeFromDept() {
        Department department = newDept();
        User user = newUser();
        User user2 = newUser2();
        deptDao.addUserToDept(department,user);
        deptDao.addUserToDept(department,user2);

        deptDao.deleteEmployeeFromDept(department,user);
        assertEquals(1,department.getDept_totalEmployees());
        assertEquals("None",user.getUser_department());
    }


    @Test
    void updateEmployeeCount() {
        Department department = newDept();
        deptDao.add(department);
        deptDao.updateEmployeeCount(department);
        int posOneId = deptDao.allDepartmentEmployees(department.getId()).get(0).getId();
        Assertions.assertEquals(1, posOneId);



    }
}