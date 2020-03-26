package top.youlanqiang.mixorm;

import top.youlanqiang.mixorm.domain.PageEntity;
import top.youlanqiang.mixorm.mate.EntityMate;
import top.youlanqiang.mixorm.sql.ConditionSql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 该接口用来定义对象与数据库的基本操作
 * @author youlanqiang
 */
public interface DataEntity<T> {

    /**
     * 使用一个数据源
     * @param dataSource 数据源
     * @return dataEntity
     * @throws SQLException dataSource获取Connection异常
     */
    DataEntity<T> source(DataSource dataSource);

    /**
     * 单独使用一个数据库连接，在使用完后续方法后
     * 该连接会自动关闭
     * @param connection 一个连接
     * @return dataEntity对象
     * @throws SQLException Connection异常
     */
    DataEntity<T> use(Connection connection) ;



    /**
     * 插入一条记录
     * @param entity 实体对象
     * @return 插入成功记录数
     */
    Integer insert(T entity);


    /**
     * 批量插入记录
     * @param entity 实体对象
     * @return 插入成功记录数
     */
    Long insertBatch(List<T> entity);

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
    Integer deleteByCondition(ConditionSql sql);

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
    Integer update(T entity, ConditionSql sql);

    /**
     * 批量更新
     * @param entity 实体对象
     * @return 修改成功记录数
     */
    Long updateBatchById(List<T> entity);

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
    T selectOne(ConditionSql sql);


    /**
     * 根据条件构造器，查询记录数
     * @param sql 条件构造器
     * @return 记录数数量
     */
    Integer selectCount(ConditionSql sql);

    /**
     * 根据条件构造器，查询全部记录
     * @param sql 条件构造器
     * @return 实体集合
     */
    List<T> selectList(ConditionSql sql);

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
    PageEntity<T> selectPage(int current, int size, ConditionSql sql);



    /**
     * 直接执行sql语句
     * @param sql sql语句
     * @param params params字段
     * @return 0/1
     */
    Integer execute(String sql, List<Object> params);


    /**
     * 获取对象元数据
     * @return EntityMate
     */
    EntityMate<T> getEntityMate();

    /**
     * DataEntity将会在下一次操作中开启事物支持
     * @return dataEntity对象
     */
    DataEntity<T> transaction();

    /**
     * 下次操作是否支持事物
     * @return true/false
     */
    Boolean isOpenTransaction();

    /**
     * 关闭事物
     */
    void closeTransaction() ;

    /**
     * 自动关闭连接
     */
    DataEntity<T> autoClose(boolean auto);

    /**
     * 是否自动关闭连接
     * @return true/false
     */
    Boolean isAutoClose();

    /**
     * 关闭连接
     */
    void closeConn();

}
