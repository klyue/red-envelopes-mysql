package com.group6.redenvelopesmysql.repository;

import com.group6.redenvelopesmysql.entity.RedEnvelope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:49
 */
public interface RedEnvelopeRepository extends JpaRepository<RedEnvelope, Integer> {

    List<RedEnvelope> findByUid(int uid);

}
