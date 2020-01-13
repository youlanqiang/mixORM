package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlSelectSqlGenerator;
import top.youlanqiang.mixorm.sql.mysql.MysqlUpdateSqlGenerator;

import java.util.List;

/**
 * @author youlanqiang
 */
public interface UpdateSqlGenerator extends SqlGenerator {

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static UpdateSqlGenerator create(DataBase dataBase){
        if(DataBase.MYSQL == dataBase){
            return new MysqlUpdateSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    UpdateSqlGenerator update(String tableName);

    UpdateSqlGenerator set(String column, Object value);

    UpdateSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    @Override
    List<Object> getParams();
}
