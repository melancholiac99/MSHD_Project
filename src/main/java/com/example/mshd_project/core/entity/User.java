package com.example.mshd_project.core.entity;

public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String status;
    private String registTime;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRegistTime() {
        return registTime;
    }
    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passwd='" + password + '\'' +
                ", status='" + status + '\'' +
                ", registTime='" + registTime + '\'' +
                '}';
    }
}


