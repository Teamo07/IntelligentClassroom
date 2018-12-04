package org.gec.model;

import java.util.List;

public class PageModel {

    private int start; //从第几条开始查询
    private int pageSize;  //每页显示的记录数
    private int total; //总记录数
    private List rows; //保存查询的结果

    public PageModel() {}

    public PageModel(int start, int pageSize) {
        this.start = start;
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
