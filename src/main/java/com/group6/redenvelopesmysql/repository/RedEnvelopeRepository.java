package com.group6.redenvelopesmysql.repository;

import com.group6.redenvelopesmysql.entity.RedEnvelope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:49
 */
public interface RedEnvelopeRepository extends CrudRepository<RedEnvelope, Integer> {

    RedEnvelope findByEid(int eid);

    List<RedEnvelope> findByUid(int uid);

}
