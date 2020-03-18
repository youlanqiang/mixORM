package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.domain.SqlEntity;
import top.youlanqiang.mixorm.sql.ConditionSql;
import top.youlanqiang.mixorm.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * @author youlanqiang
 */
public class MysqlConditionSql implements ConditionSql {

    private StringBuilder sql;

    private List<Object> params;

    public MysqlConditionSql(){
        this.sql = new StringBuilder();
        this.params = new ArrayList<>();
    }

    @Override
    public ConditionSql and(){
        this.sql.append(" AND ");
        return this;
    }

    @Override
    public ConditionSql or(){
        this.sql.append(" OR ");
        return this;
    }

    /**
     * 等于 =
     */
    @Override
    public ConditionSql eq(String column, Object val){
        this.sql.append(" ").append(column).append(" = ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 不等于 <>
     */
    @Override
    public ConditionSql ne(String column, Object val){
        this.sql.append(" ").append(column).append(" <> ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 大于 >
     */
    @Override
    public ConditionSql gt(String column, Object val){
        this.sql.append(" ").append(column).append(" > ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 大于等于 >=
     */
    @Override
    public ConditionSql ge(String column, Object val){
        this.sql.append(" ").append(column).append(" >= ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 小于 <
     */
    @Override
    public ConditionSql lt(String column, Object val){
        this.sql.append(" ").append(column).append(" < ? ");
        this.params.add(val);
        return this;
    }

    /**
     * 小于等于 <=
     */
    @Override
    public ConditionSql le(String column, Object val){
        this.sql.append(" ").append(column).append(" <= ? ");
        this.params.add(val);
        return this;
    }

    /**
     * column between v1 and v2
     */
    @Override
    public ConditionSql between(String column, Object v1, Object v2){
        this.sql.append(" ").append(column).append(" BETWEEN ? AND ? ");
        this.params.add(v1);
        this.params.add(v2);
        return this;
    }

    /**
     * column not between v1 and v2
     */
    @Override
    public ConditionSql notBetween(String column, Object v1, Object v2){
        this.sql.append(" ").append(column).append(" NOT BETWEEN ? AND ? ");
        this.params.add(v1);
        this.params.add(v2);
        return this;
    }

    /**
     * column like %val%
     */
    @Override
    public ConditionSql like(String column, Object val){
        this.sql.append(" ").append(column).append(" LIKE  ? ");
        this.params.add("%" + val.toString() + "%");
        return this;
    }

    /**
     * column not like %val%
     */
    @Override
    public ConditionSql notLike(String column, Object val){
        this.sql.append(" ").append(column).append(" NOT LIKE  ? ");
        this.params.add("%" + val.toString() + "%");
        return this;
    }

    /**
     * column like %val
     */
    @Override
    public ConditionSql likeLeft(String column, Object val){
        this.sql.append(" ").append(column).append(" LIKE  ? ");
        this.params.add("%" + val.toString());
        return this;
    }

    /**
     * column like val%
     */
    @Override
    public ConditionSql likeRight(String column, Object val){
        this.sql.append(" ").append(column).append(" LIKE  ? ");
        this.params.add(val.toString() + "%");
        return this;
    }

    /**
     * column is null
     */
    @Override
    public ConditionSql isNull(String column){
        this.sql.append(" ").append(column).append(" IS NULL ");
        return this;
    }

    /**
     * column is not null
     */
    @Override
    public ConditionSql isNotNull(String column){
        this.sql.append(" ").append(column).append(" IS NOT NULL ");
        return this;
    }

    /**
     * column in (v1,v2,v3...)
     */
    @Override
    public ConditionSql in(String column, Object... values){
        in(column, Arrays.asList(values));
        return this;
    }

    /**
     * column in (v1,v2,v3...)
     */
    @Override
    public ConditionSql in(String column, Collection values){
        this.sql.append(" ").append(column).append(" IN ");
        String str = StringUtils.foreachByMark("(",")",",",values.size(), "?");
        this.sql.append(str);
        this.params.addAll(values);
        return this;
    }

    /**
     * column not in (v1,v2,v3...)
     */
    @Override
    public ConditionSql notIn(String column, Object... values){
        notIn(column, Arrays.asList(values));
        return this;
    }

    /**
     * column not in (v1,v2,v3...)
     */
    @Override
    public ConditionSql notIn(String column, Collection values){
        this.sql.append(" ").append(column).append(" NOT IN ");
        String str = StringUtils.foreachByMark("(",")",",",values.size(), "?");
        this.sql.append(str);
        this.params.addAll(values);
        return this;
    }

    /**
     * column in ( inValue )
     */
    @Override
    public ConditionSql inSql(String column, String inValue){
        this.sql.append(" ").append(column).append(" IN (").append(inValue).append(") ");
        return this;
    }

    /**
     * column not in ( inValue )
     */
    @Override
    public ConditionSql notInSql(String column, String inValue){
        this.sql.append(" ").append(column).append(" NOT IN (").append(inValue).append(") ");
        return this;
    }

    /**
     * group by v1, v2
     */
    @Override
    public ConditionSql groupBy(String... columns){
        this.sql.append(" ").append(" GROUP BY ");
        String sql = StringUtils.foreach("", "", ",", Arrays.asList(columns));
        this.sql.append(sql);
        return this;
    }

    /**
     * order by v1 ASC, v2 ASC
     */
    @Override
    public ConditionSql orderByAsc(String... columns){
        this.sql.append(" ").append(" ORDER BY ");
        String sql = StringUtils.foreachAddExt("",  "",  ",", Arrays.asList(columns), "ASC");
        this.sql.append(sql);
        return this;
    }

    /**
     * order by v1 DESC, v2 DESC
     */
    @Override
    public ConditionSql orderByDesc(String... columns){
        this.sql.append(" ").append(" ORDER BY ");
        String sql = StringUtils.foreachAddExt("",  "",  ",", Arrays.asList(columns), "DESC");
        this.sql.append(sql);
        return this;
    }

    /**
     * having str
     */
    @Override
    public ConditionSql having(String sqlHaving){
        this.sql.append(" ").append(" HAVING ").append(sqlHaving);
        return this;
    }

    @Override
    public ConditionSql limit(Integer offset, Integer rows) {
        this.sql.append(" ").append(" LIMIT ").append(offset).append(" , ").append(rows);
        return this;
    }

    @Override
    public ConditionSql limit(Integer offset) {
        this.sql.append(" ").append(" LIMIT ").append(offset);
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

    @Override
    public SqlEntity getSqlEntity() {
        return new SqlEntity(getSql(), getParams());
    }
}
