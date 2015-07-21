package com.cmcc.edu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
public class User implements Serializable {
    private Long id; //编号
    private Long organizationId; //所属班级
    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐
    private String roles; //加密密码的盐
    private String mobile;
    private Integer type;
    private Long schoolId;
	private List<Long> roleIds; //拥有的角色列表
    private List<Long> idList;//删除使用存储的用户列表
    
    private String address;

	private Boolean locked = Boolean.FALSE;



    public User() {
    }
    public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    public String getCredentialsSalt() {
        return username + salt;
    }

    public List<Long> getRoleIds() {
        if(roleIds == null) {
            roleIds = new ArrayList<Long>();
        }
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }


    public String getRoleIdsStr() {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long roleId : roleIds) {
            s.append(roleId);
            s.append(",");
        }
        return s.toString();
    }

    public void setRoleIdsStr(String roleIdsStr) {
        if(StringUtils.isEmpty(roleIdsStr)) {
            return;
        }
        String[] roleIdStrs = roleIdsStr.split(",");
        for(String roleIdStr : roleIdStrs) {
            if(StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            getRoleIds().add(Long.valueOf(roleIdStr));
        }
    }
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

     
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

     
    public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
		if(StringUtils.isEmpty(roles)) {
            return;
        }
        String[] roleIdStrs = roles.split(",");
        for(String roleIdStr : roleIdStrs) {
            if(StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            getRoleIds().add(Long.valueOf(roleIdStr));
        }
	}

	public String toString() {
        return "User{" +
                "id=" + id +
                ", organizationId=" + organizationId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", roleIds=" + roleIds +
                ", locked=" + locked +
                '}';
    }
}