package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.sql.mysql.MysqlSelectSqlGenerator;

public interface SelectSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlSelectSqlGenerator
     */
    default SelectSqlGenerator create(){
        return new MysqlSelectSqlGenerator();
    }

    SelectSqlGenerator select(String... columns);

    SelectSqlGenerator from(String tableName);

    SelectSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

}