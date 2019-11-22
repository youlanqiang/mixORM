package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;

import java.util.Collection;
import java.util.List;

public class MysqlConditionSqlGenerator implements ConditionSqlGenerator {

    private StringBuilder sql;

    public MysqlConditionSqlGenerator(){
        this.sql = new StringBuilder();
    }

    public ConditionSqlGenerator and(){
        return this;
    }

    public ConditionSqlGenerator or(){
        return this;
    }

    /**
     * 等于 =
     */
    public ConditionSqlGenerator eq(String column, Object val){

        return this;
    }

    /**
     * 不等于 <>
     */
    public ConditionSqlGenerator ne(String column, Object val){
        return this;
    }

    /**
     * 大于 >
     */
    public ConditionSqlGenerator gt(String column, Object val){
        return this;
    }

    /**
     * 大于等于 >=
     */
    public ConditionSqlGenerator ge(String column, Object val){
        return this;
    }

    /**
     * 小于 <
     */
    public ConditionSqlGenerator lt(String column, Object val){
        return this;
    }

    /**
     * 小于等于 <=
     */
    public ConditionSqlGenerator le(String column, Object val){
        return this;
    }

    /**
     * column between v1 and v2
     */
    public ConditionSqlGenerator between(String column, Object v1, Object v2){
        return this;
    }

    /**
     * column not between v1 and v2
     */
    public ConditionSqlGenerator notBetween(String column, Object v1, Object v2){
        return this;
    }

    /**
     * column like %val%
     */
    public ConditionSqlGenerator like(String column, Object val){
        return this;
    }

    /**
     * column not like %val%
     */
    public ConditionSqlGenerator notLike(String column, Object val){
        return this;
    }

    /**
     * column like %val
     */
    public ConditionSqlGenerator likeLeft(String column, Object val){
        return this;
    }

    /**
     * column like val%
     */
    public ConditionSqlGenerator likeRight(String column, Object val){
        return this;
    }

    /**
     * column is null
     */
    public ConditionSqlGenerator isNull(String column){
        return this;
    }

    /**
     * column is not null
     */
    public ConditionSqlGenerator isNotNull(String column){
        return this;
    }

    /**
     * column in (v1,v2,v3...)
     */
    public ConditionSqlGenerator in(String column, Object... values){
        return this;
    }

    /**
     * column in (v1,v2,v3...)
     */
    public ConditionSqlGenerator in(String column, Collection values){
        return this;
    }

    /**
     * column not in (v1,v2,v3...)
     */
    public ConditionSqlGenerator notIn(String column, Object... values){
        return this;
    }

    /**
     * column not in (v1,v2,v3...)
     */
    public ConditionSqlGenerator notIn(String column, Collection values){
        return this;
    }

    /**
     * column in ( inValue )
     */
    public ConditionSqlGenerator inSql(String column, String inValue){
        return this;
    }

    /**
     * column not in ( inValue )
     */
    public ConditionSqlGenerator notInSql(String column, String inValue){
        return this;
    }

    /**
     * group by v1, v2
     */
    public ConditionSqlGenerator groupBy(String... columns){
        return this;
    }

    /**
     * order by v1 ASC, v2 ASC
     */
    public ConditionSqlGenerator orderByAsc(String... columns){
        return this;
    }

    /**
     * order by v1 DESC, v2 DESC
     */
    public ConditionSqlGenerator orderByDesc(String... columns){
        return this;
    }

    /**
     * having str
     */
    public ConditionSqlGenerator having(String sqlHaving){
        return this;
    }


    @Override
    public String getString() {
        return sql.toString();
    }

    @Override
    public String getSql() {
        return " WHERE " + sql.toString();
    }

    @Override
    public List<Object> getParams() {
        return null;
    }
}
