package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlSelectSqlGenerator;
import top.youlanqiang.mixorm.sql.mysql.MysqlUpdateSqlGenerator;

import java.util.List;

public interface UpdateSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlUpdateSqlGenerator
     */
    static UpdateSqlGenerator create(String productName){
        if(productName.equals("MySQL")){
            return new MysqlUpdateSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + productName);
    }

    UpdateSqlGenerator update(String tableName);

    UpdateSqlGenerator set(String column, Object value);

    UpdateSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    List<Object> getParams();
}
