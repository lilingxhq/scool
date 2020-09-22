package com.man.common;

import java.io.Serializable;
import java.util.List;

public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    /**总记录数**/
    private int totalCount;
    /**每页显示记录数**/
    private int pageSize;
    /**总页数**/
    private int totalPage;
    /**当前页*/
    private int currPage;
    /**数据列表**/
    private List<?> list;

    /**
     * 构造方法 构造相关实体
     * @param list          数据
     * @param totalCount    总条数
     * @param pageSize      每页显示条数
     * @param currPage      当前页
     */
    public PageUtils(List<?> list, int totalCount, int pageSize, int currPage){
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        // 自动计算出总页数
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
