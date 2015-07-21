package com.cmcc.edu.service.impl;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.AttenceTimeMapper;
import com.cmcc.edu.dao.AttendanceMapper;
import com.cmcc.edu.entity.AttenceTime;
import com.cmcc.edu.entity.Attendance;
import com.cmcc.edu.service.IAttendanceService;
import com.cmcc.edu.util.pagehelper.PageHelper;

@Service
public class AttendanceService implements IAttendanceService {
	@Autowired
	private AttendanceMapper attendanceDao;
	
	@Autowired
	private  AttenceTimeMapper attenceTimeDao;
	
	private  AttenceTime attendceTime ;
	


	/**
	 * 日考勤
	 */
	@Override
	public List<Attendance> selectByList(Attendance attendance) {
		attendceTime = attenceTimeDao.selectByPrimaryKey(0L);
		List<Attendance> list=attendanceDao.selectByList(attendance);
		if(list.size()>0){
			for (Attendance attendance2 : list) {
				StringBuffer sb = new StringBuffer();
				boolean arrive = timeArrive(attendance2.getArrivetime());
				boolean leave = timeLeave(attendance2.getLeavetime());
				  if(arrive){
					  sb.append(" 迟到 ");
				  }
				  if(leave){
					 sb.append(" 早退 ");
				  }
				  if(!arrive && !leave){
					  sb.append("正常");
				  }
				  if(attendance2.getCheckdate()!=null){
					  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					  attendance2.setTime(sdf.format(attendance2.getCheckdate()));
				  }
				  attendance2.setStateStr(sb.toString());
			}
		}
		return list;
	}
	public boolean timeArrive(Time time){
		return time.after(attendceTime.getArrivetime());
	}
	public boolean timeLeave(Time time){
	    return time.before(attendceTime.getLeavetime());
	}
	/**
	 * 月考勤
	 */
	@Override
	public List<Attendance> selectByListMonth(Attendance attendance) {
		List<Attendance> list=attendanceDao.selectByList(attendance);
		attendceTime = attenceTimeDao.selectByPrimaryKey(0L);
		Map<String,List<Attendance>> map=new HashMap<String,List<Attendance>>();
		for (Attendance att : list) {
			if(map.containsKey(att.getStuId().toString())){
				List<Attendance> lists=map.get(att.getStuId().toString());
				lists.add(att);
			}else{
				List<Attendance> list2=new ArrayList<Attendance>();
				list2.add(att);
				map.put(att.getStuId().toString(), list2);
			}
		}
		List<Attendance> attList=new ArrayList<Attendance>();
		for (Map.Entry<String, List<Attendance>> m : map.entrySet()) 
		  {
	  		    //获取所有的值
	  		    List<Attendance> lists = m.getValue();
	  		    Attendance att=new Attendance();
	  		    if(lists.get(0).getCheckdate()!=null){
	  		    	 att.setCheckdate(lists.get(0).getCheckdate());
	  		    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
	  		    	att.setTime(sdf.format(lists.get(0).getCheckdate()));
	  		    }
	  		   
	  		    att.setName(lists.get(0).getName());
	  		    int late=0;
	  		    int leave=0;
	  		    for (Attendance attendance2 : lists) {
					if(timeArrive(attendance2.getArrivetime())){
						late++;
					}
					if(timeLeave(attendance2.getLeavetime())){
						leave++;
					}
				}  
		  		  att.setLate(late+"");
		  		  att.setLeave(leave+"");
		  		  if(late==0 && leave==0){
		  			att.setStateStr("正常");
		  		  }else{
		  			att.setStateStr("异常");
		  		  }  
		  		  
		  		  
		  		  attList.add(att);
		  }	
		return attList;
	}
	@Override
	public List<Attendance> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Attendance> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Attendance> find(int pageNumber, int itemsOnPage, Attendance t) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		attendceTime = attenceTimeDao.selectByPrimaryKey(0L);
		List<Attendance> list=attendanceDao.selectByList(t);
/*		if(list.size()>0){
			for (Attendance attendance2 : list) {
				StringBuffer sb = new StringBuffer();
				boolean arrive = timeArrive(attendance2.getArrivetime());
				boolean leave = timeLeave(attendance2.getLeavetime());
				  if(arrive){
					  sb.append(" 迟到 ");
				  }
				  if(leave){
					 sb.append(" 早退 ");
				  }
				  if(!arrive && !leave){
					  sb.append("正常");
				  }
				  if(attendance2.getCheckdate()!=null){
					  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					  attendance2.setTime(sdf.format(attendance2.getCheckdate()));
				  }
				  attendance2.setStateStr(sb.toString());
			}
		}*/
		return list;
	}
	@Override
	public List<Attendance> findByMonth(int pageNumber, int itemsOnPage,
			Attendance t) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		List<Attendance> list=attendanceDao.selectByList(t);
		attendceTime = attenceTimeDao.selectByPrimaryKey(0L);
		Map<String,List<Attendance>> map=new HashMap<String,List<Attendance>>();
		for (Attendance att : list) {
			if(map.containsKey(att.getStuId().toString())){
				List<Attendance> lists=map.get(att.getStuId().toString());
				lists.add(att);
			}else{
				List<Attendance> list2=new ArrayList<Attendance>();
				list2.add(att);
				map.put(att.getStuId().toString(), list2);
			}
		}
		List<Attendance> attList=new ArrayList<Attendance>();
		for (Map.Entry<String, List<Attendance>> m : map.entrySet()) 
		  {
	  		    //获取所有的值
	  		    List<Attendance> lists = m.getValue();
	  		    Attendance att=new Attendance();
	  		    if(lists.get(0).getCheckdate()!=null){
	  		    	 att.setCheckdate(lists.get(0).getCheckdate());
	  		    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
	  		    	att.setTime(sdf.format(lists.get(0).getCheckdate()));
	  		    }
	  		   
	  		    att.setName(lists.get(0).getName());
	  		    int late=0;
	  		    int leave=0;
	  		    for (Attendance attendance2 : lists) {
					if(timeArrive(attendance2.getArrivetime())){
						late++;
					}
					if(timeLeave(attendance2.getLeavetime())){
						leave++;
					}
				}  
		  		  att.setLate(late+"");
		  		  att.setLeave(leave+"");
		  		  if(late==0 && leave==0){
		  			att.setStateStr("正常");
		  		  }else{
		  			att.setStateStr("异常");
		  		  }  
		  		  
		  		  
		  		  attList.add(att);
		  }	
		return attList;
	}

   
}
