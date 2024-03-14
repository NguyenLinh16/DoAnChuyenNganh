package com.example.learnenglish.model;

import java.io.Serializable;

public class LuyenNghe implements Serializable {
    private String Title;
    private int File;

    public LuyenNghe(String title, int file) {
        Title = title;
        File = file;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }
}
