package top.youlanqiang.mixorm.domain;

import java.util.List;

public interface PageEntity<T> {

    Integer getTotal();

    Integer getCurrent();

    Integer getSize();

    List<T> getList();

}
