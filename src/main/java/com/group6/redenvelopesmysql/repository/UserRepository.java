package com.group6.redenvelopesmysql.repository;

import com.group6.redenvelopesmysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:49
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
