package com.hwb.tg.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 何伟斌
 * @date 2021/1/4 23:38
 */

public class MyPage<T> {
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;
    //总记录数
    private long total;
    //总页数
    private int pages;
    //结果集(每页显示的数据)
    private List<T> list;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;

    public MyPage(List<T> list, int pageSize, int pageNum) {
        System.out.println(list);
        //开始位置
        int begin = (pageNum - 1) * pageSize;
        //结束位置
        int end = pageNum * pageSize;
        if (end > list.size())
            end = list.size();
        setPageNum(pageNum);
        setPageSize(pageSize);
        setTotal(list.size());
        if (list.size() != 0)
            setPages((int) Math.ceil(this.total / this.pageSize));
        else
            setPages(0);
        if (begin >= list.size() || begin < 0) {
            setSize(0);
            setList(new ArrayList<T>());
        }else{
            setSize(end - begin);
            setList(list.subList(begin,end));
        }
    }

    @Override
    public String toString() {
        return "MyPage{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", total=" + total +
                ", pages=" + pages +
                ", list=" + list +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
