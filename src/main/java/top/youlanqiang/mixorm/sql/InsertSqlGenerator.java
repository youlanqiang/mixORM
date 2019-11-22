package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.sql.mysql.MysqlInsertSqlGenerator;

import java.util.Collection;

public interface InsertSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlInsertSqlGenerator
     */
    default InsertSqlGenerator create(){
        return new MysqlInsertSqlGenerator();
    }

    InsertSqlGenerator insertInto(String tableName);

    InsertSqlGenerator fields(String... columns);

    InsertSqlGenerator values(Object... values);

    InsertSqlGenerator values(Collection values);

}
