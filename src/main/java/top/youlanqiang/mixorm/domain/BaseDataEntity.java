package top.youlanqiang.mixorm.domain;

import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BaseDataEntity<T>  implements DataEntity{

    public BaseDataEntity(Class<T> clazz){

    }

    @Override
    public int insert(Object entity) {
        return 0;
    }

    @Override
    public int deleteById(Object id) {
        return 0;
    }

    @Override
    public int deleteByMap(Map map) {
        return 0;
    }

    @Override
    public int deleteByCondition(ConditionSqlGenerator sql) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection idList) {
        return 0;
    }

    @Override
    public int updateById(Object entity) {
        return 0;
    }

    @Override
    public int update(Object entity, ConditionSqlGenerator sql) {
        return 0;
    }

    @Override
    public Object selectById(Object id) {
        return null;
    }

    @Override
    public List selectByMap(Map map) {
        return null;
    }

    @Override
    public Object selectOne(ConditionSqlGenerator sql) {
        return null;
    }

    @Override
    public Integer selectCount(ConditionSqlGenerator sql) {
        return null;
    }

    @Override
    public List selectList(ConditionSqlGenerator sql) {
        return null;
    }

    @Override
    public PageEntity selectPage(int current, int size) {
        return null;
    }

    @Override
    public PageEntity selectPage(int current, int size, ConditionSqlGenerator sql) {
        return null;
    }
}
