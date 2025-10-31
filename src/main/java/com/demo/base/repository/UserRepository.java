package com.demo.base.repository;

import com.demo.base.domain.User;
import com.demo.base.mapper.UserMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends ServiceImpl<UserMapper, User> {
}
