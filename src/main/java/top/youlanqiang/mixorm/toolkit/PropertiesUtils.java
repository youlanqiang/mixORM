package top.youlanqiang.mixorm.toolkit;

import top.youlanqiang.mixorm.exceptions.ParseEntityException;

import java.util.Locale;

/**
 * @author youlanqiang
 */
public final class PropertiesUtils {

    private PropertiesUtils(){}


    public static String toFieldName(String name){
        if(name.startsWith("get") || name.startsWith("set")){
            name = name.substring(3);
        } else if(name.startsWith("is")){
            name = name.substring(2);
        } else{
            throw new ParseEntityException("属性名称有误.");
        }

        if(name.length() == 1 || (name.length() >1 && Character.isUpperCase(name.charAt(1))) ){
            name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        }
        return name;
    }

    public static boolean isGetter(String name){

        return (name.startsWith("get") && name.length() > 3)
                || (name.startsWith("is") && name.length() > 2) ;
    }


    public static boolean isSetter(String name){
        return name.startsWith("set") && name.length() > 3;
    }


}
