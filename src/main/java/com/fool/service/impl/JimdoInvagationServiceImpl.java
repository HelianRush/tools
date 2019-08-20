package com.fool.service.impl;

import com.fool.redis_dao.JIRedisDao;
import com.fool.entity.JimdoInvagation;
import com.fool.service.JimdoInvagationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JimdoInvagationServiceImpl implements JimdoInvagationService {

    @Autowired
    JIRedisDao jiRedisDao;

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<JimdoInvagation> queryAll() {
        return jiRedisDao.queryAll();
    }

    /**
     * 新增
     *
     * @param ji
     */
    @Override
    public void add(JimdoInvagation ji) {
        // ji.setIndexID(SystemCode.GitID(JimdoInvagation.class));
        if (ji.getScore() == 0) {
            double score = jiRedisDao.getMaxScore() + 1.0;
            ji.setScore(score);
        }
        jiRedisDao.add(ji);
    }

    /**
     * 删除
     *
     * @param ji
     */
    @Override
    public void remove(JimdoInvagation ji) {
        jiRedisDao.remove(ji);
    }

    /**
     * 删除根据Score
     *
     * @param score
     * @return
     */
    @Override
    public Long removeByScore(double score) {
        return jiRedisDao.removeByScore(score);
    }

}
