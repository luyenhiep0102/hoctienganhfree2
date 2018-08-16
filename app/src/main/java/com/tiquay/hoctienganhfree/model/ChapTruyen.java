package com.tiquay.hoctienganhfree.model;

public class ChapTruyen {
    private int maChap;
    private String TenChapTV;
    private int hinh;


    public ChapTruyen(int maChap, String tenChapTV, int hinh) {
        this.maChap = maChap;
        TenChapTV = tenChapTV;
        this.hinh = hinh;
    }

    public int getMaChap() {
        return maChap;
    }

    public void setMaChap(int maChap) {
        this.maChap = maChap;
    }

    public String getTenChapTV() {
        return TenChapTV;
    }

    public void setTenChapTV(String tenChapTV) {
        TenChapTV = tenChapTV;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
