package com.demo.base.repository;

import com.demo.base.domain.User;
import com.demo.base.mapper.UserMapper;
import com.demo.base.util.annotation.ResourceFinder;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UserRepository extends ServiceImpl<UserMapper, User> implements ResourceFinder<User> {
    public Page<User> CursorPagination(Page<User> page, Serializable lastID) {
        return super.page(page, QueryWrapper.create().gt(User::getUuid, lastID, lastID != null));
    }

    @Override
    public User getEntity(Serializable id) {
        return super.getById(id);
    }
}