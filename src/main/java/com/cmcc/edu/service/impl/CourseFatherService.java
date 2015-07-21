package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import com.cmcc.edu.dao.CourseFatherMapper;
import com.cmcc.edu.dao.CourseSetupMapper;
import com.cmcc.edu.entity.CourseFather;
import com.cmcc.edu.entity.CourseSetup;
import com.cmcc.edu.service.ICourseFatherService;
import com.cmcc.edu.service.ICourseSetupService;


@Service
public class CourseFatherService implements ICourseFatherService{
	
	@Autowired
	private CourseFatherMapper courseFatherMapper;

	@Override
	public int insert(CourseFather record) {
		return courseFatherMapper.insert(record);
	}

	@Override
	public void insertForIds(String ids,Integer id) {
		if(ids!=null && !"".equals(ids)){
			CourseFather courseFather =new CourseFather();
			courseFather.setState(1);
			courseFather.setCourseSetupId(id);
			int i=1;
			String[] str=ids.split(",");
			for (String s : str) {
				if(s!=null && !"".equals(s)){
					courseFather.setCourseId(Integer.parseInt(s));
					courseFather.setNumber(i);	
					courseFatherMapper.insert(courseFather);
				}
				i++;
			}
		}
		
	}

	
	

	
	
	
}
