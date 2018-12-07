package org.gec.service;

import org.gec.model.Asset;
import org.gec.model.PageModel;
import org.gec.model.Student;

import java.util.List;

/**
 * @Author LZM
 * @Date 2018/12/6 14:44
 **/
public interface IStuService {

    void addStu(Student student);

    PageModel findStu(Integer page, Integer rows);

    Student getStu(String id);

    void updateStu(Student student);

    void deleteStu(String id);

    List<Student> findStu();

    /**
     批量添加学生
     @param students
     */
    void addStudent(List<Student> students);
}
