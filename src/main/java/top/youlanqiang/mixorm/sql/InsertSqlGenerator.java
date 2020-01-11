package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlDeleteSqlGenerator;
import top.youlanqiang.mixorm.sql.mysql.MysqlInsertSqlGenerator;

import java.util.Collection;
import java.util.List;

public interface InsertSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlInsertSqlGenerator
     */
    static InsertSqlGenerator create(String productName){
        if(productName.equals("MySQL")){
            return new MysqlInsertSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + productName);
    }

    InsertSqlGenerator insertInto(String tableName);

    InsertSqlGenerator fields(String... columns);

    InsertSqlGenerator fields(List<String> columns);

    InsertSqlGenerator values();

    InsertSqlGenerator oneItem(Object... values);

    InsertSqlGenerator oneItem(List<Object> values);

    List<Object> getParams();

}
