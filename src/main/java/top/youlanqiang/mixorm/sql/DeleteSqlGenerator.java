package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.sql.mysql.MysqlDeleteSqlGenerator;

public interface DeleteSqlGenerator extends SqlGenerator{

    /**
     * @return 默认使用MysqlDeleteSqlGenerator
     */
    static DeleteSqlGenerator create(){
        return new MysqlDeleteSqlGenerator();
    }


    DeleteSqlGenerator deleteForm(String tableName);

    DeleteSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

}
