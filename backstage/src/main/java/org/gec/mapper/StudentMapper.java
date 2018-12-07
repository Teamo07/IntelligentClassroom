package org.gec.mapper;


import org.apache.ibatis.annotations.*;
import org.gec.model.Asset;
import org.gec.model.PageModel;
import org.gec.model.Student;

import java.util.List;

public interface StudentMapper {


    @Delete("delete from student where id = #{id}")
    void deleteStu(Student student);

    @Update("update student set name = #{name}, number = #{number}, rfid = #{rfid}, status = #{status} where id = #{id}")
    void updateStu(Student student);




    @Select("select count(*) from student")
    int count();

    @Select("select * from student limit #{start}, #{pageSize}")
    @ResultType(Student.class)
    List<Asset> getStu(PageModel pageModel);

    @Insert("insert into student values(#{id}, #{name}, #{number}, #{rfid}, #{status})")
    void addStu(Student student);

    @Select("select * from student where id = #{id}")
    Student getStuById(Student student);

    @Select("select * from student")
    List<Student> getAllStudent();
}