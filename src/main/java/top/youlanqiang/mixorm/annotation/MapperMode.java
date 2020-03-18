package top.youlanqiang.mixorm.annotation;

/**
 * 字段的映射规则
 * @author youlanqiang
 */
public enum MapperMode {

    /**
     * 标准映射 userAge => userAge
     */
    Standard,
    /**
     * 驼峰映射 userAge => user_age
     */
    CamelCase

}
