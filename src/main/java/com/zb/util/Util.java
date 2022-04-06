package com.zb.util;




import java.util.List;

public class Util<T> {
    //5个属性
    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int pageCount;
    private List<T> list;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if(pageIndex<=1){
            this.pageIndex=1;
        }else if(pageIndex>=this.pageCount){
            this.pageIndex=this.pageCount;
        }else{
            this.pageIndex = pageIndex;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if(this.totalCount%this.pageSize==0){
            this.pageCount=this.totalCount/this.pageSize;
        }else{
            this.pageCount=this.totalCount/this.pageSize+1;
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
