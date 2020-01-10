package top.youlanqiang.mixorm.sql.mysql;


import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;
import top.youlanqiang.mixorm.sql.UpdateSqlGenerator;

import java.util.LinkedList;
import java.util.List;


public class MysqlUpdateSqlGenerator implements UpdateSqlGenerator {

    private StringBuilder sql;

    private ConditionSqlGenerator conditionSqlGenerator;

    private List<Object> params;

    public MysqlUpdateSqlGenerator(){
        this.sql = new StringBuilder();
        this.params = new LinkedList<>();
    }

    @Override
    public UpdateSqlGenerator update(String tableName) {
        this.sql.append("UPDATE ").append(tableName).append(" SET ");
        return this;
    }

    @Override
    public UpdateSqlGenerator set(String column, Object value) {
        if(params.isEmpty()){
            this.sql.append(column).append("= ? ");
        }else{
            this.sql.append(" , ").append(column).append("= ? ");
        }
        this.params.add(value);
        return this;
    }

    @Override
    public UpdateSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator) {
        if(conditionSqlGenerator != null){
            this.conditionSqlGenerator = conditionSqlGenerator;
            this.params.addAll(conditionSqlGenerator.getParams());
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
        if(this.conditionSqlGenerator == null){
            return getString();
        }else{
            return this.sql.toString() + this.conditionSqlGenerator.getSql();
        }
    }


}
