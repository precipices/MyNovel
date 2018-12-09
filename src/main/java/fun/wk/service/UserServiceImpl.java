package fun.wk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.wk.dao.UserMapper;
/**
 * 编写业务层接口的实现类：UserService.java （切记要使用@Service("userService")类标识类，表示这是service）
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


}