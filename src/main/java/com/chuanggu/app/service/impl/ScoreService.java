package com.chuanggu.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.ExamMapper;
import com.chuanggu.app.dao.NoticeMapper;
import com.chuanggu.app.dao.ScoreMapper;
import com.chuanggu.app.dao.UserMapper;
import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.Notice;


import com.chuanggu.app.entity.Score;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.service.INoticeService;
import com.chuanggu.app.service.IScoreService;



@Service
public class ScoreService implements IScoreService{
	
	@Autowired
	private ScoreMapper scoreMapper;
	@Autowired
	private ExamMapper examMapper;
	@Autowired
	private UserMapper userMapper;
	
	private int enterstate=0;//录入状态

	@Override
	public int insert(Score record) {
		return scoreMapper.insert(record);
	}

	@Override
	public int update(Score record) {
		return scoreMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Long id) {
		return scoreMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Score find(Long id) {
		return scoreMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Score> selectScoreList(Score record) {
		return scoreMapper.selectScoreList(record);
	}
	
	@Override
	public List<Score> selectScoreByExamId(Long examId) {
		// TODO Auto-generated method stub
		return scoreMapper.selectScoreByExamId(examId);
	}
	


	/**
	 * 导入成绩
	 * @param classId  班级id
	 * @param examId  考试id
	 * @param examName  成绩单名称
	 * @param filename  导入的excel的路径
	 */
	@Override
	public void formExcel(Integer classId,Integer examId,String examName,String filename) {
		String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userMapper.findByUsername(username);
    	
		if(classId!=null && examId!=null &&examName!=null &&classId!=0 && examId!=0 && !examName.equals("")&& filename!=null && !filename.equals("")){
			Exam exam=examMapper.selectByPrimaryKey((long)examId);
			if(exam!=null){
				exam.setResname(examName);
				if(user!=null){
					exam.setSendId(user.getId());
				}	
				exam.setEnterstate(enterstate+"");
				examMapper.updateByPrimaryKeySelective(exam);
			}
			
			List<Score> list=new ArrayList<Score>();
			String result = "success";
	        /** Excel文件的存放位置。注意是正斜线 */
	        // String fileToBeRead = "F:\\" + fileFileName;
			
			// 创建对Excel工作簿文件的引用
	        HSSFWorkbook workbook;
			try {
				workbook = new HSSFWorkbook(new FileInputStream(
				        filename));
		        HSSFSheet sheet = workbook.getSheetAt(0);

		        int j = 1;//从第2行开始堵数据
		        // 第在excel中读取一条数据就将其插入到数据库中
		        while (j < sheet.getPhysicalNumberOfRows()) {
		            HSSFRow row = sheet.getRow(j);
		            Score score=new Score();
		            score.setClassId(classId);
		            score.setExamId(examId);
		            
		            for (int i = 0; i <= 3; i++) {
		                HSSFCell cell = row.getCell((short) i);

		                if (i == 0) {
		                	int a=0;
		                	for (Score s : list) {
								if(cell.getStringCellValue().equals(s.getStuname())){
									a++;
								}
							}
		                	if(a>0){
		                		score.setStuname(cell.getStringCellValue()+"1");
		                	}else{
		                		score.setStuname(cell.getStringCellValue());
		                	}	
		                	list.add(score);
		                } else if (i == 1)
		                	score.setScore((Double)cell.getNumericCellValue());
		                
		            }
		            scoreMapper.insert(score);
		            j++;
		            
		        }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
       
		
	}

	@Override
	public void downLoadExcel(String filename, List<Score> scoreList) {
	
		WritableWorkbook book;
		try {
			book = Workbook.createWorkbook(new File(filename));
			WritableSheet sheet = book.createSheet("成绩表", 0);
			
			WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 11,  
            WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
            jxl.format.Colour.BLACK); 
			WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义  
            wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色  
            wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
            wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //

            
            Map<String,List<Score>> map=new HashMap<String,List<Score>>();
    		for (Score score : scoreList) {
    			if(map.containsKey(score.getClassId().toString())){
    				List<Score> list=map.get(score.getClassId().toString());
    				list.add(score);
    			}else{
    				List<Score> list=new ArrayList<Score>();
    				list.add(score);
    				map.put(score.getClassId().toString(), list);
    			}
    		}
    		int row=0;
    		
    		for (Map.Entry<String, List<Score>> m : map.entrySet()) 
    		  {
	    		    //获取所有的值
	    		    List<Score> lists = m.getValue();
 
    		   		Label title = new Label(0, row, lists.get(0).getClassName()+"  "+lists.get(0).getCourseName()+"期末考试统计",wcf_title);
		            sheet.mergeCells(0, row, 2, 0);
		            sheet.addCell(title);
					
					Label label = new Label(0, row+1, "班级平均分",wcf_title);
			        sheet.addCell(label);
			        label = new Label(1, row+1, "年级平均分",wcf_title);
			        sheet.addCell(label);
			        label = new Label(2, row+1, "年级排名",wcf_title);
			        sheet.addCell(label);
			        
			        Label examId = new Label(0, row+2, lists.get(0).getClassAverageMark().toString(),wcf_title);
		            sheet.addCell(examId);
		            Label sco = new Label(1, row+2, lists.get(0).getGradeAverageMark().toString(),wcf_title);
		            sheet.addCell(sco);
		            Label classId = new Label(2, row+2, lists.get(0).getGradeRanking().toString(),wcf_title);
		            sheet.addCell(classId);
		            
		            Label label2 = new Label(0,row+ 3, "学生姓名",wcf_title);
			        sheet.addCell(label2);
			        label2 = new Label(1, row+3, lists.get(0).getCourseName()+"分数",wcf_title);
			        sheet.addCell(label2);
			        label2 = new Label(2, row+3, "班级排名",wcf_title);
			        sheet.addCell(label2);
      			 
	      			 for (Score scores : lists) {       
      				 	Label stuName = new Label(0, row+4, scores.getStuname(),wcf_title);
			            sheet.addCell(stuName);
			            Label sc = new Label(1, row+4, scores.getScore().toString(),wcf_title);
			            sheet.addCell(sc);
			            Label num = new Label(2, row+4, scores.getClassRanking().toString(),wcf_title);
			            sheet.addCell(num);
			            row++;	        
	      			 }
	      			row=row+6;
    	    }    
	        
	        book.write();
	        book.close();
	        
	       
	       
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
       
		
	}

	@Override
	public int deleteByexamId(Long examId) {
		return scoreMapper.deleteByPrimaryKey(examId);
	}


	

	

	
	
	
}
