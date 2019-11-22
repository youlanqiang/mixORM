package top.youlanqiang.mixorm.sql;

import top.youlanqiang.mixorm.sql.mysql.MysqlDeleteSqlGenerator;

public interface DeleteSqlGenerator extends SqlGenerator{

    /**
     * @return 默认使用MysqlDeleteSqlGenerator
     */
    default DeleteSqlGenerator create(){
        return new MysqlDeleteSqlGenerator();
    }

}
