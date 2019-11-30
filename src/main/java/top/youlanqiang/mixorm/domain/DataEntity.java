package top.youlanqiang.mixorm.domain;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DataEntity<T> {

    DataEntity<T> use(Connection connection);

    /**
     * 插入一条记录
     * @param entity 实体对象
     * @return 插入成功记录数
     */
    int insert(T entity);

    /**
     * 根据ID删除
     * @param id 主键ID
     * @return 删除成功记录数
     */
    int deleteById(Object id);

    /**
     * 根据map删除对象
     * @param map 字段map对象
     * @return 删除成功记录数
     */
    int deleteByMap(Map<String, Object> map);

    /**
     * 根据条件构造器删除
     * @param sql 条件构造器
     * @return 删除成功记录数
     */
    int deleteByCondition(ConditionSqlGenerator sql);

    /**
     * 删除，根据ID批量删除
     * @param idList 主键ID列表
     * @return 删除成功记录数
     */
    int deleteBatchIds(Collection<Object> idList);

    /**
     * 根据ID修改
     * @param entity 实体对象
     * @return 修改成功记录数
     */
    int updateById(T entity);

    /**
     * 根据条件构造器更新
     * @param entity 实体对象
     * @param sql 条件构造器
     * @return 修改成功记录数
     */
    int update(T entity, ConditionSqlGenerator sql);

    /**
     * 根据ID查询
     * @param id 主键ID
     * @return 实体
     */
    T selectById(Object id);

    /**
     *  查询根据map条件
     * @param map map条件
     * @return 实体集合
     */
    List<T> selectByMap(Map<String, Object> map);

    /**
     * 根据条件构造器查询一个
     * @param sql 条件构造器
     * @return 实体对象
     */
    T selectOne(ConditionSqlGenerator sql);


    /**
     * 根据条件构造器，查询记录数
     * @param sql 条件构造器
     * @return 记录数数量
     */
    Integer selectCount(ConditionSqlGenerator sql);

    /**
     * 根据条件构造器，查询全部记录
     * @param sql 条件构造器
     * @return 实体集合
     */
    List<T> selectList(ConditionSqlGenerator sql);

    /**
     * 查询一个分页
     * @param current 页数
     * @param size 项数
     * @return 分页结果集合
     */
    PageEntity<T> selectPage(int current, int size);

    /**
     * 根据条件构造器查询一个分页
     * @param current 页数
     * @param size 项数
     * @param sql 条件构造器
     * @return 分页结果集合
     */
    PageEntity<T> selectPage(int current, int size, ConditionSqlGenerator sql);

}
