package top.youlanqiang.mixorm.sql.mysql;


import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;
import top.youlanqiang.mixorm.sql.UpdateSqlGenerator;


public class MysqlUpdateSqlGenerator implements UpdateSqlGenerator {

    @Override
    public UpdateSqlGenerator update(String tableName) {
        return null;
    }

    @Override
    public UpdateSqlGenerator set(String column, Object value) {
        return null;
    }

    @Override
    public UpdateSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator) {
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
