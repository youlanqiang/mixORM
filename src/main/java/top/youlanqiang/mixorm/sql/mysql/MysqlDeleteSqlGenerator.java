package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;
import top.youlanqiang.mixorm.sql.DeleteSqlGenerator;


import java.util.List;

public class MysqlDeleteSqlGenerator implements DeleteSqlGenerator {

    private StringBuilder sql;

    private ConditionSqlGenerator conditionSqlGenerator;

    public MysqlDeleteSqlGenerator(){
        this.sql = new StringBuilder();
        this.conditionSqlGenerator = null;
    }

    @Override
    public DeleteSqlGenerator deleteForm(String tableName) {
        this.sql.append(" DELETE FROM ").append(tableName).append(" ");
        return this;
    }

    @Override
    public DeleteSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator) {
        this.conditionSqlGenerator = conditionSqlGenerator;
        return this;
    }

    @Override
    public List<Object> getParams() {
        return conditionSqlGenerator.getParams();
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
