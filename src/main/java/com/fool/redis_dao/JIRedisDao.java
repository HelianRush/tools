package com.fool.redis_dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fool.sys_common.RedisCode;
import com.fool.entity.JimdoInvagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class JIRedisDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 查询全部
    public List<JimdoInvagation> queryAll() {
        List<JimdoInvagation> list = new ArrayList<>();
        Set<String> jiSet = stringRedisTemplate.opsForZSet().range(RedisCode.JI, 0, -1);
        for (String temp : jiSet) {
            JimdoInvagation jiTemp = JSON.parseObject(temp, JimdoInvagation.class);
            list.add(jiTemp);
        }
        return list;
    }

    // 新增
    public Boolean add(JimdoInvagation ji) {
        String strJI = JSONObject.toJSONString(ji);
        Boolean flag = stringRedisTemplate.opsForZSet().add(RedisCode.JI, strJI, ji.getScore());
        return flag;
    }

    // 修改
    public Long edit(JimdoInvagation ji) {
        Long flag = null;
        return flag;
    }

    // 删除
    public Long remove(JimdoInvagation ji) {
        String strJI = JSONObject.toJSONString(ji);
        Long flag = stringRedisTemplate.opsForZSet().remove(RedisCode.JI, strJI);
        return flag;
    }

    // 删除根据Score
    public Long removeByScore(double score) {
        Long flag = stringRedisTemplate.opsForZSet().removeRangeByScore(RedisCode.JI, score, score);
        return flag;
    }

    // 获取最大Score
    public double getMaxScore() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(RedisCode.JI, 0, -1);
        // zReverseRange (RedisCode.JI, 0, -1);
        // zReverseRangeByScoreWithScores
        // zReverseRangeWithScores

        double[] scores = new double[typedTuples.size()];

        int count = 0;
        for (ZSetOperations.TypedTuple<String> temp : typedTuples) {
            Double score = temp.getScore();
            scores[count] = score;
            count++;
        }
        System.out.println(scores[0]);
        return scores[0];
    }

}
