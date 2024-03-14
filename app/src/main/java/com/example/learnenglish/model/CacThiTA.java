package com.example.learnenglish.model;

import java.io.Serializable;

public class CacThiTA implements Serializable {
    private String title;
    public String noidung;

    public CacThiTA(String title, String noidung) {
        this.title = title;
        this.noidung = noidung;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

}
