package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlDeleteSqlGenerator;

import java.util.List;

/**
 * @author youlanqiang
 */
public interface DeleteSqlGenerator extends SqlGenerator{

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static DeleteSqlGenerator create(DataBase dataBase){
        if(DataBase.MySQL == dataBase){
            return new MysqlDeleteSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    /**
     * 设置delete表名
     * DELETE FROM [tablename]
     * @param tableName 表名
     * @return this
     */
    DeleteSqlGenerator deleteForm(String tableName);

    /**
     * 设置条件语句
     * @param conditionSqlGenerator 条件语句
     * @return this
     */
    DeleteSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    @Override
    List<Object> getParams();
}
