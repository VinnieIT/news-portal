package Dao;

import models.Department;
import models.News;
import models.User;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("ConstantConditions")
class Sql2oNewsDaoTest {
        private Sql2oDeptDao deptDao = new Sql2oDeptDao();
        private static Sql2oNewsDao newsDao = new Sql2oNewsDao();
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

    private News altNews(){
            News news = new News("New Direction","The IT department is dead");
            news.setAuthor(newUser().getUsername());
            newsDao.add(news);
            return news;
        }
        private News altNews2(){
            News news = new News("New DirectionAgain","The it department will arise");
            news.setType("Technology");
            news.setAuthor(newUser2().getUsername());
            newsDao.add(news);
            return news;
        }

        private Department newDept(){
            Department department = new Department("Technology Assuarance","We are working on it");
            deptDao.add(department);
            return department;
        }

        private User newUser(){
            User user = new User("Mwangi Vin","Manager","IT","Technology Assuarance");
            userDao.add(user);
            return user;
        }

        private User newUser2(){
            User user = new User("Mwangi Vin","Manager","IT","Technology Assuarance");
            userDao.add(user);
            return user;
        }

        @Test
        public void newsGetsSavedToDb(){
            News news = altNews();
            assertNotEquals(0,news.getId());
        }

        @Test
        public void findNewsById(){
            News news = altNews();
            News news2 = altNews2();
            assertTrue(news.equals(newsDao.findById(news.getId())));
        }


        @Test
        public void getDepartmentAfterCrosscheck_String(){
            Department department = newDept();
            News news2 = altNews2();
            assertEquals("Technology",newsDao.findById(news2.getId()).getType());
        }

        @Test
        public void findAllNewsPosts_int(){
            News news = altNews();
            News news2 = altNews2();
            assertEquals(2,newsDao.allNews().size());
        }

        @Test
        public void simpleDeleteNewsById_true(){
            News news = altNews();
            News news2 = altNews2();
            newsDao.deleteById(news.getId());
            assertEquals(1,newsDao.allNews().size());
        }

        @Test
        public void deleteAllNewsPosts(){
            News news = altNews();
            News news2 = altNews2();
            newsDao.deleteAll();
            assertEquals(0,newsDao.allNews().size());
        }

        @Test
        public void addNewsToGeneralDepartment(){
            User user = newUser();
            News news = altNews();
            newsDao.addNewsToDepartment(0,news.getId(),user.getId());
            assertEquals(1,deptDao.allDepartmentNews(0).size());
            assertEquals("General",deptDao.allDepartmentNews(0).get(0).getType());
        }

        @Test
        public void addNewsToSpecificDepartment(){
            Department department = newDept();
            User user = newUser();
            News news = altNews2();
            newsDao.addNewsToDepartment(department.getId(),news.getId(),user.getId());
            assertEquals(1,deptDao.allDepartmentNews(department.getId()).size());
        }

}