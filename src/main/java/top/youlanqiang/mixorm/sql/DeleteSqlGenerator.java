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
        if(DataBase.MYSQL == dataBase){
            return new MysqlDeleteSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }


    DeleteSqlGenerator deleteForm(String tableName);

    DeleteSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    @Override
    List<Object> getParams();
}
