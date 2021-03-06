package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlInsertSql;

import java.util.List;

/**
 * @author youlanqiang
 */
public interface InsertSql extends SqlGenerator {

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static InsertSql create(DataBase dataBase){
        if(dataBase == null){
            throw new SqlGeneratorException("未连接数据库.");
        }
        if(DataBase.MySQL == dataBase){
            return new MysqlInsertSql();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    /**
     * 设置插入数据的表名
     * @param tableName 表名
     * @return this
     */
    InsertSql insertInto(String tableName);


    /**
     * 设置字段
     * @param columns 字段的list
     * @return this
     */
    InsertSql fields(List<String> columns);


    /**
     * 将数据插入insertSqlGenerator中
     * @param values value
     * @return this
     */
    InsertSql values(List<Object> values);

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    @Override
    List<Object> getParams();

}
