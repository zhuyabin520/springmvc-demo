package com.qf.admin.pojo.po;

public class UserInfo {
    private int id;
    private String uname;
    private int uage;

    public UserInfo() {
    }

    public UserInfo(String uname, int uage) {
        this.uname = uname;
        this.uage = uage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getUage() {
        return uage;
    }

    public void setUage(int uage) {
        this.uage = uage;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", uage=" + uage +
                '}';
    }
}
