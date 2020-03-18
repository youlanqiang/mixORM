package top.youlanqiang.mixorm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表名
 * @author  youlanqiang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTable {

    /**
     * 如果使用默认名称，那么UserItem类会映射为user_item
     */
    String value() default "";

    /**
     * 字段自动映射
     */
    boolean autoMapper() default false;

    /**
     * 字段映射模式
     */
    MapperMode mapperMode() default MapperMode.Standard;
}
