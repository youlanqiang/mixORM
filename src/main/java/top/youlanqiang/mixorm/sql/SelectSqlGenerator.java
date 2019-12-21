package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.sql.mysql.MysqlSelectSqlGenerator;

import java.util.Collection;

public interface SelectSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlSelectSqlGenerator
     */
    static SelectSqlGenerator create(){
        return new MysqlSelectSqlGenerator();
    }

    SelectSqlGenerator select(String... columns);

    SelectSqlGenerator select(Collection<String> columns);

    SelectSqlGenerator from(String tableName);

    SelectSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

}
