package top.youlanqiang.mixorm.domain;

import java.util.List;

public interface PageEntity<T> {

    Integer getTotal();

    Integer getCurrent();

    Integer getSize();

    List<T> getList();

    void setTotal(Integer total);

    void setCurrent(Integer current);

    void setSize(Integer size);

    void setList(List<T> list);

}
