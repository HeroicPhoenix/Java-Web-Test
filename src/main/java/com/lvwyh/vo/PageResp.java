// PageResp.java  通用分页
package com.lvwyh.vo;

import java.util.List;

public class PageResp<T> {
    private List<T> list;
    private long total;

    public PageResp() {}
    public PageResp(List<T> list, long total) { this.list = list; this.total = total; }

    public List<T> getList() { return list; }
    public void setList(List<T> list) { this.list = list; }
    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }
}
