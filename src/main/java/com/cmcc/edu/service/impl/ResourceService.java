package com.cmcc.edu.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cmcc.edu.dao.ResourceMapper;
import com.cmcc.edu.entity.Resource;
import com.cmcc.edu.service.IResourceService;

@Service
public class ResourceService implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

	@Override
	public int createResource(Resource resource) {		
		return resourceMapper.insert(resource);
	}

	@Override
	public Resource updateResource(Resource resource) {
		resourceMapper.updateByPrimaryKey(resource);
		return resource;
	}

	@Override
	public void deleteResource(Long resourceId) {
		Resource resource = resourceMapper.selectByPrimaryKey(resourceId);
		resource.setMakeSelfAsParentIds();
		resourceMapper.deleteByPrimaryKey(resourceId);
		resourceMapper.deleteByParentids(resource);	
	}

	@Override
	public Resource findOne(Long resourceId) {
		Resource resource=resourceMapper.selectByPrimaryKey(resourceId);
		if(resource==null){
			return null;
		}
		return resource;
	}

	@Override
	public List<Resource> findAll() {
		return resourceMapper.findAll();
	}
	
	@Override
	 public Set<String> findPermissions(Set<Long> resourceIds) {
	        Set<String> permissions = new HashSet<String>();
	        for(Long resourceId : resourceIds) {
	            Resource resource = findOne(resourceId);
	            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
	                permissions.add(resource.getPermission());
	            }
	        }
	        return permissions;
	  }

	@Override
	public List<Resource> findMenus(Set<String> permissions) {
		 List<Resource> allResources = findAll();
	        List<Resource> menus = new ArrayList<Resource>();
	        for(Resource resource : allResources) {
 
	            if(!resource.getTypes().equals(Resource.ResourceType.menu.toString())) {
	                continue;
	            }
	            if(!hasPermission(permissions, resource)) {
	                continue;
	            }
	            menus.add(resource);
	        }
	        return menus;
	}
	
	@Override
	public List<Resource> findAllowed(Set<String> permissions) {
		 List<Resource> allResources = findAll();
	        List<Resource> menus = new ArrayList<Resource>();
	        for(Resource resource : allResources) {
	            if(resource.isRootNode()) {
	                continue;
	            }
	            if(!hasPermission(permissions, resource)) {
	                continue;
	            }
	            menus.add(resource);
	        }
	        return menus;
	}


	 private boolean hasPermission(Set<String> permissions, Resource resource) {
	        if(StringUtils.isEmpty(resource.getPermission())) {
	            return true;
	        }
	        for(String permission : permissions) {
	            WildcardPermission p1 = new WildcardPermission(permission);
	            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
	            if(p1.implies(p2) || p2.implies(p1)) {
	                return true;
	            }
	        }
	        return false;
	    }
 
     
  
 

}