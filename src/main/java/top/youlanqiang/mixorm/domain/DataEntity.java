package top.youlanqiang.mixorm.domain;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DataEntity<T> {

    /**
     * 使用一个数据源
     * @param dataSource 数据源
     * @return dataEntity
     */
    DataEntity<T> source(DataSource dataSource) throws SQLException;

    /**
     * 单独使用一个数据库连接，在使用完后续方法后
     * 该连接会自动关闭
     * @param connection 一个连接
     * @return dataEntity对象
     */
    DataEntity<T> use(Connection connection) throws SQLException;

    /**
     * 插入一条记录
     * @param entity 实体对象
     * @return 插入成功记录数
     */
    Integer insert(T entity);

    /**
     * 根据ID删除
     * @param id 主键ID
     * @return 删除成功记录数
     */
    Integer deleteById(Object id);

    /**
     * 根据map删除对象
     * @param map 字段map对象
     * @return 删除成功记录数
     */
    Integer deleteByMap(Map<String, Object> map);

    /**
     * 根据条件构造器删除
     * @param sql 条件构造器
     * @return 删除成功记录数
     */
    Integer deleteByCondition(ConditionSqlGenerator sql);

    /**
     * 删除，根据ID批量删除
     * @param idList 主键ID列表
     * @return 删除成功记录数
     */
    Integer deleteBatchIds(List<Object> idList);

    /**
     * 根据ID修改
     * @param entity 实体对象
     * @return 修改成功记录数
     */
    Integer updateById(T entity);

    /**
     * 根据条件构造器更新
     * @param entity 实体对象
     * @param sql 条件构造器
     * @return 修改成功记录数
     */
    Integer update(T entity, ConditionSqlGenerator sql);

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
