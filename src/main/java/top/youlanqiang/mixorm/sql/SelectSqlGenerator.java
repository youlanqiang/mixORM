package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlSelectSqlGenerator;

import java.util.Collection;
import java.util.List;

/**
 * @author youlanqiang
 */
public interface SelectSqlGenerator extends SqlGenerator {

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static SelectSqlGenerator create(DataBase dataBase){
        if(DataBase.MySQL == dataBase){
            return new MysqlSelectSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    /**
     * 设置需要查询的字段
     * @param columns 查询字段
     * @return this
     */
    SelectSqlGenerator select(String... columns);

    /**
     * 设置需要查询的字段
     * @param columns 查询字段
     * @return this
     */
    SelectSqlGenerator select(Collection<String> columns);

    /**
     * 设置查询的表名
     * @param tableName 表名
     * @return this
     */
    SelectSqlGenerator from(String tableName);

    /**
     * 设置查询条件
     * @param conditionSqlGenerator 条件
     * @return this
     */
    SelectSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    @Override
    List<Object> getParams();

}
