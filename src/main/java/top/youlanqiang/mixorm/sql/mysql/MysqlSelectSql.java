package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.ConditionSql;
import top.youlanqiang.mixorm.sql.SelectSql;
import top.youlanqiang.mixorm.toolkit.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author youlanqiang
 */
public class MysqlSelectSql implements SelectSql {

    private StringBuilder sql;

    private ConditionSql conditionSql;

    public MysqlSelectSql(){
        this.sql = new StringBuilder();
    }

    @Override
    public SelectSql select(String... columns) {
        return select(Arrays.asList(columns));
    }

    @Override
    public SelectSql select(Collection<String> columns) {
        this.sql.append("SELECT ")
                .append(StringUtils.foreach("", "", ",", columns))
                .append(" ");
        return this;
    }

    @Override
    public SelectSql from(String tableName) {
        this.sql.append(" FROM ").append(tableName);
        return this;
    }

    @Override
    public SelectSql where(ConditionSql conditionSql) {
        this.conditionSql = conditionSql;
        return this;
    }

    @Override
    public List<Object> getParams() {
        if(this.conditionSql != null){
            return this.conditionSql.getParams();
        }
        return null;
    }

    @Override
    public String getString() {
        return sql.toString();
    }

    @Override
    public String getSql() {
        if(this.conditionSql == null){
            return getString();
        }else{
            return this.sql.toString() + this.conditionSql.getSql();
        }
    }


}
