package com.example.intentssqlite;

//This Java class represents a Template for TABLE USERS. Defined in Helper
public class User {

    Integer id_user;
    String name;
    String last_name;
    String dob;
    String role;
    Integer semester;
    String nickname;
    String password;

    public User(Integer id_user, String name, String last_name, String dob, String role, Integer semester, String nickname, String password) {
        this.id_user = id_user;
        this.name = name;
        this.last_name = last_name;
        this.dob = dob;
        this.role = role;
        this.semester = semester;
        this.nickname = nickname;
        this.password = password;
    }

    public User(String name, String last_name, String dob, String role, Integer semester, String nickname, String password) {
        this.name = name;
        this.last_name = last_name;
        this.dob = dob;
        this.role = role;
        this.semester = semester;
        this.nickname = nickname;
        this.password = password;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getDob() {
        return dob;
    }

    public String getRole() {
        return role;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
