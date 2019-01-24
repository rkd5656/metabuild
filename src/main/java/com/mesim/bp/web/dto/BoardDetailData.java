package com.mesim.bp.web.dto;

/**
 * Created by MisunKim
 * Date: 2019-01-24 오후 2:08
 */
public class BoardDetailData {
    int id;
    String content;
    String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
