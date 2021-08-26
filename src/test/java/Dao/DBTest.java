package Dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

public class DBTest extends ExternalResource {
    @Override
    protected void before(){
        DB.sql2o =  new Sql2o("jdbc:postgresql://localhost:5432/user_news_portal_test","postgres","hello");

    }
    @Override
    protected void after(){
        try(Connection conn = DB.sql2o.open()) {
            String deleteNewsQuery = "DELETE FROM news;";
            String deleteDepartmentQuery = "DELETE FROM departments;";
            String deleteUsersQuery = "DELETE FROM users;";
            String deleteDept_Users = "DELETE FROM department_news;";
            String deleteDept_News = "DELETE FROM department_news;";

            conn.createQuery(deleteNewsQuery).executeUpdate();
            conn.createQuery(deleteDepartmentQuery).executeUpdate();
            conn.createQuery(deleteUsersQuery).executeUpdate();
            conn.createQuery(deleteDept_Users).executeUpdate();
            conn.createQuery(deleteDept_News).executeUpdate();


        }


    }


}