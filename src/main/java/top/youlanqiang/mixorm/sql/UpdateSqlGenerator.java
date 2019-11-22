package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.sql.mysql.MysqlUpdateSqlGenerator;

public interface UpdateSqlGenerator extends SqlGenerator {

    /**
     * @return 默认使用MysqlUpdateSqlGenerator
     */
    default UpdateSqlGenerator create(){
        return new MysqlUpdateSqlGenerator();
    }
}
