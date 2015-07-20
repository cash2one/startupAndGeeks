package cn.kawa.studio.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import cn.kawa.studio.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}

