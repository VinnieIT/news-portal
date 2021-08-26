package Dao;

import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    @Override
    public void add(User user) {
        String sqlquery = "INSERT INTO users (name,position,role,department) VALUES (:name,:position,:role,:department);";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sqlquery,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex){
            System.out.println("User not added: "+ex);
        }

    }

    @Override
    public User findById(int id) {
        String sqlquery = "SELECT * from users WHERE id=:id;";
        try (Connection conn = DB.sql2o.open()) {
            return conn.createQuery(sqlquery)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }

    }

    @Override
    public List<User> allUsers() {
        String sqlquery = "SELECT * from users;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sqlquery)
                    .executeAndFetch(User.class);
        }

    }

    @Override
    public List<News> myNews(int userId) {
        List<News> newsList = new ArrayList<>();
        String joinSql = "SELECT newsid from departments_news WHERE userid = :userid;";
        try (Connection con = DB.sql2o.open()) {
            List<Integer> allIds = con.createQuery(joinSql).addParameter("userid",userId)
                    .executeAndFetch(Integer.class);

            String getSql = "SELECT * FROM news WHERE id = :id;";
            for(int id:allIds){
                newsList.add(
                        con.createQuery(getSql).addParameter("id",id).executeAndFetchFirst(News.class)
                );
            }
        }

        return newsList;


    }

    @Override
    public void deleteById(int id) {
        String sqlquery = "DELETE from users where id = :id;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sqlquery).addParameter("id",id).executeUpdate();
        }

    }

    @Override
    public void deleteAll() {
            String sqlquery = "DELETE from users;";
            try (Connection con = DB.sql2o.open()) {
                con.createQuery(sqlquery).executeUpdate();
            }

    }
}
