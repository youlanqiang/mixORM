package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.InsertSqlGenerator;
import top.youlanqiang.mixorm.toolkit.StringUtils;


import java.util.*;

public class MysqlInsertSqlGenerator implements InsertSqlGenerator {

    private StringBuilder sql;

    private List<Collection<Object>> params;


    public MysqlInsertSqlGenerator() {
        this.sql = new StringBuilder();
        this.params = new LinkedList<>();
    }

    @Override
    public InsertSqlGenerator insertInto(String tableName) {
        this.sql.append(" INSERT INTO ").append(tableName).append(" ");
        return this;
    }

    @Override
    public InsertSqlGenerator fields(String... columns) {
        return fields(Arrays.asList(columns));
    }

    @Override
    public InsertSqlGenerator fields(Collection<String> columns) {
        this.sql.append(StringUtils.foreach("(", ")", ",", columns));
        return this;
    }

    @Override
    public InsertSqlGenerator values() {
        this.sql.append(" VALUES ");
        return this;
    }

    @Override
    public InsertSqlGenerator oneItem(Object... values) {
        return oneItem(Arrays.asList(values));
    }

    @Override
    public InsertSqlGenerator oneItem(Collection<Object> values) {
        this.params.add(values);
        this.sql.append(StringUtils.foreachByMark("(", ")", ",", values.size(), "?"));
        return this;
    }

    @Override
    public List<Collection<Object>> getParams() {
        return this.params;
    }

    @Override
    public String getString() {
        return sql.toString();
    }

    @Override
    public String getSql() {
        return sql.toString();
    }

}
