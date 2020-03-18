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

    private List<Object> param;

    private static final List<Object> EMPTY = new ArrayList<>(0);

    public SqlEntity(String sql, List<Object> param) {
        this.sql = sql;
        this.param = param;
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

    public List<Object> getParam() {
        if(param == null){
            return EMPTY;
        }
        return param;
    }

    public void setParam(List<Object> param) {
        this.param = param;
    }
}
