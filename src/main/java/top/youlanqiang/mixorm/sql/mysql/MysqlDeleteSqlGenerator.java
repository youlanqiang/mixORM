package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;
import top.youlanqiang.mixorm.sql.DeleteSqlGenerator;


import java.util.List;

public class MysqlDeleteSqlGenerator implements DeleteSqlGenerator {


    @Override
    public DeleteSqlGenerator deleteForm(String tableName) {
        return null;
    }

    @Override
    public DeleteSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator) {
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
