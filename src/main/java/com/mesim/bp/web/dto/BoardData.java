package com.mesim.bp.web.dto;

/**
 * Created by MisunKim
 * Date: 2019-01-24 오후 2:07
 */
public class BoardData {
    int id;
    String title;
    String reg_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}
