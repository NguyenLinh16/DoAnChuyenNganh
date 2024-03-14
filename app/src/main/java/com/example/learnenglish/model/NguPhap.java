package com.example.learnenglish.model;


public class NguPhap {
    private String titleNP;
    public String noidungNP;

    public NguPhap(String titleNP, String noidungNP) {
        this.titleNP = titleNP;
        this.noidungNP = noidungNP;
    }

    public String getTitleNP() {
        return titleNP;
    }

    public void setTitleNP(String titleNP) {
        this.titleNP = titleNP;
    }

    public String getNoidungNP() {
        return noidungNP;
    }

    public void setNoidungNP(String noidungNP) {
        this.noidungNP = noidungNP;
    }
}
