package top.youlanqiang.mixorm;

import top.youlanqiang.mixorm.annotation.IdType;
import top.youlanqiang.mixorm.domain.DataEntity;
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
 * @author youlanqiang
 */
class BaseDataEntity<T> implements DataEntity<T> {

    private final QueryMapper<T> queryMapper;

    private final EntityMate<T> entityMate;

    private Connection connection;

    private DataSource dataSource;

    private DataBase dataBase;

    public BaseDataEntity(Class<T> clazz) {
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
        Map<String, Object> variable = entityMate.getVariableSkipNull(entity);
        InsertSqlGenerator sqlGenerator = InsertSqlGenerator.create(dataBase)
                .insertInto(entityMate.getTableName())
                .fields(new ArrayList<>(variable.keySet())).values(variable.values());

        //判断是否存在自增主键
        boolean hasGeneratedKey = false;
        if(entityMate.isHasId() && entityMate.getIdEntity().getIdType() == IdType.INCREMENT){
            hasGeneratedKey = true;
        }

        QueryMapper.InsertResult result = queryMapper.insert(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams(), hasGeneratedKey);
        return result.getCount();
    }

    @Override
    public Integer deleteById(Object id) {
        if (entityMate.isHasId()) {

            DeleteSqlGenerator sqlGenerator = DeleteSqlGenerator.create(dataBase)
                    .deleteForm(entityMate.getTableName())
                    .where(ConditionSqlGenerator.create(dataBase).eq(entityMate.getIdEntity().getColumnName(), id));
            return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());

        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }

    }

    @Override
    public Integer deleteByMap(Map<String, Object> map) {
        ConditionSqlGenerator condition = ConditionSqlGenerator.create(dataBase);
        setMapToConditionSql(map, condition);
        return deleteByCondition(condition);
    }

    @Override
    public Integer deleteBatchIds(List<Object> idList) {
        if (entityMate.isHasId()) {
            ConditionSqlGenerator condition = ConditionSqlGenerator.create(dataBase)
                    .in(entityMate.getIdEntity().getColumnName(), idList);
            return deleteByCondition(condition);
        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }
    }

    @Override
    public Integer deleteByCondition(ConditionSqlGenerator sql) {
        DeleteSqlGenerator sqlGenerator = DeleteSqlGenerator.create(dataBase)
                .deleteForm(entityMate.getTableName()).where(sql);
        return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }


    @Override
    public Integer updateById(T entity) {
        if (entityMate.isHasId()) {
            UpdateSqlGenerator sqlGenerator = UpdateSqlGenerator.create(dataBase)
                    .update(entityMate.getTableName());
            Map<String, Object> variables = entityMate.getVariableSkipNull(entity);
            variables.forEach(sqlGenerator::set);
            return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }

    }

    @Override
    public Integer update(T entity, ConditionSqlGenerator sql) {
        UpdateSqlGenerator sqlGenerator = UpdateSqlGenerator.create(dataBase)
                .update(entityMate.getTableName());
        Map<String, Object> variables = entityMate.getVariableSkipNull(entity);
        variables.forEach(sqlGenerator::set);
        sqlGenerator.where(sql);
        return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }

    @Override
    public T selectById(Object id) {
        if (entityMate.isHasId()) {
            ConditionSqlGenerator condition = ConditionSqlGenerator.create(dataBase).eq(entityMate.getIdEntity().getColumnName(), id);
            return selectOne(condition);
        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }
    }

    @Override
    public T selectOne(ConditionSqlGenerator sql) {
        SelectSqlGenerator sqlGenerator = SelectSqlGenerator.create(dataBase)
                .select(entityMate.getFields().keySet())
                .from(entityMate.getTableName())
                .where(sql);
        return queryMapper.queryToSingle(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }

    @Override
    public Integer selectCount(ConditionSqlGenerator sql) {
        SelectSqlGenerator sqlGenerator = SelectSqlGenerator.create(dataBase)
                .select(" COUNT(*) ")
                .from(entityMate.getTableName())
                .where(sql);
        return queryMapper.queryToInteger(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }

    @Override
    public List<T> selectByMap(Map<String, Object> map) {
        ConditionSqlGenerator condition = ConditionSqlGenerator.create(dataBase);
        setMapToConditionSql(map, condition);
        return selectList(condition);
    }


    @Override
    public List<T> selectList(ConditionSqlGenerator sql) {
        SelectSqlGenerator sqlGenerator = SelectSqlGenerator.create(dataBase)
                .select(entityMate.getFields().keySet())
                .from(entityMate.getTableName())
                .where(sql);
        return queryMapper.queryToList(getConnection(), sqlGenerator.getSql(), sqlGenerator.getParams());
    }

    @Override
    public PageEntity<T> selectPage(int current, int size) {
        PageEntity<T> pageEntity = new SimplePageEntity<>(current, size);

        Integer count = selectCount(null);
        pageEntity.setTotal(count);

        ConditionSqlGenerator condition = ConditionSqlGenerator.create(dataBase)
                .limit( (current - 1) * size, size);
        List<T> list = selectList(condition);
        pageEntity.setList(list);
        return pageEntity;
    }

    @Override
    public PageEntity<T> selectPage(int current, int size, ConditionSqlGenerator sql) {
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
    private void setMapToConditionSql(Map<String, Object> map ,final ConditionSqlGenerator condition){
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
