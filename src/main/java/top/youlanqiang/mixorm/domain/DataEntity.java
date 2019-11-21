package top.youlanqiang.mixorm.domain;

public interface DataEntity<T> {

    T selectById();

    PageEntity<T> selectPage(int current, int size);

    boolean updateById(T entity);

    boolean insertOne(T entity);

    boolean deleteById(Object id);

}
