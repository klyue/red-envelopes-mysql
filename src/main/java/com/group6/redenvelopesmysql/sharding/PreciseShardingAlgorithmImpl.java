package com.group6.redenvelopesmysql.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:59
 */
public class PreciseShardingAlgorithmImpl implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        String dbName = "ds";
        Integer value = preciseShardingValue.getValue();
        dbName += value;
        for (String each : collection) {
            if (each.equals(dbName)) {
                return each;
            }
        }

        throw new IllegalArgumentException();
    }
}
