package com.man.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.man.common.constant.CommonConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 课程
 */
public class Lesson {
    private Integer lessonId;

    private String name;

    private Integer lessonTeacher;

    private String teacherName;

    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date adddate;

    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date editdate;

    private String introduce;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLessonTeacher() {
        return lessonTeacher;
    }

    public void setLessonTeacher(Integer lessonTeacher) {
        this.lessonTeacher = lessonTeacher;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}