package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlConditionSqlGenerator;
import top.youlanqiang.mixorm.sql.mysql.MysqlDeleteSqlGenerator;

import java.util.List;

public interface DeleteSqlGenerator extends SqlGenerator{

    /**
     * @return 默认使用MysqlDeleteSqlGenerator
     */
    static DeleteSqlGenerator create(String productName){
        if(productName.equals("MySQL")){
            return new MysqlDeleteSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + productName);
    }


    DeleteSqlGenerator deleteForm(String tableName);

    DeleteSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    List<Object> getParams();
}
