package top.youlanqiang.mixorm.sql.mysql;


import top.youlanqiang.mixorm.sql.ConditionSql;
import top.youlanqiang.mixorm.sql.UpdateSql;

import java.util.LinkedList;
import java.util.List;

/**
 * @author youlanqiang
 */
public class MysqlUpdateSql implements UpdateSql {

    private StringBuilder sql;

    private ConditionSql conditionSql;

    private List<Object> params;

    public MysqlUpdateSql(){
        this.sql = new StringBuilder();
        this.params = new LinkedList<>();
    }

    @Override
    public UpdateSql update(String tableName) {
        this.sql.append("UPDATE ").append(tableName).append(" SET ");
        return this;
    }

    @Override
    public UpdateSql set(String column, Object value) {
        if(params.isEmpty()){
            this.sql.append(column).append("= ? ");
        }else{
            this.sql.append(" , ").append(column).append("= ? ");
        }
        this.params.add(value);
        return this;
    }

    @Override
    public UpdateSql where(ConditionSql conditionSql) {
        if(conditionSql != null){
            this.conditionSql = conditionSql;
            this.params.addAll(conditionSql.getParams());
        }
        return this;
    }

    @Override
    public List<Object> getParams() {
        return params;
    }

    @Override
    public String getString() {
        return sql.toString();
    }

    @Override
    public String getSql() {
        if(this.conditionSql == null){
            return getString();
        }else{
            return this.sql.toString() + this.conditionSql.getSql();
        }
    }


}
