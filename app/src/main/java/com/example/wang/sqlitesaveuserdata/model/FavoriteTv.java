package com.example.wang.sqlitesaveuserdata.model;

/**
 * Created by Wang on 2/20/18.
 */

public class FavoriteTv {
    private int id = -1;
    private String name;
    private String email;
    private String favoriteTv;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavoriteTv() {
        return favoriteTv;
    }

    public void setFavoriteTv(String favoriteTv) {
        this.favoriteTv = favoriteTv;
    }

}
