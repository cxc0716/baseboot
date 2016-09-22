package com.cxc.mapper;


import com.cxc.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author:chenxinchao
 * date:2016-09-21 15:52
 * desc:com.cxc.mapper
 */
@Mapper
public interface UserMapper {

	public User queryUserById(int id);

	public void updateUserInfo(User user);
}
