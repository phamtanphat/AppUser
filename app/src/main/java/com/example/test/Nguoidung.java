package com.example.test;

public class Nguoidung {
    private int id;
    private String username;
    private String password;
    private long birthday;
    private int gender;
    private String hobbies;

    public Nguoidung(int id, String username, String password, long birthday, int gender, String hobbies) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.hobbies = hobbies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}
