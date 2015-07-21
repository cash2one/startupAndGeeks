package com.chuanggu.app.service;


import java.util.List;
import java.util.Set;

import com.chuanggu.app.entity.Resource;

public interface IResourceService {


	public int createResource(Resource resource);
	
	public Resource updateResource(Resource resource);
	
	public void deleteResource(Long resourceId);
	
	public Resource findOne(Long resourceId);
	
	public  List<Resource> findAll();
	/**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
	public Set<String> findPermissions(Set<Long> resourceIds);
	
   

    
  

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);

	List<Resource> findAllowed(Set<String> permissions);
}
