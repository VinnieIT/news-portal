package models;

import Dao.DB;
import Dao.DBTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User newUser(){
        return new User("Kanye West","Manager","Artist","Media");
    }


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


    @Test
    public void initializeCorrectly(){
        User user = newUser();
        assertTrue(user instanceof User);
    }

    @Test
    public void getFunctionsWorkWell(){
        User user = newUser();
        assertEquals(user.getUsername(),newUser().getUsername());
        assertEquals(user.getUser_position(),newUser().getUser_position());
        assertEquals(user.getUser_role(),newUser().getUser_role());
        assertEquals(user.getUser_department(),newUser().getUser_department());
    }
}