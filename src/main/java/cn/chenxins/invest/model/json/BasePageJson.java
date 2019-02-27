package cn.chenxins.invest.model.json;


public class BasePageJson {

    private Integer page;

    private Integer perPage;

    private  Integer total;

    public BasePageJson(Integer page, Integer perPage, Integer total) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
