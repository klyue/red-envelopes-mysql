package com.group6.redenvelopesmysql.controller;

import com.group6.redenvelopesmysql.entity.RedEnvelope;
import com.group6.redenvelopesmysql.entity.Wallet;
import com.group6.redenvelopesmysql.repository.RedEnvelopeRepository;
import com.group6.redenvelopesmysql.repository.WalletRepository;
import com.group6.redenvelopesmysql.service.RedEnvelopesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yang Xichun
 * @date 2021/11/13 17:19
 */
@RestController
public class WebController {

    @Autowired
    private RedEnvelopesService redEnvelopesService;

    @PostMapping(value = "/wallet/{id}")
    public Map<String, Object> getWallet(@PathVariable("id") int id) {
        Wallet wallet = redEnvelopesService.findWallet(id);
        List<RedEnvelope> redEnvelopes = redEnvelopesService.findRedEnvelopes(id);

        Map<String, Object> res = new HashMap<>();
        res.put("wallet", wallet);
        res.put("red_envelopes", redEnvelopes);

        return res;
    }
}
