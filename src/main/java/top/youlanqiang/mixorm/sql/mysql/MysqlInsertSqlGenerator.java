package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.InsertSqlGenerator;


import java.util.Collection;
import java.util.List;

public class MysqlInsertSqlGenerator implements InsertSqlGenerator {

    @Override
    public InsertSqlGenerator insertInto(String tableName) {
        return null;
    }

    @Override
    public InsertSqlGenerator fields(String... columns) {
        return null;
    }

    @Override
    public InsertSqlGenerator values(Object... values) {
        return null;
    }

    @Override
    public InsertSqlGenerator values(Collection values) {
        return null;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public String getSql() {
        return null;
    }

}
