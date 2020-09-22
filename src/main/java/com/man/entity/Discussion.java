package com.man.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.man.common.constant.CommonConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Discussion {
    private Integer id;

    private String title;

    private String content;
    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date adddate;
    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date editdate;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}