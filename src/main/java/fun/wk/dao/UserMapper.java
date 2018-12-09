package fun.wk.dao;

import org.apache.ibatis.annotations.Mapper;

import fun.wk.entity.User;

/**
 * 持久层接口： UserMapper.java (定义操作数据库的方法) 这里切记要用@Mapper注解标识
 */
@Mapper
public interface UserMapper {

	public void insert(User user);

	public void update(User user);

	public void delete(int id);

	public User getUserById(int id);

	public User getUserByName(String username);

}