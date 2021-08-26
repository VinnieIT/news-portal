package models;

import java.util.Objects;

public class User {
    private String username;
    private String user_position;
    private String user_role;
    private String user_department;
    private int id;
    public  User (String username, String user_position, String user_role, String user_department){
        this.username = username;
        this.user_position = user_position;
        this.user_role = user_role;
        this.user_department = user_department;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return Objects.equals(username, users.username) &&
                Objects.equals(user_position, users.user_position) &&
                Objects.equals(user_role, users.user_role) &&
                Objects.equals(user_department, users.user_department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, user_position, user_role, user_department);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_position() {
        return user_position;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_department() {
        return user_department;
    }

    public void setUser_department(String user_department) {
        this.user_department = user_department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
