package com.w2a.Api_Automation.POJO;

public class User {

    private String name;
    private  String job;

    public User(String name,String job) {
        this.name = name;
        this.job = job;
    }

    public String getEmail() {
        return name;
    }

    public void setEmail(String email) {
        this.name = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
