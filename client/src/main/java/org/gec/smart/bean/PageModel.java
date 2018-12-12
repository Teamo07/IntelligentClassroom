package org.gec.smart.bean;

import java.util.List;

public class PageModel {

    private List data; // 数据
    private int curPage; // 当前第几页
    private int totalPage; // 总页数
    private int total; // 总的记录数
    private int pageSize; // 每页显示的记录数

    public PageModel() {
    }

    public PageModel(List data, int curPage, int totalPage, int total, int pageSize) {
        this.data = data;
        this.curPage = curPage;
        this.totalPage = totalPage;
        this.total = total;
        this.pageSize = pageSize;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
