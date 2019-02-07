package com.damon.service;

import com.damon.entity.Person;
import org.springframework.stereotype.Service;

import javax.xml.bind.*;
import java.io.File;

/**
 * @author Damon Chen
 * @date 2018/11/29
 */
@Service
public class PersonService {

    /**
     * person对象转换成xml
     *
     * @param clz 转换的bean class
     * @param person 转换的person对象
     * @throws JAXBException
     */
    public void beanToXml1(Class clz, Person person) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clz);
        Marshaller marshaller = jaxbContext.createMarshaller();
        //格式化输出，即按标签自动换行，否则就是一行输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //设置编码（默认编码就是utf-8）
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //是否省略xml头信息，默认不省略（false）
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
        File file = new File("D:\\repository\\jaxb\\src\\main\\resources\\person.xml");
        marshaller.marshal(person,file);
    }

    /**
     * person 对象转换成xml jdk1.7以上可使用
     *
     * @param file 要转换的文件
     * @param person 要转换的java对象
     */
    public void beanToXml2(File file,Person person){
        JAXB.marshal(person,file);
    }

    /**
     * xml 转换成bean
     *
     * @param file xml文件
     * @param clz 要转换的bean class
     * @return 生成的对象
     * @throws JAXBException
     */
    public <T> T xmlToBean1(File file,Class clz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(file);
    }

    /**
     * 解析xml生成java对象
     *
     * @param file xml文件
     * @param clz 要生成的对象class
     * @return  生成的对象
     * @throws JAXBException
     */
    public <T> T xmlToBean2(File file,Class clz) throws JAXBException {
        return (T) JAXB.unmarshal(file,clz);
    }

}
