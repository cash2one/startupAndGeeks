package com.cmcc.edu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.cmcc.edu.dao.UserDao;
import com.cmcc.edu.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public User createUser(final User user) {
        final String sql = "insert into sys_user(organization_id, username, password, salt, role_ids, locked) values(?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
             
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setLong(count++, user.getOrganizationId());
                psst.setString(count++, user.getUsername());
                psst.setString(count++, user.getPassword());
                psst.setString(count++, user.getSalt());
                psst.setString(count++, user.getRoleIdsStr());
                psst.setBoolean(count++, user.getLocked());
                return psst;
            }
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public User updateUser(User user) {
        String sql = "update sys_user set organization_id=?,username=?, password=?, salt=?, role_ids=?, locked=? where id=?";
        jdbcTemplate.update(
                sql,
                user.getOrganizationId(), user.getUsername(), user.getPassword(), user.getSalt(), user.getRoleIdsStr(), user.getLocked(), user.getId());
        return user;
    }

    public void deleteUser(Long userId) {
        String sql = "delete from sys_user where id=?";
        jdbcTemplate.update(sql, userId);
    }

     
    public User findOne(Long userId) {
        String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_user where id=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

     
    public List<User> findAll() {
        String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }


     
    public User findByUsername(String username) {
        String sql = "select id as id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_user  where username=?"
        			+" union "
        			+"select id as id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_admin_user where username=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), new Object[]{username,username});
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

	@Override
	public int deleteBatchRecord(final List<Long> idList) {
		final String sql = "delete from sys_user where id =?";
		int[] count = null;
		count = jdbcTemplate.batchUpdate(  
		            sql,  
		            new BatchPreparedStatementSetter() {  
		           //为prepared statement设置参数。这个方法将在整个过程中被调用的次数  
		        public void setValues(PreparedStatement ps, int i) throws SQLException {  
		                ps.setLong(1, idList.get(i));  

		              }

				@Override
				public int getBatchSize() {
					return idList.size();
				}  
		            }); 
		  
		  return count.length;
	}
}
