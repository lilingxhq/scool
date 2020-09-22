package com.man.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.man.common.constant.CommonConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Exam {
    private Integer id;

    private String title;

    private String descr;
    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date startTime;
    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date endTime;
    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date adddate;
    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date editdate;

    private String fileUrl;

    private Integer lessonId;

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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }
}