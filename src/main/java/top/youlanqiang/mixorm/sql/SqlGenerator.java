package top.youlanqiang.mixorm.sql;


import java.util.List;

/**
 * @author youlanqiang
 */
public interface SqlGenerator {

    String getString();

    String getSql();

    List<Object> getParams();
}
