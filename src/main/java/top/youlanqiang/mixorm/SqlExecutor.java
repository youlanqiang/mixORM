package top.youlanqiang.mixorm;

import top.youlanqiang.mixorm.annotation.IdType;
import top.youlanqiang.mixorm.domain.BatchSqlEntity;
import top.youlanqiang.mixorm.domain.PageEntity;
import top.youlanqiang.mixorm.domain.SimplePageEntity;
import top.youlanqiang.mixorm.domain.SqlEntity;
import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.mate.EntityMate;
import top.youlanqiang.mixorm.mate.EntityMateContainer;
import top.youlanqiang.mixorm.sql.*;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 此类用来执行sql语句，并封装结果对象
 * @author youlanqiang
 */
class SqlExecutor<T> implements DataEntity<T> {

    private final QueryMapper<T> queryMapper;

    private final EntityMate<T> entityMate;

    private final AtomicBoolean isOpenTransaction = new AtomicBoolean(false);

    private final AtomicBoolean isAutoClose = new AtomicBoolean(true);

    private Connection connection;

    private DataSource dataSource;

    private DataBase dataBase;

    public SqlExecutor(Class<T> clazz) {
        EntityMate<T> mate = EntityMateContainer.getInstance().get(clazz);
        this.entityMate = mate;
        this.queryMapper = new QueryMapper<>(this);;
    }

    @Override
    public DataEntity<T> source(DataSource dataSource)  {
        this.dataSource = dataSource;
        //获取数据库厂商名称
        try(Connection connection = dataSource.getConnection()){
            this.dataBase = DataBase.valueOf(connection.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库类型错误.");
        }
        return this;
    }


    @Override
    public DataEntity<T> use(Connection connection)  {
        this.connection = connection;
        //获取数据库厂商名称
        try {
            this.dataBase = DataBase.valueOf(connection.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            throw new RuntimeException("获取数据类型错误.");
        }
        return this;
    }


    @Override
    public Integer insert(T entity) {
        InsertSql sqlGenerator = InsertSql.create(dataBase)
                .insertInto(entityMate.getTableName());

        //判断是否存在自增主键

        QueryMapper.InsertResult result = null;

        if(entityMate.hasId() && entityMate.getIdEntity().getIdType() == IdType.INCREMENT){
            //存在自增主键，则在插入字段中不包含主键

            Map<String, Object> variable = entityMate.getVariableSkipNullAndId(entity);
            sqlGenerator.fields(new ArrayList<>(variable.keySet())).values(new ArrayList<>(variable.values()));
            result = queryMapper.insert(getConnection(), sqlGenerator.getSqlEntity(),
                    true, entityMate.getIdEntity().getColumnType());

            entityMate.autowiredKey(result.getKey(), entity);
        }else{

            Map<String, Object> variable = entityMate.getVariableSkipNull(entity);
            sqlGenerator.fields(new ArrayList<>(variable.keySet())).values(new ArrayList<>(variable.values()));
            result = queryMapper.insert(getConnection(), sqlGenerator.getSqlEntity(), false, null);

        }

        return result.getCount();
    }

    @Override
    public Long insertBatch(List<T> entity) {
        //todo 批量插入记录
        InsertSql sqlGenerator = InsertSql.create(dataBase)
                .insertInto(entityMate.getTableName()).fields(new ArrayList<>(entityMate.getFields().keySet()));
        BatchSqlEntity  sqlEntity = new BatchSqlEntity();
        sqlEntity.setSql(sqlGenerator.getSql());

        List<List<Object>> params = new ArrayList<>();
        for (T t : entity) {
            List<Object> item = new ArrayList<>(entityMate.getVariableSkipId(t).values());
            params.add(item);
        }
        sqlEntity.setRows(params);
        return queryMapper.executeBatchToUpdate(getConnection(), sqlEntity);
    }

    @Override
    public Integer deleteById(Object id) {
        if (entityMate.hasId()) {

            DeleteSql sqlGenerator = DeleteSql.create(dataBase)
                    .deleteForm(entityMate.getTableName())
                    .where(ConditionSql.create(dataBase).eq(entityMate.getIdEntity().getColumnName(), id));
            return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSqlEntity());

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
        if (entityMate.hasId()) {
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
        return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSqlEntity());
    }


    @Override
    public Integer updateById(T entity) {
        if (entityMate.hasId()) {
            UpdateSql sqlGenerator = UpdateSql.create(dataBase)
                    .update(entityMate.getTableName());

            ConditionSql conditionSql = ConditionSql.create(dataBase);
            conditionSql.eq(entityMate.getIdEntity().getColumnName(), entityMate.getPrimaryKeyValue(entity));

            Map<String, Object> variables = entityMate.getVariableSkipId(entity);
            variables.forEach(sqlGenerator::set);

            sqlGenerator.where(conditionSql);
            return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSqlEntity());
        } else {
            throw new SqlGeneratorException("对象没有设置主键.");
        }

    }

    @Override
    public Long updateBatchById(List<T> entity) {
        if(entityMate.hasId()){
            UpdateSql sqlGenerator = UpdateSql.create(dataBase)
                    .update(entityMate.getTableName());
            Set<String> columns = entityMate.getFields().keySet();
            for (String column : columns) {
                sqlGenerator.set(column, null);
            }
            BatchSqlEntity sqlEntity = new BatchSqlEntity();
            sqlEntity.setSql(sqlGenerator.getSql());
            List<List<Object>> params = new ArrayList<>();
            for (T t : entity) {
                List<Object> item = new ArrayList<>(entityMate.getVariableSkipId(t).values());
                params.add(item);
            }
            sqlEntity.setRows(params);
            return queryMapper.executeBatchToUpdate(getConnection(), sqlEntity);
        }else{
            throw new SqlGeneratorException("对象没有设置主键.");
        }
    }

    @Override
    public Integer update(T entity, ConditionSql sql) {
        UpdateSql sqlGenerator = UpdateSql.create(dataBase)
                .update(entityMate.getTableName());
        Map<String, Object> variables = entityMate.getVariable(entity);
        variables.forEach(sqlGenerator::set);
        sqlGenerator.where(sql);
        return queryMapper.executeToUpdate(getConnection(), sqlGenerator.getSqlEntity());
    }


    @Override
    public T selectById(Object id) {
        if (entityMate.hasId()) {
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
        return queryMapper.queryToSingle(getConnection(), sqlGenerator.getSqlEntity());
    }

    @Override
    public Integer selectCount(ConditionSql sql) {
        SelectSql sqlGenerator = SelectSql.create(dataBase)
                .select(" COUNT(*) ")
                .from(entityMate.getTableName())
                .where(sql);
        return queryMapper.queryToInteger(getConnection(), sqlGenerator.getSqlEntity());
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
        return queryMapper.queryToList(getConnection(), sqlGenerator.getSqlEntity());
    }

    @Override
    public synchronized PageEntity<T>  selectPage(int current, int size) {
        this.isAutoClose.set(false);

        PageEntity<T> pageEntity = new SimplePageEntity<>(current, size);
        Integer count = selectCount(null);
        pageEntity.setTotal(count);
        ConditionSql condition = ConditionSql.create(dataBase).eq("1","1")
                .limit( (current - 1) * size, size);
        List<T> list = selectList(condition);
        pageEntity.setList(list);

        this.closeConn();
        this.isAutoClose.set(true);

        return pageEntity;
    }

    @Override
    public synchronized PageEntity<T> selectPage(int current, int size, ConditionSql sql) {
        this.isAutoClose.set(false);
        PageEntity<T> pageEntity = new SimplePageEntity<>(current, size);
        Integer count = selectCount(sql);
        pageEntity.setTotal(count);
        if(sql == null){
            throw new SqlGeneratorException();
        }
        sql.limit((current - 1) * size, size);
        List<T> list = selectList(sql);
        pageEntity.setList(list);
        this.closeConn();
        this.isAutoClose.set(true);
        return pageEntity;
    }

    @Override
    public Integer execute(String sql, List<Object> params) {
        return queryMapper.executeToUpdate(getConnection(), new SqlEntity(sql, params));
    }

    @Override
    public EntityMate<T> getEntityMate() {
        return this.entityMate;
    }

    @Override
    public DataEntity<T> transaction() {
        this.isOpenTransaction.set(true);
        return this;
    }

    @Override
    public Boolean isOpenTransaction() {
        return this.isOpenTransaction.get();
    }

    @Override
    public void closeTransaction()  {
        this.isOpenTransaction.set(false);
        if(connection != null){
            try {
                connection.setAutoCommit(true);
                connection.commit();
                connection.close();
            }catch(SQLException e){
                throw new RuntimeException("关闭事务失败.");
            }
        }
    }

    @Override
    public DataEntity<T> autoClose(boolean auto) {
        this.isAutoClose.set(auto);
        return this;
    }

    @Override
    public Boolean isAutoClose() {
        return this.isAutoClose.get();
    }

    @Override
    public void closeConn() {
        if(!this.isAutoClose.get()){
            if(connection != null){
                try {
                    connection.close();
                }catch(SQLException e){
                    throw new RuntimeException("关闭SQL Connection失败.");
                }
            }
        }
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
        Connection conn = null;
        if (connection != null) {
            conn =  connection;
        }
        if (dataSource != null) {
            try {
                connection = dataSource.getConnection();
                conn = connection;
            } catch (SQLException e) {
                throw new RuntimeException("数据库连接出错.");
            }
        }

        if(conn == null){
            throw new RuntimeException("DB Connection has had Exception");
        }

        if(isOpenTransaction.get()){
            try {
                conn.setAutoCommit(false);
                return conn;
            } catch (SQLException e) {
                throw new RuntimeException("数据库连接出错.");
            }

        }else{
            return conn;
        }

    }
}
