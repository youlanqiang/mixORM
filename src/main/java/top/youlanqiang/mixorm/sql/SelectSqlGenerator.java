package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlInsertSqlGenerator;
import top.youlanqiang.mixorm.sql.mysql.MysqlSelectSqlGenerator;

import java.util.Collection;
import java.util.List;

public interface SelectSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlSelectSqlGenerator
     */
    static SelectSqlGenerator create(String productName){
        if(productName.equals("MySQL")){
            return new MysqlSelectSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + productName);
    }

    SelectSqlGenerator select(String... columns);

    SelectSqlGenerator select(Collection<String> columns);

    SelectSqlGenerator from(String tableName);

    SelectSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    List<Object> getParams();

}
