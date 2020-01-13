package top.youlanqiang.mixorm.sql.mysql;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;
import top.youlanqiang.mixorm.sql.SelectSqlGenerator;
import top.youlanqiang.mixorm.toolkit.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author youlanqiang
 */
public class MysqlSelectSqlGenerator implements SelectSqlGenerator {

    private StringBuilder sql;

    private ConditionSqlGenerator conditionSqlGenerator;

    public MysqlSelectSqlGenerator(){
        this.sql = new StringBuilder();
    }

    @Override
    public SelectSqlGenerator select(String... columns) {
        return select(Arrays.asList(columns));
    }

    @Override
    public SelectSqlGenerator select(Collection<String> columns) {
        this.sql.append("SELECT ")
                .append(StringUtils.foreach("", "", ",", columns))
                .append(" ");
        return this;
    }

    @Override
    public SelectSqlGenerator from(String tableName) {
        this.sql.append(" FROM ").append(tableName);
        return this;
    }

    @Override
    public SelectSqlGenerator where(ConditionSqlGenerator conditionSqlGenerator) {
        this.conditionSqlGenerator = conditionSqlGenerator;
        return this;
    }

    @Override
    public List<Object> getParams() {
        if(this.conditionSqlGenerator != null){
            return this.conditionSqlGenerator.getParams();
        }
        return null;
    }

    @Override
    public String getString() {
        return sql.toString();
    }

    @Override
    public String getSql() {
        if(this.conditionSqlGenerator == null){
            return getString();
        }else{
            return this.sql.toString() + this.conditionSqlGenerator.getSql();
        }
    }


}
