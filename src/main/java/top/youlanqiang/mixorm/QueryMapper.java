package top.youlanqiang.mixorm;


import top.youlanqiang.mixorm.mate.EntityField;
import top.youlanqiang.mixorm.mate.EntityMate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class QueryMapper<T> {


    private final EntityMate<T> mate;


    public QueryMapper(EntityMate<T> mate) {
        this.mate = mate;
    }


    List<T> queryToList(Connection conn, String sql, List<Object> param) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        try {
            statement = conn.prepareStatement(sql);
            if(param != null) {
                for (int i = 0; i < param.size(); i++) {
                    statement.setObject(i + 1, param.get(i));
                }
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T t = transform(resultSet);
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, statement, resultSet);
        }
        return list;
    }


    T queryToSingle(Connection conn, String sql, List<Object> param) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T t = null;
        try {
            statement = conn.prepareStatement(sql);
            if(param != null) {
                for (int i = 0; i < param.size(); i++) {
                    statement.setObject(i + 1, param.get(i));
                }
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                t = transform(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, statement, resultSet);
        }
        return t;
    }

    Integer queryToInteger(Connection conn, String sql, List<Object> param) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int t = 0;
        try {
            statement = conn.prepareStatement(sql);
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
            e.printStackTrace();
        } finally {
            close(conn, statement, resultSet);
        }
        return t;
    }

    InsertResult insert(Connection conn, String sql, List<Object> param, boolean hasKey) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            if (hasKey) {
                statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                statement = conn.prepareStatement(sql);
            }
            if(param != null) {
                for (int i = 0; i < param.size(); i++) {
                    statement.setObject(i + 1, param.get(i));
                }
            }
            int count = statement.executeUpdate();
            if (hasKey) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    Object key = resultSet.getObject(1);
                    return new InsertResult(count, key);
                }
            }
            return new InsertResult(count, null);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, statement, resultSet);
        }
        return new InsertResult(0, null);
    }

    Integer executeToUpdate(Connection conn, String sql, List<Object> param) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            if(param != null) {
                for (int i = 0; i < param.size(); i++) {
                    statement.setObject(i + 1, param.get(i));
                }
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, statement, null);
        }
        return 0;
    }

    public EntityMate<T> getMate() {
        return mate;
    }

    public void close(Connection conn, Statement state, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (state != null) state.close();
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据数据库查询结果集构造一个实体类对象
     *
     * @param resultSet 数据库结果集
     * @return 实体类对象
     */
    private T transform(ResultSet resultSet) {
        Class<T> tClass = mate.getClazz();
        T result = null;
        try {
            Constructor<T> constructor = tClass.getConstructor(null);
            result = constructor.newInstance(null);
            EntityField id = mate.getIdEntity();
            Method idMethod = tClass.getMethod(id.getSetMethod(), id.getColumnType());
            idMethod.invoke(result, resultSet.getObject(id.getColumnName()));

            Map<String, EntityField> fields = mate.getFields();
            for (String key : fields.keySet()) {
                EntityField field = fields.get(key);
                Method method = tClass.getMethod(field.getSetMethod(), field.getColumnType());
                idMethod.invoke(result, resultSet.getObject(field.getColumnName()));
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return result;
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
