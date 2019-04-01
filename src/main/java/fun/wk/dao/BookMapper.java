package fun.wk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
public interface BookMapper {
	/**
	 * 根据用户ID查询所有书
	 * @param i
	 * @return
	 */
	List queryBookByUserId(int i);
}
