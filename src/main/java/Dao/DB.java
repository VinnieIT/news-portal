package Dao;

import org.sql2o.Sql2o;

public class DB {
    //public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/user_news_portal", "postgres", "hello");

    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-44-197-40-76.compute-1.amazonaws.com:5432/d9c8o6tnsv6c2a", "emhgtpgwjaigop", "322724fea6a9296fd8f65caaf0364145be0ec563acce6797a12a28cb46246607");

}
