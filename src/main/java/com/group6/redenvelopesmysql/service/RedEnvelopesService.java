package com.group6.redenvelopesmysql.service;

import com.group6.redenvelopesmysql.entity.RedEnvelope;
import com.group6.redenvelopesmysql.entity.Wallet;
import com.group6.redenvelopesmysql.repository.RedEnvelopeRepository;
import com.group6.redenvelopesmysql.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 红包的相关操作，有的操作还和钱包有关
 *
 * @author Yang Xichun
 * @date 2021/11/14 17:01
 */
@Slf4j
@Service
public class RedEnvelopesService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private RedEnvelopeRepository redEnvelopeRepository;

    @Transactional(readOnly = true)
    public Wallet findWallet(int uid) {
        return walletRepository.findById(uid).get();
    }

    @Transactional(readOnly = true)
    public List<RedEnvelope> findRedEnvelopes(int uid) {
        return redEnvelopeRepository.findByUid(uid);
    }

    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public boolean insert(RedEnvelope redEnvelope) {
        redEnvelopeRepository.save(redEnvelope);

        return true;
    }

    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public boolean update(int eid, int uid, int value) {
        Wallet wallet = walletRepository.findByUid(uid);
        RedEnvelope redEnvelope = redEnvelopeRepository.findByEid(eid);
        if (wallet == null) {
            log.error("更新时没有找到对应 uid 的钱包：{}", uid);
            return false;
        }
        if (redEnvelope == null) {
            log.error("更新时没有找到对应 eid 的红包：{}", eid);
            return false;
        }
        wallet.setBalance(wallet.getBalance() + value);
        redEnvelope.setOpened(true);
        redEnvelopeRepository.save(redEnvelope);
        walletRepository.save(wallet);

        return true;
    }
}
