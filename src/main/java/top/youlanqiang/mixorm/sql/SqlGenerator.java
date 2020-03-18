package top.youlanqiang.mixorm.sql;


import top.youlanqiang.mixorm.domain.SqlEntity;

import java.util.List;

/**
 * @author youlanqiang
 */
public interface SqlGenerator {

    /**
     * 返回内部StringBuilder字符串
     * @return 字符串
     */
    String getString();

    /**
     * 放回SqlGenerator拼接的SQL语句
     * @return sql字符串
     */
    String getSql();

    /**
     * 返回params参数集合
     * @return 参数集合
     */
    List<Object> getParams();

    /**
     * 返回sqlEntity对象
     * @return sql entity对象
     */
    SqlEntity getSqlEntity();
}
