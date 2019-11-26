package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;
import top.youlanqiang.mixorm.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MysqlConditionSqlGenerator implements ConditionSqlGenerator {

    private StringBuilder sql;

    private List<Object> params;

    public MysqlConditionSqlGenerator(){
        this.sql = new StringBuilder();
        this.params = new ArrayList<>();
    }

    public ConditionSqlGenerator and(){
        this.sql.append(" AND ");
        return this;
    }

    public ConditionSqlGenerator or(){
        this.sql.append(" OR ");
        return this;
    }

    /**
     * 等于 =
     */
    public ConditionSqlGenerator eq(String column, Object val){
        this.sql.append(" ").append(column).append(" = ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 不等于 <>
     */
    public ConditionSqlGenerator ne(String column, Object val){
        this.sql.append(" ").append(column).append(" <> ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 大于 >
     */
    public ConditionSqlGenerator gt(String column, Object val){
        this.sql.append(" ").append(column).append(" > ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 大于等于 >=
     */
    public ConditionSqlGenerator ge(String column, Object val){
        this.sql.append(" ").append(column).append(" >= ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 小于 <
     */
    public ConditionSqlGenerator lt(String column, Object val){
        this.sql.append(" ").append(column).append(" < ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 小于等于 <=
     */
    public ConditionSqlGenerator le(String column, Object val){
        this.sql.append(" ").append(column).append(" <= ? ");
        this.params.add(val);
        return this;
    }

    /**
     * column between v1 and v2
     */
    public ConditionSqlGenerator between(String column, Object v1, Object v2){
        this.sql.append(" ").append(column).append(" BETWEEN ? AND ? ");
        this.params.add(v1);
        this.params.add(v2);
        return this;
    }

    /**
     * column not between v1 and v2
     */
    public ConditionSqlGenerator notBetween(String column, Object v1, Object v2){
        this.sql.append(" ").append(column).append(" NOT BETWEEN ? AND ? ");
        this.params.add(v1);
        this.params.add(v2);
        return this;
    }

    /**
     * column like %val%
     */
    public ConditionSqlGenerator like(String column, Object val){
        this.sql.append(" ").append(column).append(" LIKE  ? ");
        this.params.add("%" + val.toString() + "%");
        return this;
    }

    /**
     * column not like %val%
     */
    public ConditionSqlGenerator notLike(String column, Object val){
        this.sql.append(" ").append(column).append(" NOT LIKE  ? ");
        this.params.add("%" + val.toString() + "%");
        return this;
    }

    /**
     * column like %val
     */
    public ConditionSqlGenerator likeLeft(String column, Object val){
        this.sql.append(" ").append(column).append(" LIKE  ? ");
        this.params.add("%" + val.toString());
        return this;
    }

    /**
     * column like val%
     */
    public ConditionSqlGenerator likeRight(String column, Object val){
        this.sql.append(" ").append(column).append(" LIKE  ? ");
        this.params.add(val.toString() + "%");
        return this;
    }

    /**
     * column is null
     */
    public ConditionSqlGenerator isNull(String column){
        this.sql.append(" ").append(column).append(" IS NULL ");
        return this;
    }

    /**
     * column is not null
     */
    public ConditionSqlGenerator isNotNull(String column){
        this.sql.append(" ").append(column).append(" IS NOT NULL ");
        return this;
    }

    /**
     * column in (v1,v2,v3...)
     */
    public ConditionSqlGenerator in(String column, Object... values){
        in(column, Arrays.asList(values));
        return this;
    }

    /**
     * column in (v1,v2,v3...)
     */
    public ConditionSqlGenerator in(String column, Collection values){
        this.sql.append(" ").append(column).append(" IN ");
        String str = StringUtils.foreachByMark("(",")",",",values.size(), "?");
        this.sql.append(str);
        this.params.addAll(values);
        return this;
    }

    /**
     * column not in (v1,v2,v3...)
     */
    public ConditionSqlGenerator notIn(String column, Object... values){
        notIn(column, Arrays.asList(values));
        return this;
    }

    /**
     * column not in (v1,v2,v3...)
     */
    public ConditionSqlGenerator notIn(String column, Collection values){
        this.sql.append(" ").append(column).append(" NOT IN ");
        String str = StringUtils.foreachByMark("(",")",",",values.size(), "?");
        this.sql.append(str);
        this.params.addAll(values);
        return this;
    }

    /**
     * column in ( inValue )
     */
    public ConditionSqlGenerator inSql(String column, String inValue){
        this.sql.append(" ").append(column).append(" IN (").append(inValue).append(") ");
        return this;
    }

    /**
     * column not in ( inValue )
     */
    public ConditionSqlGenerator notInSql(String column, String inValue){
        this.sql.append(" ").append(column).append(" NOT IN (").append(inValue).append(") ");
        return this;
    }

    /**
     * group by v1, v2
     */
    public ConditionSqlGenerator groupBy(String... columns){
        this.sql.append(" ").append(" GROUP BY ");
        String sql = StringUtils.foreach("", "", ",", Arrays.asList(columns));
        this.sql.append(sql);
        return this;
    }

    /**
     * order by v1 ASC, v2 ASC
     */
    public ConditionSqlGenerator orderByAsc(String... columns){
        this.sql.append(" ").append(" ORDER BY ");
        String sql = StringUtils.foreachAddExt("",  "",  ",", Arrays.asList(columns), "ASC");
        this.sql.append(sql);
        return this;
    }

    /**
     * order by v1 DESC, v2 DESC
     */
    public ConditionSqlGenerator orderByDesc(String... columns){
        this.sql.append(" ").append(" ORDER BY ");
        String sql = StringUtils.foreachAddExt("",  "",  ",", Arrays.asList(columns), "DESC");
        this.sql.append(sql);
        return this;
    }

    /**
     * having str
     */
    public ConditionSqlGenerator having(String sqlHaving){
        this.sql.append(" ").append(" HAVING ").append(sqlHaving);
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
        return params;
    }
}
