package com.cmcc.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.OrganizationMapper;
import com.cmcc.edu.entity.Organization;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IOrganizationService;
import com.cmcc.edu.service.IUserService;

@Service
public class OrganizationService implements IOrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

   
	@Override
	public int createOrganization(Organization organization) {		
		return organizationMapper.insert(organization);
	}


	@Override
	public Organization updateOrganization(Organization organization) {
		organizationMapper.updateByPrimaryKey(organization);
		return organization;
	}


	@Override
	public void deleteOrganization(Long organizationId) {
		Organization organization=organizationMapper.selectByPrimaryKey(organizationId);
		organizationMapper.deleteByPrimaryKey(organizationId);
		organizationMapper.deleteByPrientids(organization);
		
	}


	@Override
	public Organization findOne(Long organizationId) {
		List<Organization> list=organizationMapper.findOne(organizationId);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}


	@Override
	public List<Organization> findAll() {
		return organizationMapper.findAll();
	}


	@Override
	public List<Organization> findAllWithExclude(Organization excludeOraganization) {
		return organizationMapper.findAllWithExclude(excludeOraganization);
	}


	@Override
	public void move(Organization source, Organization target) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("targetId", target.getId());
		map.put("targetParentids", target.getParentIds());
		map.put("targetMake", target.getMakeSelfAsParentIds());
		map.put("sourceId", source.getId());
		map.put("sourceMake", source.getMakeSelfAsParentIds());
		organizationMapper.moveSource(map);
		organizationMapper.moveSourceDescendants(map);	
	}
 
     
    
 

}