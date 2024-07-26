package com.fastcampus.customer.board;

import java.time.LocalDateTime;

public class BoardDto {
    private int bno;
    private String title;
    private String content;
    private String writer;
    private int view_cnt;
    private int comment_cnt;
    private LocalDateTime reg_date;

    BoardDto(String title, String content, String writer){
        bno=bno+1;
        this.title=title;
        this.content=content;
        this.writer=writer;
        view_cnt=0;
        comment_cnt=0;
        reg_date=LocalDateTime.now();
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public LocalDateTime getReq_date() {
        return reg_date;
    }

    public void setReq_date(LocalDateTime reg_date) {
        this.reg_date = reg_date;
    }
}
