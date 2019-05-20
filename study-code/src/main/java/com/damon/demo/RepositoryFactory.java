package com.damon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author Damon Chen
 * @date 2019/5/18
 */
@Component
public class RepositoryFactory {

    /**
     *  key:JpaRepository's generic   value:jpaRepository instance
     */
    private static final Map<Class,JpaRepository> JPA_REPOSITORY_MAP = new ConcurrentHashMap<>();

    @Autowired
    private ApplicationContext context;

    /**
     *  通过泛型 获得对应的jpaRepository代理实现
     *
     * @param clazz  泛型class
     * @return       jpaRepository 代理实现
     */
    public JpaRepository getJpaRepository(Class clazz){
        if (JPA_REPOSITORY_MAP.containsKey(clazz)){
            return JPA_REPOSITORY_MAP.get(clazz);
        }
        throw new RuntimeException("not found jpaRepository by class  " +clazz);
    }

    /**
     *  初始化JPA_REPOSITORY_MAP
     */
    @PostConstruct
    public void init(){
        //  获得spring容器中所有的jpaRepository代理实现类
        Map<String, JpaRepository> jpaRepositoryMap = context.getBeansOfType(JpaRepository.class);
        for (Map.Entry<String,JpaRepository> entry : jpaRepositoryMap.entrySet()){
            JpaRepository jpaRepository = entry.getValue();
            //  获得对应的entity的class
            Class generic = GenericUtils.getSuperInterfaceGeneric(jpaRepository.getClass(), JpaRepository.class);
            if (!JPA_REPOSITORY_MAP.containsKey(generic)){
                JPA_REPOSITORY_MAP.put(generic,jpaRepository);
            }
        }
    }

}
