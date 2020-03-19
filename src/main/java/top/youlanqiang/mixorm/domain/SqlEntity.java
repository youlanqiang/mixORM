package top.youlanqiang.mixorm.domain;


import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author youlanqiang
 */
public class SqlEntity {

    private String sql;

    private List<Object> params;

    private static final List<Object> EMPTY = new ArrayList<>(0);

    public SqlEntity(){}

    public SqlEntity(String sql, List<Object> params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        if(sql == null){
            throw new SqlGeneratorException("SQL语句不能为空");
        }
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParams() {
        if(params == null){
            return EMPTY;
        }
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }
}
