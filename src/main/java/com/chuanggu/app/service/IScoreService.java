package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.ExamType;
import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.Score;



public interface IScoreService {
	
	public int insert(Score record);
	
	public int update(Score record);
	
	public int delete(Long id);
	
	public Score find(Long id);
	
	public List<Score> selectScoreList(Score record);
	
	List<Score> selectScoreByExamId(Long examId);
	
	int deleteByexamId(Long examId);

	/**
	 * 导入成绩单
	 * @param classId  班级id
	 * @param examId  考试id
	 * @param examName  成绩单名称
	 * @param filename  导入的excel的路径
	 */
	public void formExcel(Integer classId,Integer examId,String examName,String filename);
	
	public void downLoadExcel(String filename, List<Score> scoreList);

}
