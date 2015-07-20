package cn.kawa.studio.repository.mybatis;

import java.util.List;
import java.util.Map;

import cn.kawa.studio.entity.User;

/**
 * ͨ��@MapperScannerConfigurerɨ��Ŀ¼�е����нӿ�, ��̬��Spring Context������ʵ��.
 * �������Ʊ�����Mapper.xml�б���һ��.
 * 
 * @author
 */
@MyBatisRepository
public interface UserMybatisDao {

	User get(Long id);

	List<User> search(Map<String, Object> parameters);

	void save(User user);

	void delete(Long id);
}