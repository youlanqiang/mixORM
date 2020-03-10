package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlSelectSql;

import java.util.Collection;
import java.util.List;

/**
 * @author youlanqiang
 */
public interface SelectSql extends SqlGenerator {

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static SelectSql create(DataBase dataBase){
        if(dataBase == null){
            throw new SqlGeneratorException("未连接数据库.");
        }
        if(DataBase.MySQL == dataBase){
            return new MysqlSelectSql();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    /**
     * 设置需要查询的字段
     * @param columns 查询字段
     * @return this
     */
    SelectSql select(String... columns);

    /**
     * 设置需要查询的字段
     * @param columns 查询字段
     * @return this
     */
    SelectSql select(Collection<String> columns);

    /**
     * 设置查询的表名
     * @param tableName 表名
     * @return this
     */
    SelectSql from(String tableName);

    /**
     * 设置查询条件
     * @param conditionSql 条件
     * @return this
     */
    SelectSql where(ConditionSql conditionSql);

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    @Override
    List<Object> getParams();

}
