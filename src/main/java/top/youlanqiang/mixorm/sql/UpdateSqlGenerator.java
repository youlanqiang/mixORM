package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.sql.mysql.MysqlUpdateSqlGenerator;

import java.util.List;

public interface UpdateSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlUpdateSqlGenerator
     */
    static UpdateSqlGenerator create(){
        return new MysqlUpdateSqlGenerator();
    }

    UpdateSqlGenerator update(String tableName);

    UpdateSqlGenerator set(String column, Object value);

    UpdateSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator);

    List<Object> getParams();
}
