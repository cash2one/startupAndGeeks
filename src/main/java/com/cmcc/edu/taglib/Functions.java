package com.cmcc.edu.taglib;

import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.cmcc.edu.entity.Grade;
import com.cmcc.edu.entity.Class;
import com.cmcc.edu.entity.Organization;
import com.cmcc.edu.entity.Resource;
import com.cmcc.edu.entity.Role;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.IGradeService;
import com.cmcc.edu.service.IOrganizationService;
import com.cmcc.edu.service.IResourceService;
import com.cmcc.edu.service.IRoleService;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.spring.SpringUtils;

public class Functions {

    public static boolean in(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

    public static String organizationName(Long organizationId) {
        Organization organization = getOrganizationService().findOne(organizationId);
        if(organization == null) {
            return "";
        }
        return organization.getName();
    }

    public static String organizationNames(Collection<Long> organizationIds) {
        if(CollectionUtils.isEmpty(organizationIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long organizationId : organizationIds) {
            Organization organization = getOrganizationService().findOne(organizationId);
            if(organization == null) {
                return "";
            }
            s.append(organization.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
    public static String roleName(Long roleId) {
        Role role = getRoleService().findOne(roleId);
        if(role == null) {
            return "";
        }
        return role.getDescription();
    }

    public static String roleNames(Collection<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long roleId : roleIds) {
            Role role = getRoleService().findOne(roleId);
            if(role == null) {
                return "";
            }
            s.append(role.getRole());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
    public static String resourceName(Long resourceId) {
        Resource resource = getResourceService().findOne(resourceId);
        if(resource == null) {
            return "";
        }
        return resource.getName();
    }
    public static String resourceNames(Collection<Long> resourceIds) {
        if(CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long resourceId : resourceIds) {
            Resource resource = getResourceService().findOne(resourceId);
            if(resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
    
    public static String gradeName(Integer gradeId) {
        Grade grade = getGradeService().findOne(gradeId);
        if(grade == null) {
            return "";
        }
        return grade.getName();
    }
    
    public static String className(Integer gradeId) {
        Class clazz = getClassService().findOne(gradeId);
        if(clazz == null) {
            return "";
        }
        return clazz.getName();
    }
    
    public static List<Student> getStudentByClass(Integer classId) {
    	List<Student> list = getStudentService().findStudentByClassId(classId);
        if(list == null || list.size() ==0) {
            return null;
        }
        return list;
    }
    
    private static IOrganizationService organizationService;
    private static IRoleService roleService;
    private static IResourceService resourceService;
    private static IGradeService gradeService;
    private static IClassService classService;
    private static IStudentService studentService;

    public static IOrganizationService getOrganizationService() {
        if(organizationService == null) {
            organizationService = SpringUtils.getBean(IOrganizationService.class);
        }
        return organizationService;
    }

    public static IRoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtils.getBean(IRoleService.class);
        }
        return roleService;
    }

    public static IResourceService getResourceService() {
        if(resourceService == null) {
            resourceService = SpringUtils.getBean(IResourceService.class);
        }
        return resourceService;
    }
    
    public static IGradeService getGradeService() {
        if(gradeService == null) {
        	gradeService = SpringUtils.getBean(IGradeService.class);
        }
        return gradeService;
    }
    
    public static IClassService getClassService() {
        if(classService == null) {
        	classService = SpringUtils.getBean(IClassService.class);
        }
        return classService;
    }
    
    public static IStudentService getStudentService() {
        if(studentService == null) {
        	studentService = SpringUtils.getBean(IStudentService.class);
        }
        return studentService;
    }
}

