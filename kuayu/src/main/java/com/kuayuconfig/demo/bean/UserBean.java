package com.kuayuconfig.demo.bean;

public class UserBean {

    private int id;
    private String name;
    private int age;
    private String email;

    public UserBean() {
    }

    public UserBean(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String userEmail) {
        this.email = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:" + name  +
                ", age:" + age +
                ", email:" + email + "}";
    }
}
