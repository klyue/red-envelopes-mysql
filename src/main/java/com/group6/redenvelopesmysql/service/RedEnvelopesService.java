package com.group6.redenvelopesmysql.service;

import com.group6.redenvelopesmysql.entity.RedEnvelope;
import com.group6.redenvelopesmysql.entity.Wallet;
import com.group6.redenvelopesmysql.repository.RedEnvelopeRepository;
import com.group6.redenvelopesmysql.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
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

    @Transactional
    public boolean insert(RedEnvelope redEnvelope) {
        redEnvelopeRepository.save(redEnvelope);

        return true;
    }

    @Transactional
    public boolean update(int eid, int uid, int value) {
//        int n1 = redEnvelopeRepository.updateOpened(true, eid);
//        int n2 = walletRepository.updateBalance(value, uid);
//        log.info("n1: {}, n2: {}", n1, n2);

        Wallet wallet = walletRepository.findByUid(uid);
        RedEnvelope redEnvelope = redEnvelopeRepository.findByEid(eid);
        if (wallet == null) {
            log.info("wallet null");
        }
        if (redEnvelope == null) {
            log.info("redEnvelope null");
        }
        wallet.setBalance(wallet.getBalance() + value);
        redEnvelope.setOpened(true);
        walletRepository.save(wallet);
        redEnvelopeRepository.save(redEnvelope);

        return true;
    }
}
