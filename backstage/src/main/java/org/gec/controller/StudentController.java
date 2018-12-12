package org.gec.controller;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.gec.model.Asset;
import org.gec.model.PageModel;
import org.gec.model.Student;
import org.gec.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentController
 * @Author LZM
 * @Date 2018/12/6 14:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/stu")
public class StudentController extends BaseController{

    @Autowired
    private IStuService stuService;

    @RequestMapping(path="/getStu.do", produces="application/json;charset=utf-8")
    public PageModel getStu(Integer page, Integer rows) {
        System.out.println("page = " + page + ", rows = " + rows);
        return stuService.findStu(page, rows);
    }

    @RequestMapping(path="/addStu.do", produces="application/json;charset=utf-8")
    public Map addStu(Student student) {
        try {
            boolean flag=stuService.addStu(student);
            if (flag){
                return ajaxReturn(true, "添加成功");
            }
            return ajaxReturn(false, "添加失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发生错误，请稍后再试");
    }

    @RequestMapping(path="/loadStu.do", produces="application/json;charset=utf-8")
    public Student loadStu(String id) {
        return stuService.getStu(id);
    }

    @RequestMapping(path="/updateStu.do", produces="application/json;charset=utf-8")
    public Map updateStu(Student student) {
        try {
            stuService.updateStu(student);
            return ajaxReturn(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发送异常，请稍后再试");
    }

    @RequestMapping(path="/delStu.do", produces="application/json;charset=utf-8")
    public Map delStu(String id) {
        try {
            stuService.deleteStu(id);
            return ajaxReturn(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发送异常，请稍后再试");
    }

    /*
    导出学生表
     */
    @RequestMapping(path = "/exportStu.do")
    public void exportStudent(HttpServletResponse response) throws Exception {
        // 设置响应头
        response.setHeader("Content-Disposition", "attachement;filename="
                + URLEncoder.encode("学生统计表.xls", "UTF-8"));
        // 创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = wb.createSheet("学生统计表");
        // 创建标题行
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("学生姓名");
        row.createCell(2).setCellValue("学号");
        row.createCell(3).setCellValue("RFID");
        row.createCell(4).setCellValue("状态");
        //查询学生
        List<Student> students =stuService.findStu();
        for (int i = 1; i <= students.size(); i++) {
            Student student = students.get(i - 1);
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getNumber());
            row.createCell(3).setCellValue(student.getRfid());
            row.createCell(4).setCellValue(student.getStatus()==1 ? "已打卡" : "未打卡");
        }
        // 把工作簿输出
        wb.write(response.getOutputStream());
        // 关闭资源
        wb.close();
    }

    /*
       导入学生表
     */
    @RequestMapping(path="/importStu.do", produces="application/json;charset=utf-8")
    public Map importStudent(HttpServletRequest request, MultipartFile file) {
        HSSFWorkbook wb = null;
        try {
            //判断上传文件类型
            String contentType = file.getContentType();
            if (!"application/vnd.ms-excel".equals(contentType)) {
                return ajaxReturn(true, "上传文件不是Excel类型");
            }
            //第一步：把Excel文件解析成HSSFWorkbook对象；
            wb = new HSSFWorkbook(file.getInputStream());
            //第二步：通过该对象获取分页Sheet对象；
            HSSFSheet sheet = wb.getSheetAt(0);
            //第三步：获取总的行数；
            int lastRowNum = sheet.getLastRowNum();
            //该集合用于读取到的学生
//            List<Student> students = new ArrayList<Student>();
            List<Student> students = new ArrayList<>();
            Integer status=0;
            for (int i = 1; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i); //获取行
                String id = row.getCell(0).getStringCellValue(); //获取单元格数据，下标从0开始
                String name = row.getCell(1).getStringCellValue();
                String number = row.getCell(2).getStringCellValue();
                String rfid = row.getCell(3).getStringCellValue();
                String statu = row.getCell(4).getStringCellValue();
                if (statu.equals("已打卡")){
                    status=1;
                }
                students.add(new Student(id, name, number, rfid, status));
            }
            //第六步：把获取到的联系人的数据保存数据库中；
            boolean flag=stuService.addStudent(students);
            if (flag){
                return ajaxReturn(true, "导入成功");
            }
            return ajaxReturn(false, "导入失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                }
            }
        }
        return ajaxReturn(true, "导入失败");
    }
}
