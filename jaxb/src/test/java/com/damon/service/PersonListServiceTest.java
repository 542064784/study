package com.damon.service;

import com.damon.entity.Person;
import com.damon.entity.PersonList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Damon Chen
 * @date 2018/11/29
 */
@RunWith(SpringRunner.class)
public class PersonListServiceTest {

    @InjectMocks
    private PersonListService personListService;

    @Test
    public void beanListToXmlTest() throws JAXBException {
        Person person1 = new Person(1,"东方月初",20,"男");
        Person person2 = new Person(2,"苏苏",21,"女");
        Person person3 = new Person(3,"王权富贵",22,"男");
        Person person4 = new Person(4,"蓉蓉",23,"女");
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        PersonList personList = new PersonList(persons);
        File file = new File("D:\\repository\\jaxb\\src\\main\\resources\\personList.xml");
        personListService.beanListToXml2(file,personList);
    }

    @Test
    public void xmlToBeanTest() throws JAXBException, FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:personList.xml");
        PersonList personList = personListService.xmlToBean2(file, PersonList.class);
        System.out.println(personList);
    }
}