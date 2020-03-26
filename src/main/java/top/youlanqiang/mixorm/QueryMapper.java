package top.youlanqiang.mixorm;


import top.youlanqiang.mixorm.domain.BatchSqlEntity;
import top.youlanqiang.mixorm.domain.SqlEntity;
import top.youlanqiang.mixorm.toolkit.NumberUtils;
import top.youlanqiang.mixorm.transform.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author youlanqiang
 */
class QueryMapper<T> {


    private final DataEntity<T> dataEntity;

    private final Transformer<T> transformer;

    public QueryMapper(DataEntity<T> dataEntity) {
        this.dataEntity = dataEntity;
        this.transformer = new Transformer<>(dataEntity.getEntityMate());
    }


    private void before(SqlEntity sqlEntity){
        if(Mixorm.getInstance().getConfig().isDebug()){
            System.out.println("SQL:" + sqlEntity.getSql());
            if(sqlEntity.getParams() != null) {
                System.out.println("PARAMS:" + sqlEntity.getParams().toString());
            }
        }
    }

    private void before(BatchSqlEntity sqlEntity){
        if(Mixorm.getInstance().getConfig().isDebug()){
            System.out.println("SQL:" + sqlEntity.getSql());
        }
    }

    private void after(Connection conn, Statement state, ResultSet rs){
        if(dataEntity.isOpenTransaction()){
            try {
                conn.commit();
            } catch (SQLException e) {
                throw new RuntimeException("事务提交失败.");
            }
        }else if(dataEntity.isAutoClose()) {
            close(conn, state, rs);
        }
    }


    private void catchError(Connection conn, SQLException exception){
        if(dataEntity.isOpenTransaction()){
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException("事务回滚失败.");
            }
        }else{
            throw new RuntimeException(exception);
        }
    }

    List<T> queryToList(Connection conn, SqlEntity sqlEntity) {

        before(sqlEntity);

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        try {
            statement = conn.prepareStatement(sqlEntity.getSql());
            List<Object> param = sqlEntity.getParams();
            if(param != null) {
                for (int i = 0; i < param.size(); i++) {
                    statement.setObject(i + 1, param.get(i));
                }
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T t = transformer.transform(resultSet);
                list.add(t);
            }
        } catch (SQLException e) {
            catchError(conn, e);
        } finally {
            after(conn, statement, resultSet);
        }
        return list;
    }


    T queryToSingle(Connection conn, SqlEntity sqlEntity) {

        before(sqlEntity);

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T t = null;
        try {
            statement = conn.prepareStatement(sqlEntity.getSql());
            List<Object> param = sqlEntity.getParams();
            if(param != null) {
                for (int i = 0; i < param.size(); i++) {
                    statement.setObject(i + 1, param.get(i));
                }
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                t = transformer.transform(resultSet);
            }
        } catch (SQLException e) {
            catchError(conn, e);
        } finally {
            after(conn, statement, resultSet);
        }
        return t;
    }

    Integer queryToInteger(Connection conn, SqlEntity sqlEntity) {

        before(sqlEntity);


        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int t = 0;
        try {

            statement = conn.prepareStatement(sqlEntity.getSql());
            List<Object> param = sqlEntity.getParams();
            if (param != null) {
                for (int i = 0; i < param.size(); i++) {
                    statement.setObject(i + 1, param.get(i));
                }
            }
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                t = resultSet.getInt(1);
            }
        } catch (SQLException e) {

            catchError(conn, e);
        } finally {
            after(conn, statement, resultSet);
        }
        return t;
    }

    InsertResult insert(Connection conn, SqlEntity sqlEntity, boolean hasGeneratedKey, Class idClass) {


        before(sqlEntity);


        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            if (hasGeneratedKey) {
                statement = conn.prepareStatement(sqlEntity.getSql(), Statement.RETURN_GENERATED_KEYS);
            } else {
                statement = conn.prepareStatement(sqlEntity.getSql());
            }
            List<Object> param = sqlEntity.getParams();
            if(param != null) {
                for (int i = 0; i < param.size(); i++) {
                    statement.setObject(i + 1, param.get(i));
                }
            }
            int count = statement.executeUpdate();
            if (hasGeneratedKey) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    Object key = resultSet.getObject(1, idClass);
                    return new InsertResult(count, key);
                }
            }
            return new InsertResult(count, null);

        } catch (SQLException e) {
            catchError(conn, e);
        } finally {
            after(conn, statement, resultSet);
        }
        return new InsertResult(0, null);
    }


    Long executeBatchToUpdate(Connection conn, BatchSqlEntity sqlEntity){
        before(sqlEntity);

        PreparedStatement statement = null;
        try{
            int count = 0;
            long result = 0;
            statement = conn.prepareStatement(sqlEntity.getSql());
            List<List<Object>> rows = sqlEntity.getRows();
            for (List<Object> params : rows) {
                if(params != null) {
                    for (int i = 0; i < params.size(); i++) {
                        statement.setObject(i + 1, params.get(i));
                    }
                    statement.addBatch();
                    count ++;
                }
                if(count % 500 == 0){
                    result += NumberUtils.arraySum(statement.executeBatch());
                    statement.clearBatch();
                }
            }
            return result;
        }catch(SQLException e){
            catchError(conn, e);
        }finally{
            after(conn, statement, null);
        }
        return 0L;
    }

    Integer executeToUpdate(Connection conn, SqlEntity sqlEntity) {

        before(sqlEntity);

        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sqlEntity.getSql());
            List<Object> params = sqlEntity.getParams();
            if(params != null) {
                for (int i = 0; i < params.size(); i++) {
                    statement.setObject(i + 1, params.get(i));
                }
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            catchError(conn, e);
        } finally {
            after(conn, statement, null);
        }
        return 0;
    }



    public void close(Connection conn, Statement state, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (state != null) {
                state.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接关闭出错.");
        }
    }



    static class InsertResult {
        private Integer count;

        private Object key;

        public InsertResult() {
        }

        public InsertResult(Integer count, Object key) {
            this.count = count;
            this.key = key;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }
    }
}
