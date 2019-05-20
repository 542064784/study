package com.damon.demo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
/**
 *  获得泛型 的工具类
 *
 * @author Damon Chen
 * @date 2019/05/18
 */
public class GenericUtils {

    /**
     *  递归获得指定的父类的第一个泛型
     *
     * @param sourceClass  子类 或子类的子类
     * @param superClass   被查找的父类
     * @return             被查找的父类的第一个泛型
     */
    public static Class getSuperClassGeneric(final Class sourceClass,final Class superClass){
        Type type = sourceClass.getGenericSuperclass();
        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class<?> rawType = (Class<?>) parameterizedType.getRawType();
            if (rawType.equals(superClass)){
                return (Class) parameterizedType.getActualTypeArguments()[0];
            }
        }else {
            return getSuperClassGeneric((Class)type, superClass);
        }
        return null;
    }

    /**
     *  递归获得指定的父接口的第一个泛型
     *
     * @param sourceClass   接口的实现类
     * @param superClass    被查找的父接口
     * @return              被查找的父接口的第一个泛型
     */
    public static Class getSuperInterfaceGeneric(final Class sourceClass,final Class superClass){
        Type[] genericInterfaces = sourceClass.getGenericInterfaces();
        for (Type type : genericInterfaces){
            if (type instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class rawType = (Class) (parameterizedType).getRawType();
                if (rawType.equals(superClass)){
                    return (Class) parameterizedType.getActualTypeArguments()[0];
                }
            }else {
                Class aClass = (Class) type;
                Type[] interfaces = aClass.getGenericInterfaces();
                if (interfaces.length == 0){
                    continue;
                }else {
                    Class clazz = getSuperInterfaceGeneric(aClass, superClass);
                    if (Objects.isNull(clazz)){
                        continue;
                    }else {
                        return clazz;
                    }
                }
            }
        }
        return null;
    }

}
