package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.sql.mysql.MysqlConditionSqlGenerator;

import java.util.Collection;
import java.util.List;

public interface ConditionSqlGenerator extends SqlGenerator {

    /**
     * @return 返回对应数据库的SqlGenerator,默认为mysql
     */
    default ConditionSqlGenerator create() {
        return new MysqlConditionSqlGenerator();
    }

    ConditionSqlGenerator and();

    ConditionSqlGenerator or();

    ConditionSqlGenerator eq(String column, Object val);

    ConditionSqlGenerator ne(String column, Object val);

    ConditionSqlGenerator gt(String column, Object val);

    ConditionSqlGenerator ge(String column, Object val);

    ConditionSqlGenerator lt(String column, Object val);

    ConditionSqlGenerator le(String column, Object val);

    ConditionSqlGenerator between(String column, Object v1, Object v2);

    ConditionSqlGenerator notBetween(String column, Object v1, Object v2);

    ConditionSqlGenerator like(String column, Object val);

   ConditionSqlGenerator notLike(String column, Object val);

    ConditionSqlGenerator likeLeft(String column, Object val);

    ConditionSqlGenerator likeRight(String column, Object val);

    ConditionSqlGenerator isNull(String column);

    ConditionSqlGenerator isNotNull(String column);

    ConditionSqlGenerator in(String column, Object... values);

    ConditionSqlGenerator in(String column, Collection values);

   ConditionSqlGenerator notIn(String column, Object... values);

    ConditionSqlGenerator notIn(String column, Collection values);

    ConditionSqlGenerator inSql(String column, String inValue);

    ConditionSqlGenerator notInSql(String column, String inValue);

    ConditionSqlGenerator groupBy(String... columns);

   ConditionSqlGenerator orderByAsc(String... columns);

    ConditionSqlGenerator orderByDesc(String... columns);

   ConditionSqlGenerator having(String sqlHaving);

    List<Object> getParams();
}
