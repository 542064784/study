package com.damon.service;

import com.damon.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Damon Chen
 * @date 2018/11/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void beanToXmlTest() throws JAXBException, FileNotFoundException {
        Person person = new Person();
        person.setId(1);
        person.setName("王权富贵");
        person.setAge(20);
        person.setSex("男");
        File file = new File("D:\\repository\\jaxb\\src\\main\\resources\\person.xml");
        personService.beanToXml2(file,person);
    }

    @Test
    public void xmlToBeanTest() throws FileNotFoundException, JAXBException {
        File file = ResourceUtils.getFile("classpath:person.xml");
        Person person = personService.xmlToBean2(file, Person.class);
        System.out.println(person);
    }
}