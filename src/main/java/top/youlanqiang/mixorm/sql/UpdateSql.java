package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlUpdateSql;

import java.util.List;

/**
 * @author youlanqiang
 */
public interface UpdateSql extends SqlGenerator {

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static UpdateSql create(DataBase dataBase){
        if(dataBase == null){
            throw new SqlGeneratorException("未连接数据库.");
        }
        if(DataBase.MySQL == dataBase){
            return new MysqlUpdateSql();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    /**
     * 待更新的数据库表
     * @param tableName 表名
     * @return this
     */
    UpdateSql update(String tableName);

    /**
     * 设置更新的字段
     * @param column 字段名
     * @param value  数据
     * @return this
     */
    UpdateSql set(String column, Object value);

    /**
     * 添加条件
     * @param conditionSql 条件
     * @return this
     */
    UpdateSql where(ConditionSql conditionSql);

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    @Override
    List<Object> getParams();
}
