package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;
import top.youlanqiang.mixorm.sql.SelectSqlGenerator;

import java.util.List;

public class MysqlSelectSqlGenerator implements SelectSqlGenerator {

    @Override
    public SelectSqlGenerator select(String... columns) {
        return null;
    }

    @Override
    public SelectSqlGenerator from(String tableName) {
        return null;
    }

    @Override
    public SelectSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator) {
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
