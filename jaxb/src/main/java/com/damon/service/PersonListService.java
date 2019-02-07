package com.damon.service;

import com.damon.entity.PersonList;
import org.springframework.stereotype.Service;

import javax.xml.bind.*;
import java.io.File;

/**
 * @author Damon Chen
 * @date 2018/11/29
 */
@Service
public class PersonListService {

    /**
     * 对象转xml
     *
     * @param clz  要转换换的javaBean 的class
     * @param personList  转换的对象
     * @throws JAXBException
     */
    public void beanListToXml(Class clz, PersonList personList) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clz);
        Marshaller marshaller = jaxbContext.createMarshaller();
        File file = new File("D:\\repository\\jaxb\\src\\main\\resources\\personList.xml");
        marshaller.marshal(personList,file);
    }

    /**
     * person 对象转换成xml jdk1.7以上可使用
     *
     * @param file 要转换的文件
     * @param personList 要转换的java对象
     */
    public void beanListToXml2(File file,PersonList personList){
        JAXB.marshal(personList,file);
    }

    /**
     * 解析xml生成java对象
     *
     * @param file  xml文件
     * @param clz  要生成的对象class
     * @return   生成的对象
     * @throws JAXBException
     */
    public <T> T xmlToBean(File file,Class clz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(file);
    }

    /**
     * 解析xml生成java对象  jdk1.7以上可使用
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
