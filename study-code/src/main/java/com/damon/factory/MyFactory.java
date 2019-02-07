package com.damon.factory;

import com.damon.service.MyInterface;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author Damon Chen
 * @date 2019/02/07
 */
@Component
public class MyFactory implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initMyInterface(applicationContext);
    }

    /**
     * 获得所有MyInterface接口的实现类
     *
     * @param applicationContext
     */
    private void initMyInterface(ApplicationContext applicationContext) {
        // 获得所有myInterface接口的实现  key为类名首字母小写，value是对象
        Map<String, MyInterface> myInterfaceMap = applicationContext.getBeansOfType(MyInterface.class);
    }
}
