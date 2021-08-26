package Dao;

import models.User;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oUserDaoTest {

    private static Sql2oUserDao userDao = new Sql2oUserDao();

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


    private User newUser(){
        User user = new User("Vincent Mwangi","Manager","Cleaning","Clean");
        userDao.add(user);
        return user;
    }
    private User newUser2(){
        User user = new User("Mwangi Vin","IT","Network","Tech");
        userDao.add(user);
        return user;
    }

    @Test
    public void userSavedToDatabase(){
        User user = newUser();
        assertNotEquals(0,user.getId());
    }

    @Test
    public void findingParticularUser(){
        User user = newUser();
        User user2 = newUser2();
        User foundUser = userDao.findById(user.getId());
        assertTrue(user.equals(foundUser));
    }

    @Test
    public void getAllUsers(){
        User user = newUser();
        User user2 = newUser2();
        assertTrue(userDao.allUsers().contains(user));
        assertTrue(userDao.allUsers().contains(user2));
    }

    @Test
    public void deleteUserFromDatabase_int(){
        User user = newUser();
        User user2 = newUser2();
        userDao.deleteById(user.getId());
        assertEquals(1, userDao.allUsers().size());
    }

    @Test
    public void deleteAllUsers(){
        User user = newUser();
        User user2 = newUser2();
        userDao.deleteAll();
        assertEquals(0, userDao.allUsers().size());
    }
}