package com.damon.reflections;

import com.damon.service.MyInterface;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Damon Chen
 * @date 2019/02/07
 */
@Component
public class MyReflections {

    /**
     * 获得指定目录下 所有类上有指定注解的类
     */
    public void getTypeByAnnotation(){
        //创建reflections
        Reflections reflections = new Reflections("com.damon.service");
        // 获得所有类上有Component 注解的类的set集合
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Component.class);
    }

    /**
     * 获得指定目录下 所有指定类型的子类
     */
    public void getTypeByParent(){
        //创建reflections
        Reflections reflections = new Reflections("com.damon.service");
        // 获得所有MyInterface 的子类型的set集合
        Set<Class<? extends MyInterface>> subTypesOf = reflections.getSubTypesOf(MyInterface.class);
    }

}
