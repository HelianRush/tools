package com.fool.service;

import com.fool.entity.JimdoInvagation;

import java.util.List;

public interface JimdoInvagationService {
    List<JimdoInvagation> queryAll();

    void add(JimdoInvagation ji);

    void remove(JimdoInvagation ji);

    Long removeByScore(double score);
}
