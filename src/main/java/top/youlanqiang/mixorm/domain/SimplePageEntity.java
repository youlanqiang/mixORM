package top.youlanqiang.mixorm.domain;

import java.util.List;

public class SimplePageEntity<T> implements PageEntity<T>{

    private Integer total;

    private Integer current;

    private Integer size;

    private List<T> list;

    public SimplePageEntity(){}

    public SimplePageEntity(Integer current, Integer size){
        this.current = current;
        this.size = size;
    }

    @Override
    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public void setCurrent(Integer current) {
        this.current = current;
    }

    @Override
    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
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
