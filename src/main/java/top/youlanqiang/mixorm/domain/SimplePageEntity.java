package top.youlanqiang.mixorm.domain;

import java.util.List;

public class SimplePageEntity<T> implements PageEntity{

    private Integer total;

    private Integer current;

    private Integer size;

    private List<T> list;

    public SimplePageEntity(){}

    public SimplePageEntity(Integer current, Integer size){
        this.current = current;
        this.size = size;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    @Override
    public Integer getCurrent() {
        return current;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public List<T> getList() {
        return list;
    }
}
