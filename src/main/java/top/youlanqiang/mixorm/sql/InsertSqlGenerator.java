package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.sql.mysql.MysqlInsertSqlGenerator;

import java.util.Collection;
import java.util.List;

public interface InsertSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlInsertSqlGenerator
     */
    static InsertSqlGenerator create(){
        return new MysqlInsertSqlGenerator();
    }

    InsertSqlGenerator insertInto(String tableName);

    InsertSqlGenerator fields(String... columns);

    InsertSqlGenerator fields(List<String> columns);

    InsertSqlGenerator values();

    InsertSqlGenerator oneItem(Object... values);

    InsertSqlGenerator oneItem(List<Object> values);

    List<List<Object>> getParams();

}
