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
        if(DataBase.MYSQL == dataBase){
            return new MysqlSelectSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    SelectSqlGenerator select(String... columns);

    SelectSqlGenerator select(Collection<String> columns);

    SelectSqlGenerator from(String tableName);

    SelectSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    @Override
    List<Object> getParams();

}
