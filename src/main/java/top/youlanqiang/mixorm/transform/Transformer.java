package top.youlanqiang.mixorm.transform;

import top.youlanqiang.mixorm.Mixorm;
import top.youlanqiang.mixorm.mate.EntityField;
import top.youlanqiang.mixorm.mate.EntityMate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * 用来处理ResultSet映射为对象的类
 * @author youlanqiang
 */
public class Transformer<T> {


    private  EntityMate<T> entityMate;

    public Transformer(EntityMate<T> entityMate) {
        this.entityMate = entityMate;
    }

    /**
     * 根据数据库查询结果集构造一个实体类对象
     *
     * @param resultSet 数据库结果集
     * @return 实体类对象
     */
    public T transform(ResultSet resultSet) {
        Class<T> tClass = entityMate.getClazz();
        T result = null;
        try {
            Constructor<T> constructor = tClass.getConstructor( null);
            result = constructor.newInstance( null);
            EntityField id = entityMate.getIdEntity();
            if(entityMate.hasId()) {
                Method idMethod = tClass.getMethod(id.getSetMethod(), id.getColumnType());
                if (Mixorm.getInstance().getConfig().isDebug()) {
                    System.out.println("ColumnName:" + id.getColumnName() + " MethodName:" + idMethod.getName()
                            + " ClassType:" + resultSet.getObject(id.getColumnName()).getClass().getName());
                }
                idMethod.invoke(result, resultSet.getObject(id.getColumnName()));
            }
            Map<String, EntityField> fields = entityMate.getFields();
            for (String key : fields.keySet()) {

                EntityField field = fields.get(key);
                Method method = tClass.getMethod(field.getSetMethod(), field.getColumnType());
                if(resultSet.getObject(field.getColumnName()) != null) {
                    if (Mixorm.getInstance().getConfig().isDebug()) {
                        System.out.println("ColumnName:" + field.getColumnName() + " MethodName:" + method.getName()
                                + " ClassType:" + resultSet.getObject(field.getColumnName()).getClass().getName());
                    }
                    method.invoke(result, resultSet.getObject(field.getColumnName()));
                }
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
