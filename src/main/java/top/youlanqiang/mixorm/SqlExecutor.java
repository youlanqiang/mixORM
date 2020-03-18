package top.youlanqiang.mixorm;

import top.youlanqiang.mixorm.annotation.IdType;
import top.youlanqiang.mixorm.domain.PageEntity;
import top.youlanqiang.mixorm.domain.SimplePageEntity;
import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.mate.EntityMate;
import top.youlanqiang.mixorm.mate.EntityMateContainer;
import top.youlanqiang.mixorm.sql.*;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * 此类用来执行sql语句，并封装结果对象
 * @author youlanqiang
 */
class SqlExecutor<T> implements DataEntity<T> {

    private final QueryMapper<T> queryMapper;

    private final EntityMate<T> entityMate;

    private Connection connection;

    private DataSource dataSource;

    private DataBase dataBase;

    public SqlExecutor(Class<T> clazz) {
        EntityMate<T> mate = EntityMateContainer.getInstance().get(clazz);
        this.queryMapper = new QueryMapper<>(mate);
        this.entityMate = queryMapper.getMate();
    }

    @Override
    public DataEntity<T> source(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        //获取数据库厂商名称
        try(Connection connection = dataSource.getConnection()){
            this.dataBase = DataBase.valueOf(connection.getMetaData().getDatabaseProductName());
        }
        return this;
    }


    @Override
    public DataEntity<T> use(Connection connection) throws SQLException {
        this.connection = connection;
        //获取数据库厂商名称
        this.dataBase = DataBase.valueOf(connection.getMetaData().getDatabaseProductName());
        return this;
    }

    @Override
    public Integer insert(T entity) {
        InsertSql sqlGenerator = InsertSql.create(dataBase)
                .insertInto(entityMate.getTableName());

        //判断是否存在自增主键

        QueryMapper.InsertResult result = null;

        if(entityMate.isHasId() && entityMate.getIdEntity().getIdType() == IdType.INCREMENT){
            //存在自增主键，则在插入字段中不包含主键

            Map<String, Object> variable = entityMate.getVariableSkipNullAndId(entity);
            sqlGenerator.fields(new ArrayList<>(variable.keySet())).values(new ArrayList<>(variable.values()));
            result = queryMapper.insert(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams(),
                    true, entityMate.getIdEntity().getColumnType());

            entityMate.autowiredKey(result.getKey(), entity);
        }else{

            Map<String, Object> variable = entityMate.getVariableSkipNull(entity);
            sqlGenerator.fields(new ArrayList<>(variable.keySet())).values(new ArrayList<>(variable.values()));
            result = queryMapper.insert(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams(), false, null);

        }

        return result.getCount();
    }

    @Override
    public Integer insertBatch(List<T> entity) {
        //批量插入记录
        return null;
    }

    @Override
    public Integer deleteById(Object id) {
        if (entityMate.isHasId()) {

            DeleteSql sqlGenerator = DeleteSql.create(dataBase)
                    .deleteForm(entityMate.getTableName())
                    .where(ConditionSql.create(dataBase).eq(entityMate.getIdEntity().getColumnName(), id));
            return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());

        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }

    }

    @Override
    public Integer deleteByMap(Map<String, Object> map) {
        ConditionSql condition = ConditionSql.create(dataBase);
        setMapToConditionSql(map, condition);
        return deleteByCondition(condition);
    }

    @Override
    public Integer deleteBatchIds(List<Object> idList) {
        if (entityMate.isHasId()) {
            ConditionSql condition = ConditionSql.create(dataBase)
                    .in(entityMate.getIdEntity().getColumnName(), idList);
            return deleteByCondition(condition);
        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }
    }

    @Override
    public Integer deleteByCondition(ConditionSql sql) {
        DeleteSql sqlGenerator = DeleteSql.create(dataBase)
                .deleteForm(entityMate.getTableName()).where(sql);
        return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }


    @Override
    public Integer updateById(T entity) {
        if (entityMate.isHasId()) {
            UpdateSql sqlGenerator = UpdateSql.create(dataBase)
                    .update(entityMate.getTableName());

            ConditionSql conditionSql = ConditionSql.create(dataBase);
            conditionSql.eq(entityMate.getIdEntity().getColumnName(), entityMate.getPrimaryKeyValue(entity));

            Map<String, Object> variables = entityMate.getVariableSkipNullAndId(entity);
            variables.forEach(sqlGenerator::set);

            sqlGenerator.where(conditionSql);
            return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }

    }

    @Override
    public Integer update(T entity, ConditionSql sql) {
        UpdateSql sqlGenerator = UpdateSql.create(dataBase)
                .update(entityMate.getTableName());
        Map<String, Object> variables = entityMate.getVariableSkipNull(entity);
        variables.forEach(sqlGenerator::set);
        sqlGenerator.where(sql);
        return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }

    @Override
    public Integer updateBatchById(List<T> entity) {
        //todo 批量更新entity
        return null;
    }

    @Override
    public T selectById(Object id) {
        if (entityMate.isHasId()) {
            ConditionSql condition = ConditionSql.create(dataBase).eq(entityMate.getIdEntity().getColumnName(), id);
            return selectOne(condition);
        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }
    }

    @Override
    public T selectOne(ConditionSql sql) {
        SelectSql sqlGenerator = SelectSql.create(dataBase)
                .select(entityMate.getFields().keySet())
                .from(entityMate.getTableName())
                .where(sql);
        return queryMapper.queryToSingle(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }

    @Override
    public Integer selectCount(ConditionSql sql) {
        SelectSql sqlGenerator = SelectSql.create(dataBase)
                .select(" COUNT(*) ")
                .from(entityMate.getTableName())
                .where(sql);
        return queryMapper.queryToInteger(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }

    @Override
    public List<T> selectByMap(Map<String, Object> map) {
        ConditionSql condition = ConditionSql.create(dataBase);
        setMapToConditionSql(map, condition);
        return selectList(condition);
    }


    @Override
    public List<T> selectList(ConditionSql sql) {
        SelectSql sqlGenerator;
        if(sql != null){
            sqlGenerator = SelectSql.create(dataBase)
                    .select(entityMate.getFields().keySet())
                    .from(entityMate.getTableName())
                    .where(sql);
        }else{
            sqlGenerator =  SelectSql.create(dataBase)
                    .select(entityMate.getFields().keySet())
                    .from(entityMate.getTableName());
        }
        return queryMapper.queryToList(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }

    @Override
    public PageEntity<T> selectPage(int current, int size) {
        PageEntity<T> pageEntity = new SimplePageEntity<>(current, size);

        Integer count = selectCount(null);
        pageEntity.setTotal(count);

        ConditionSql condition = ConditionSql.create(dataBase)
                .limit( (current - 1) * size, size);
        List<T> list = selectList(condition);
        pageEntity.setList(list);
        return pageEntity;
    }

    @Override
    public PageEntity<T> selectPage(int current, int size, ConditionSql sql) {
        PageEntity<T> pageEntity = new SimplePageEntity<>(current, size);

        Integer count = selectCount(sql);
        pageEntity.setTotal(count);

        if(sql == null){
            throw new SqlGeneratorException();
        }
        sql.limit((current - 1) * size, size);
        List<T> list = selectList(sql);
        pageEntity.setList(list);
        return pageEntity;
    }



    /**
     * 这段代码的逻辑是保证 SqlGenerator，
     * 最后生成的 sql 结尾不会带 and.
     * @param map 条件map
     * @param condition ConditionSqlGenerator
     */
    private void setMapToConditionSql(Map<String, Object> map ,final ConditionSql condition){
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            condition.eq(key, map.get(key));
            if (iterator.hasNext()) {
                condition.and();
            }
        }
    }


    private Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        if (dataSource != null) {
            try {
                return dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException("DB Connection has had Exception");
            }
        }
        throw new RuntimeException("DB Connection has had Exception");
    }
}
