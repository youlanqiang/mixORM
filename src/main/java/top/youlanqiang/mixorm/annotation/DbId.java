package top.youlanqiang.mixorm.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段ID
 * @author  youlanqiang
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbId {

    /**
     *  默认自增长
     */
    IdType type() default IdType.INPUT;

    String value() default "";
}
