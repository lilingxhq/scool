package com.man.entity;

import java.util.Date;
import java.util.List;

public class RevertDiscussion {
    private Integer id;

    private String revert;

    private String revertContent;


    private Integer discuId;

    private Integer userId;

    private Date adddate;

    private Date editdate;

    private Integer parentId;

    private Integer targetUser;

    public Integer getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Integer targetUser) {
        this.targetUser = targetUser;
    }

    private List<RevertDiscussion> children;

    public String getRevertContent() {
        return revertContent;
    }

    public void setRevertContent(String revertContent) {
        this.revertContent = revertContent;
    }

    public List<RevertDiscussion> getChildren() {
        return children;
    }

    public void setChildren(List<RevertDiscussion> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRevert() {
        return revert;
    }

    public void setRevert(String revert) {
        this.revert = revert == null ? null : revert.trim();
    }

    public Integer getDiscuId() {
        return discuId;
    }

    public void setDiscuId(Integer discuId) {
        this.discuId = discuId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}