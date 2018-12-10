package org.gec.mapper;

import org.gec.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

    @Autowired
    StudentMapper studentMapper;

    @Test
    public void addStu(){
        Student student=new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName("帅哥");
        student.setNumber("1234567890");
        student.setRfid("ERTYHGFDERFD");
        student.setStatus(1);
        int i = studentMapper.addStu(student);
        System.out.println(i);
        Assert.assertEquals(1,i);
    }
}