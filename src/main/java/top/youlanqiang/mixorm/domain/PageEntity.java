package top.youlanqiang.mixorm.domain;

import java.util.List;

/**
 * 分页对象
 * @author youlanqiang
 */
public interface PageEntity<T> {

    /**
     * 返回当前查询条数
     * @return 查询条数
     */
    Integer getTotal();

    /**
     * 返回当前页
     * @return 当前页
     */
    Integer getCurrent();

    /**
     * 返回总数量
     * @return 总数量
     */
    Integer getSize();

    /**
     * 返回对象列表
     * @return list
     */
    List<T> getList();

    /**
     * 设置查询条数
     * @param total 查询条数
     */
    void setTotal(Integer total);

    /**
     * 设置当前页码
     * @param current 当前页
     */
    void setCurrent(Integer current);

    /**
     * 设置总数量
     * @param size size
     */
    void setSize(Integer size);

    /**
     * 设置对象列表
     * @param list 对象列表
     */
    void setList(List<T> list);

}
