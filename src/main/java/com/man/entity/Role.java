package com.man.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.man.common.constant.CommonConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Role {
    private Integer roleid;

    private String rolename;

    private List<Integer> menus;

    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date adddate;

    @JSONField(format = CommonConstants.DATE_PATTERN)
    @DateTimeFormat(pattern=CommonConstants.DATE_PATTERN)
    private Date editdate;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
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

    public List<Integer> getMenus() {
        return menus;
    }

    public void setMenus(List<Integer> menus) {
        this.menus = menus;
    }
}