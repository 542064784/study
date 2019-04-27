package com.damon.demo;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 获得指定 class path 下的包中的所有Class对象
 *
 * @author Damon Chen
 * @date 2019/02/07
 */
@Component
public class GetClassByReflection {

    private static final Map<Class<?>,JpaRepository> REPOSITORY_MAP = new HashMap<>();

    @Autowired
    private ApplicationContext context;

    public void getClassDemo(){
        //  创建 reflections 对象
        Reflections reflections = new Reflections("packageName");
        // 获得所有的类上有 @Repository 注解的class   此方式必须在自定义的Repository上加上@Respsitory 注解
        // 此方法与 getReflectionByResource() 方法中获得到的class对象的使用方法相同
        Set<Class<?>> repositoryClazzs = reflections.getTypesAnnotatedWith(Repository.class);
        repositoryClazzs.forEach(clazz->{
            // 获得spring 容器中自动为此自定义Repository的接口的代理实现
            JpaRepository jpaRepository  = (JpaRepository) context.getBean(clazz);
            // 获得父接口 JpaRepository 中的泛型的第一个 自己是接口时使用
            Class<?> aClass = (Class<?>) ((ParameterizedType) clazz.getAnnotatedInterfaces()[0]).getActualTypeArguments()[0];
            //  获得父类或父接口中泛型的第一个 自己必须是实现类
            //Class<?> aClass = (Class<?>)((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
            REPOSITORY_MAP.put(aClass, jpaRepository);
        });

        // 此方法获得所有的自定义的Repository的class   并不用必须加上注解
        Set<Class<? extends JpaRepository>> subTypesOfJpaRepository = reflections.getSubTypesOf(JpaRepository.class);
    }


    /**
     *  获得指定package下的所有指定的class
     *
     * @param packageName  指定的package
     * @throws IOException  io exception
     * @throws ClassNotFoundException class not found exception
     * @throws IllegalAccessException illegal access exception
     * @throws InstantiationException instantiation exception
     */
    public void getReflectionByResource(String packageName) throws IOException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        Map<Class<?>,Object> map = new HashMap<>();
        String resource_fattern = "/**/*.class";
        // 将包名和classpath /**/*.class 结合起来  读取符合条件的java类
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(packageName) + resource_fattern;
        // 创建ResourcePatternResolver
        ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        // 获得所有的Resource 的数组
        Resource[] source = resourceLoader.getResources(pattern);
        // 获得readerFactory
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourceLoader);
        //遍历
        for (Resource resource : source) {
            // 判断是否有符合条件的存在
            if (resource.isReadable()) {
                // 获得 MetadataReader
                MetadataReader reader = readerFactory.getMetadataReader(resource);
                // 获得类全名
                String className = reader.getClassMetadata().getClassName();
                // 获得Class 对象
                Class<?> clazz = Class.forName(className);
                // 判断当前class上是否有 @Component 注解
                if (clazz.isAnnotationPresent(Component.class)){
                    map.put(clazz, clazz.newInstance());
                }
            }
        }
    }

    /**
     *  根据 Reflections 获得指定package 下所有class
     *  此方式需要maven坐标
     *                 <dependency>
     *                      <groupId>org.reflections</groupId>
     *                      <artifactId>reflections</artifactId>
     *                      <version>0.9.10</version>
     *                 </dependency>
     * @param packageName  package
     */
    public void getByReflections(String packageName){
        //创建reflections
        Reflections reflections = new Reflections(packageName);
        // 获得所有类上有Component 注解的class的set集合
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Component.class);
        // 获得所有JpaRepository的子接口的class的set集合
        Set<Class<? extends JpaRepository>> subTypesOf = reflections.getSubTypesOf(JpaRepository.class);
    }

}
