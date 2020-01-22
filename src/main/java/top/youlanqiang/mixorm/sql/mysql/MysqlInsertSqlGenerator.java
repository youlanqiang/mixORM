package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.InsertSqlGenerator;
import top.youlanqiang.mixorm.toolkit.StringUtils;


import java.util.*;

/**
 * @author youlanqiang
 */
public class MysqlInsertSqlGenerator implements InsertSqlGenerator {

    private StringBuilder sql;

    private List<Object> params;

    private boolean batch;

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
    public InsertSqlGenerator fields(List<String> columns) {
        this.sql.append(StringUtils.foreach("(", ")", ",", columns));
        return this;
    }



    @Override
    public InsertSqlGenerator values(List<Object> values){
        this.sql.append(" VALUES ");
        this.oneItem(values);
        return this;
    }


    @Override
    public List<Object> getParams() {
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



    private InsertSqlGenerator oneItem(List<Object> values) {
        this.params = values;
        if(!batch) {
            this.sql.append(StringUtils.foreachByMark("(", ")", ",", values.size(), "?"));
            batch = true;
        }else{
            throw new SqlGeneratorException();
        }
        return this;
    }
}
