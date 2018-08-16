package com.tiquay.hoctienganhfree.model;

public class NoiDungTruyen {
    private int id;
    private String viet;

    public NoiDungTruyen(int id, String viet) {
        this.id = id;
        this.viet = viet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViet() {
        return viet;
    }

    public void setViet(String viet) {
        this.viet = viet;
    }
}
