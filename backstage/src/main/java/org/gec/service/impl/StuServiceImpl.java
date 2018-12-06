package org.gec.service.impl;

import org.gec.mapper.StudentMapper;
import org.gec.model.Asset;
import org.gec.model.PageModel;
import org.gec.model.Student;
import org.gec.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName StuServiceImpl
 * @Author LZM
 * @Date 2018/12/6 14:55
 * @Version 1.0
 **/
@Service
public class StuServiceImpl implements IStuService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStu(Student student) {
        student.setId(UUID.randomUUID().toString()); //生成UUID唯一值
        studentMapper.addStu(student);
    }

    @Override
    public PageModel findStu(Integer page, Integer rows) {
        //计算开始查询的位置
        int start = (page - 1) * rows;
        PageModel pageModel = new PageModel();
        pageModel.setStart(start);;
        pageModel.setPageSize(rows);
        List<Asset> data = studentMapper.getStu(pageModel);
        int total = studentMapper.count();

        pageModel.setTotal(total);
        pageModel.setRows(data);
        return pageModel;
    }

    @Override
    public Student getStu(String id) {
        Student student = new Student();
        student.setId(id);
        return studentMapper.getStuById(student);

    }


    @Override
    public void updateStu(Student student) {
        studentMapper.updateStu(student);
    }

    @Override
    public void deleteStu(String id) {
        Student student = new Student();
        student.setId(id);
        studentMapper.deleteStu(student);
    }
}
