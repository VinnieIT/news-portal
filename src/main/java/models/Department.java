package models;

import java.util.Objects;

public class Department {
    private String dept_name;
    private String dept_description;
    private int dept_totalEmployees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public Department(String dept_name, String dept_description){
        this.dept_name= dept_name;
        this.dept_description = dept_description;
        this.dept_totalEmployees = 0;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department depts = (Department) o;
        return Objects.equals(dept_name, depts.dept_name) &&
                Objects.equals(dept_description, depts.dept_description) &&
                Objects.equals(dept_totalEmployees, depts.dept_totalEmployees) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dept_name, dept_description, dept_totalEmployees);
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_description() {
        return dept_description;
    }

    public void setDept_description(String dept_description) {
        this.dept_description = dept_description;
    }

    public int getDept_totalEmployees() {
        return dept_totalEmployees;
    }

    public void setDept_totalEmployees(int dept_totalEmployees) {
        this.dept_totalEmployees = dept_totalEmployees;
    }
    public void addTotalEmployees(){
        this.dept_totalEmployees += 1;
    }
    public void subtractTotalEmployees(){
        this.dept_totalEmployees -= 1;
    }
}
