package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlDeleteSql;

import java.util.List;

/**
 * @author youlanqiang
 */
public interface DeleteSql extends SqlGenerator{

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static DeleteSql create(DataBase dataBase){
        if(dataBase == null){
            throw new SqlGeneratorException("未连接数据库.");
        }
        if(DataBase.MySQL == dataBase){
            return new MysqlDeleteSql();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    /**
     * 设置delete表名
     * DELETE FROM [tablename]
     * @param tableName 表名
     * @return this
     */
    DeleteSql deleteForm(String tableName);

    /**
     * 设置条件语句
     * @param conditionSql 条件语句
     * @return this
     */
    DeleteSql where(ConditionSql conditionSql);

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    @Override
    List<Object> getParams();
}
