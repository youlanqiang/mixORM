package top.youlanqiang.mixorm;

import top.youlanqiang.mixorm.domain.DataEntity;
import top.youlanqiang.mixorm.domain.PageEntity;
import top.youlanqiang.mixorm.mapper.QueryMapper;
import top.youlanqiang.mixorm.mate.EntityMate;
import top.youlanqiang.mixorm.mate.EntityMateContainer;
import top.youlanqiang.mixorm.sql.ConditionSqlGenerator;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class Mixorm {

    private Mixorm(){}

    public static <T> BaseDataEntity<T> create(Class<T> clazz){
        BaseDataEntity<T> entity = new BaseDataEntity<>(clazz);
        return entity;
    }


    static class BaseDataEntity<T>  implements DataEntity<T> {

        private QueryMapper<T> queryMapper;

        private Connection connection;

        public BaseDataEntity(Class<T> clazz){
            EntityMate<T> mate = EntityMateContainer.getInstance().get(clazz);
            this.queryMapper = new QueryMapper<>(mate);
        }

        @Override
        public void use(Connection connection){
            this.connection = connection;
        }

        @Override
        public int insert(T entity) {

            return 0;
        }

        @Override
        public int deleteById(Object id) {
            return 0;
        }

        @Override
        public int deleteByMap(Map<String, Object> map) {
            return 0;
        }

        @Override
        public int deleteByCondition(ConditionSqlGenerator sql) {
            return 0;
        }

        @Override
        public int deleteBatchIds(Collection<Object> idList) {
            return 0;
        }

        @Override
        public int updateById(T entity) {
            return 0;
        }

        @Override
        public int update(T entity, ConditionSqlGenerator sql) {
            return 0;
        }

        @Override
        public T selectById(Object id) {
            return null;
        }

        @Override
        public List<T> selectByMap(Map<String, Object> map) {
            return null;
        }

        @Override
        public T selectOne(ConditionSqlGenerator sql) {
            return null;
        }

        @Override
        public Integer selectCount(ConditionSqlGenerator sql) {
            return null;
        }

        @Override
        public List<T> selectList(ConditionSqlGenerator sql) {
            return null;
        }

        @Override
        public PageEntity<T> selectPage(int current, int size) {
            return null;
        }

        @Override
        public PageEntity<T> selectPage(int current, int size, ConditionSqlGenerator sql) {
            return null;
        }
    }
}
