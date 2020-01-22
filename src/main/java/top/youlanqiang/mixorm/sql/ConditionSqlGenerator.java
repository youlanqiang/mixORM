package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlConditionSqlGenerator;

import java.util.Collection;
import java.util.List;


/**
 * @author youlanqiang
 */
public interface ConditionSqlGenerator extends SqlGenerator {

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static ConditionSqlGenerator create(DataBase dataBase) {
        if(DataBase.MySQL == dataBase){
            return new MysqlConditionSqlGenerator();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    /**
     * and
     * @return this
     */
    ConditionSqlGenerator and();

    /**
     * or
     * @return this
     */
    ConditionSqlGenerator or();

    /**
     * 等于 =
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator eq(String column, Object val);

    /**
     * 等于 <>
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator ne(String column, Object val);

    /**
     * 大于 >
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator gt(String column, Object val);

    /**
     * 大于等于 >=
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator ge(String column, Object val);

    /**
     * 小于 <
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator lt(String column, Object val);

    /**
     * 小于等于 <=
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator le(String column, Object val);

    /**
     * between v1 and v2
     * @param column 字段名
     * @param v1 from
     * @param v2 to
     * @return this
     */
    ConditionSqlGenerator between(String column, Object v1, Object v2);

    /**
     * not between v1 and v2
     * @param column 字段名
     * @param v1 from
     * @param v2 to
     * @return this
     */
    ConditionSqlGenerator notBetween(String column, Object v1, Object v2);

    /**
     * like '%v1%'
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator like(String column, Object val);

    /**
     * not like '%v1%'
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator notLike(String column, Object val);

    /**
     * like '%v1'
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator likeLeft(String column, Object val);

    /**
     * like 'v1%'
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSqlGenerator likeRight(String column, Object val);

    /**
     * v1 is null
     * @param column 字段名
     * @return this
     */
    ConditionSqlGenerator isNull(String column);

    /**
     * v1 is not null
     * @param column 字段名
     * @return this
     */
    ConditionSqlGenerator isNotNull(String column);

    /**
     * in ( values )
     * @param column 字段名
     * @param values values
     * @return this
     */
    ConditionSqlGenerator in(String column, Object... values);

    /**
     * in ( values )
     * @param column 字段名
     * @param values values
     * @return this
     */
    ConditionSqlGenerator in(String column, Collection values);

    /**
     * not in ( values )
     * @param column 字段名
     * @param values values
     * @return this
     */
    ConditionSqlGenerator notIn(String column, Object... values);

    /**
     * not in ( values )
     * @param column 字段名
     * @param values values
     * @return this
     */
    ConditionSqlGenerator notIn(String column, Collection values);

    /**
     * in ( sql )
     * @param column 字段名
     * @param inValue sql
     * @return this
     */
    ConditionSqlGenerator inSql(String column, String inValue);

    /**
     * not in ( sql )
     * @param column 字段名
     * @param inValue sql
     * @return this
     */
    ConditionSqlGenerator notInSql(String column, String inValue);

    /**
     * group by columns
     * @param columns 字段名
     * @return this
     */
    ConditionSqlGenerator groupBy(String... columns);

    /**
     * order by columns asc
     * @param columns 字段名
     * @return this
     */
    ConditionSqlGenerator orderByAsc(String... columns);

    /**
     * order by columns desc
     * @param columns 字段名
     * @return this
     */
    ConditionSqlGenerator orderByDesc(String... columns);

    /**
     * HAVING [sql]
     * @param sqlHaving sql
     * @return this
     */
    ConditionSqlGenerator having(String sqlHaving);

    /**
     * limit offset, rows
     * @param offset offset
     * @param rows rows
     * @return this
     */
    ConditionSqlGenerator limit(Integer offset, Integer rows);

    /**
     * limit offset
     * @param offset offset
     * @return this
     */
    ConditionSqlGenerator limit(Integer offset);

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    @Override
    List<Object> getParams();
}
