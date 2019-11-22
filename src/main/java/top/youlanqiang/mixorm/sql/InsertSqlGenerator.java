package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.sql.mysql.MysqlInsertSqlGenerator;

public interface InsertSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlInsertSqlGenerator
     */
    default InsertSqlGenerator create(){
        return new MysqlInsertSqlGenerator();
    }
}
