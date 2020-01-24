package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.exceptions.SqlGeneratorException;
import top.youlanqiang.mixorm.sql.mysql.MysqlConditionSql;

import java.util.Collection;
import java.util.List;


/**
 * @author youlanqiang
 */
public interface ConditionSql extends SqlGenerator {

    /**
     * 返回对应数据库的SqlGenerator
     * @param dataBase 数据库类型
     * @return SqlGenerator
     */
    static ConditionSql create(DataBase dataBase) {
        if(DataBase.MySQL == dataBase){
            return new MysqlConditionSql();
        }
        throw new SqlGeneratorException("未支持的数据库类型:" + dataBase);
    }

    /**
     * and
     * @return this
     */
    ConditionSql and();

    /**
     * or
     * @return this
     */
    ConditionSql or();

    /**
     * 等于 =
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql eq(String column, Object val);

    /**
     * 等于 <>
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql ne(String column, Object val);

    /**
     * 大于 >
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql gt(String column, Object val);

    /**
     * 大于等于 >=
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql ge(String column, Object val);

    /**
     * 小于 <
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql lt(String column, Object val);

    /**
     * 小于等于 <=
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql le(String column, Object val);

    /**
     * between v1 and v2
     * @param column 字段名
     * @param v1 from
     * @param v2 to
     * @return this
     */
    ConditionSql between(String column, Object v1, Object v2);

    /**
     * not between v1 and v2
     * @param column 字段名
     * @param v1 from
     * @param v2 to
     * @return this
     */
    ConditionSql notBetween(String column, Object v1, Object v2);

    /**
     * like '%v1%'
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql like(String column, Object val);

    /**
     * not like '%v1%'
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql notLike(String column, Object val);

    /**
     * like '%v1'
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql likeLeft(String column, Object val);

    /**
     * like 'v1%'
     * @param column 字段名
     * @param val 值
     * @return this
     */
    ConditionSql likeRight(String column, Object val);

    /**
     * v1 is null
     * @param column 字段名
     * @return this
     */
    ConditionSql isNull(String column);

    /**
     * v1 is not null
     * @param column 字段名
     * @return this
     */
    ConditionSql isNotNull(String column);

    /**
     * in ( values )
     * @param column 字段名
     * @param values values
     * @return this
     */
    ConditionSql in(String column, Object... values);

    /**
     * in ( values )
     * @param column 字段名
     * @param values values
     * @return this
     */
    ConditionSql in(String column, Collection values);

    /**
     * not in ( values )
     * @param column 字段名
     * @param values values
     * @return this
     */
    ConditionSql notIn(String column, Object... values);

    /**
     * not in ( values )
     * @param column 字段名
     * @param values values
     * @return this
     */
    ConditionSql notIn(String column, Collection values);

    /**
     * in ( sql )
     * @param column 字段名
     * @param inValue sql
     * @return this
     */
    ConditionSql inSql(String column, String inValue);

    /**
     * not in ( sql )
     * @param column 字段名
     * @param inValue sql
     * @return this
     */
    ConditionSql notInSql(String column, String inValue);

    /**
     * group by columns
     * @param columns 字段名
     * @return this
     */
    ConditionSql groupBy(String... columns);

    /**
     * order by columns asc
     * @param columns 字段名
     * @return this
     */
    ConditionSql orderByAsc(String... columns);

    /**
     * order by columns desc
     * @param columns 字段名
     * @return this
     */
    ConditionSql orderByDesc(String... columns);

    /**
     * HAVING [sql]
     * @param sqlHaving sql
     * @return this
     */
    ConditionSql having(String sqlHaving);

    /**
     * limit offset, rows
     * @param offset offset
     * @param rows rows
     * @return this
     */
    ConditionSql limit(Integer offset, Integer rows);

    /**
     * limit offset
     * @param offset offset
     * @return this
     */
    ConditionSql limit(Integer offset);

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    @Override
    List<Object> getParams();
}
