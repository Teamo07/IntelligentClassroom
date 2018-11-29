package org.gec.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.gec.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
	定义映射接口
*/
@Repository
public interface UserMapper {

	/**
	 * 添加用户
	 * @param user 保存用户的信息
	 */
	@Insert("insert into tb_user(id, name, pass, phone) values(#{id}, #{name}, password(#{pass}), #{phone})")
	public void addUser(User user);

	@Select("select * from tb_user where name = #{name} and pass = #{pass}")
	List<User> findUser(User user);
}
