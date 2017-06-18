package com.cxc.mapper;


import com.cxc.domain.HiUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * author:chenxinchao
 * date:2016-09-21 15:52
 * desc:com.cxc.mapper
 */
@Mapper
public interface UserMapper {

	public HiUser queryUserById(int id);

	public int updatePassword(HiUser user);

	public HiUser queryByUsernameAndPassword(HiUser user);
}
