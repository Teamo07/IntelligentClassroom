package org.gec.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.gec.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

	@Select("select * from tb_user where name = #{username} and pass = #{userpass}")
	User getUser(Map map);
}
