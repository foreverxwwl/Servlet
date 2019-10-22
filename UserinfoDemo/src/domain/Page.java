package domain;

import java.util.List;

/**
 * @outhor li
 * @create 2019-10-20 13:28
 * 分页展示的JavaBean
 */
public class Page<T> {
    private int totalCount;//总记录数
    private int totalPage;//总页码数
    private List<T> totalList;//页面数据集合
    private int currentPage;//当前页码
    private int rows;//每页展示条数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getTotalList() {
        return totalList;
    }

    public void setTotalList(List<T> totalList) {
        this.totalList = totalList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", totalList=" + totalList +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
