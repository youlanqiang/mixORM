package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlInsertSqlGenerator;

import java.util.List;

/**
 * @author Administrator
 */
public interface InsertSqlGenerator extends SqlGenerator {

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static InsertSqlGenerator create(DataBase dataBase){
        if(DataBase.MYSQL == dataBase){
            return new MysqlInsertSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    InsertSqlGenerator insertInto(String tableName);

    InsertSqlGenerator fields(String... columns);

    InsertSqlGenerator fields(List<String> columns);

    InsertSqlGenerator values();

    InsertSqlGenerator oneItem(Object... values);

    InsertSqlGenerator oneItem(List<Object> values);

    @Override
    List<Object> getParams();

}
