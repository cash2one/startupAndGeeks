package com.chuanggu.app.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.StudentMapper;
import com.chuanggu.app.entity.Student;
import com.chuanggu.app.service.IStudentService;
import com.chuanggu.app.util.pagehelper.PageHelper;
@Service
public class StudentService implements IStudentService{
	
	@Autowired
	private StudentMapper studentMapper;
	
	
	@Override
	public List<Student> findStudentById(Integer stuId) {
		// TODO Auto-generated method stub
		return studentMapper.findStudentById(stuId);
	}
	
	@Override
	public List<Student> findStudentByClassId(Integer classId) {
		// TODO Auto-generated method stub
		return studentMapper.findStudentByClassId(classId);
	}

	/**
	 * excel导入学生信息
	 */
	@Override
	public void formExcel(Integer classId,Integer gradeId, String filename) {
		//String result = "success";
        /** Excel文件的存放位置。注意是正斜线 */
        // String fileToBeRead = "F:\\" + fileFileName;
		
		// 创建对Excel工作簿文件的引用
        HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(
			        filename));
	        HSSFSheet sheet = workbook.getSheetAt(0);

	        int j = 1;//从第2行开始堵数据
	        Student stu=new Student();
            stu.setClassId(classId);
            stu.setGradeId(gradeId);
	        // 第在excel中读取一条数据就将其插入到数据库中
	        while (j < sheet.getPhysicalNumberOfRows()) {
	            HSSFRow row = sheet.getRow(j);
	            
	            for (int i = 0; i <= 2; i++) {
	                HSSFCell cell = row.getCell((short) i);
	               
	                if(i==0){
	                	stu.setName(cell.getStringCellValue().toString());
	                }else if(i==1){
	                	cell.setCellType(cell.CELL_TYPE_STRING);
	                	stu.setMobile(cell.getStringCellValue());
	                }else if(i==2){
	                	stu.setBirthday(cell.getDateCellValue());
	                }
	                
	            }  
	            studentMapper.insert(stu); 
	            j++;    
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量上传学生信息
	 * @throws ParseException 
	 */
	@Override
	public void batch(String information,Integer classId,Integer gradeId) throws ParseException {
		String[] stuList=information.split(";");
		for (String s : stuList) {
			String[] stu=s.split("、");
			Student student=new Student();
			student.setClassId(classId);
			student.setGradeId(gradeId);
			for (int i = 0; i < stu.length; i++) {
				if(i==0){
					student.setName(stu[i]);
				}else if(i==1){
					student.setMobile(stu[i]);
				}else if (i==2){
					String birthday=stu[i];
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					student.setBirthday(sdf.parse(birthday));
				}
			}
			studentMapper.insert(student);
		}		
	}
	
	
	@Override
	public List<Student> findStuByClassIdAndGradeId(Student record) {
		// TODO Auto-generated method stub
		return studentMapper.findStuByClassIdAndGradeId(record);
	}
	
	@Override
	public List<Student> findStuTreeBySchoolId(Long schoolId) {
		// TODO Auto-generated method stub
		return studentMapper.findStuTreeBySchoolId(schoolId);
	}
	
	@Override
	public List<Student> selectStudentList(Student record) {
		// TODO Auto-generated method stub
		return studentMapper.selectStudentList(record);
	}
	
		
	@Override
	public List<Student> findClassBySchoolId(Long schoolId) {
		// TODO Auto-generated method stub
		return studentMapper.findClassBySchoolId(schoolId);
	}
	
	@Override
	public List<Student> findStuList(Student record) {
		// TODO Auto-generated method stub
		return studentMapper.findStuList(record);
	}
	
	
	@Override
	public List<Student> find(int pageNumber, int itemsOnPage, Student t) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNumber, itemsOnPage);
		return studentMapper.findStuList(t);
	}
	
	@Override
	public List<Student> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Student> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Student student) {
		return studentMapper.insert(student);
	}

}
