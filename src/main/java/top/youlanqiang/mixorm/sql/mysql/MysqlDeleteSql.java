package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.domain.SqlEntity;
import top.youlanqiang.mixorm.sql.ConditionSql;
import top.youlanqiang.mixorm.sql.DeleteSql;


import java.util.List;

/**
 * @author youlanqiang
 */
public class MysqlDeleteSql implements DeleteSql {

    private StringBuilder sql;

    private ConditionSql conditionSql;

    public MysqlDeleteSql(){
        this.sql = new StringBuilder();
        this.conditionSql = null;
    }

    @Override
    public DeleteSql deleteForm(String tableName) {
        this.sql.append(" DELETE FROM ").append(tableName).append(" ");
        return this;
    }

    @Override
    public DeleteSql where(ConditionSql conditionSql) {
        this.conditionSql = conditionSql;
        return this;
    }

    @Override
    public List<Object> getParams() {
        if(this.conditionSql != null){
            return conditionSql.getParams();
        }
        return null;
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


    @Override
    public SqlEntity getSqlEntity() {
        return new SqlEntity(getSql(), getParams());
    }
}
