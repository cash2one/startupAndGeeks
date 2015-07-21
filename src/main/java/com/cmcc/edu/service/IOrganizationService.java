package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Organization;

public interface IOrganizationService {
	
	public int createOrganization(Organization organization);
	
	public Organization updateOrganization(Organization organization);
	
	public void deleteOrganization(Long organizationId);
	
	public Organization findOne(Long organizationId);
	 
	public List<Organization> findAll();
	
	public Object findAllWithExclude(Organization excludeOraganization);

    public void move(Organization source, Organization target);
	
    
}
