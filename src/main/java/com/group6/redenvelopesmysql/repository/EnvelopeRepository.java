package com.group6.redenvelopesmysql.repository;

import com.group6.redenvelopesmysql.entity.Envelope;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:49
 */
public interface EnvelopeRepository extends JpaRepository<Envelope, Integer> {
}
