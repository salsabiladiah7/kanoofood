package com.mafaa.kanoofood.admin_ui.user;

public class Users {
    private String name, level, gender, tgl, no_hp, email;

    public Users() {
    }

    public Users(String name, String level, String gender, String tgl, String no_hp, String email) {
        this.name = name;
        this.level = level;
        this.gender = gender;
        this.tgl = tgl;
        this.no_hp = no_hp;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
