package top.youlanqiang.mixorm.domain;


import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;

import java.util.List;

/**
 * 批量sql
 * @author youlanqiang
 */
public class BatchSqlEntity {

    private String sql;

    private List<List<Object>> rows;

    public String getSql() {
        if(sql == null){
            throw new SqlGeneratorException("SQL语句不能为空");
        }
        return sql;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }



}
