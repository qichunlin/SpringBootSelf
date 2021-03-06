package com.legend.springcache.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.legend.springcache.entity.UserInfo;
import com.legend.springcache.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author legend
 * @version 1.0
 * @description
 * @date 2021/1/20
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    /**
     * 模拟数据库存储数据
     */
    private static Map<Integer, UserInfo> userInfoMap = new HashMap<>(16);

    static {
        for (int i = 0; i < 20; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(i);
            userInfo.setAge(i + 10);
            userInfo.setName(i + "123");
            userInfo.setSex("男");
            userInfoMap.put(i, userInfo);
        }
        log.info("初始化map数据：{}", userInfoMap.toString());
    }

    @Autowired
    Cache<String, Object> caffeineCache;

    @Override
    public void addUserInfo(UserInfo userInfo) {
        log.info("create userinfo");
        userInfoMap.put(userInfo.getId(), userInfo);
        // 加入缓存
        caffeineCache.put(String.valueOf(userInfo.getId()), userInfo);
    }

    @Override
    public UserInfo getByName(Integer id) {
        // 先从缓存读取
        caffeineCache.getIfPresent(id);
        UserInfo userInfo = (UserInfo) caffeineCache.asMap().get(String.valueOf(id));
        if (userInfo != null) {
            log.info("get userinfo from caffeineCache");
            return userInfo;
        }
        // 如果缓存中不存在，则从库中查找
        log.info("get userinfo from DB");
        userInfo = userInfoMap.get(id);
        // 如果用户信息不为空，则加入缓存
        if (userInfo != null) {
            caffeineCache.put(String.valueOf(userInfo.getId()), userInfo);
        }
        return userInfo;
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        log.info("update userinfo");
        if (!userInfoMap.containsKey(userInfo.getId())) {
            return null;
        }
        // 取旧的值
        UserInfo oldUserInfo = userInfoMap.get(userInfo.getId());

        // 替换内容
//        if (!StringUtils.isEmpty(oldUserInfo.getAge())) {
//            oldUserInfo.setAge(userInfo.getAge());
//        }
//        if (!StringUtils.isEmpty(oldUserInfo.getName())) {
//            oldUserInfo.setName(userInfo.getName());
//        }
//        if (!StringUtils.isEmpty(oldUserInfo.getSex())) {
//            oldUserInfo.setSex(userInfo.getSex());
//        }

        //或者第二种方式
        BeanUtils.copyProperties(userInfo, oldUserInfo);
        log.info("修改后的的数据oldUserInfo：{}", oldUserInfo);

        // 将新的对象存储，更新旧对象信息
        userInfoMap.put(oldUserInfo.getId(), oldUserInfo);
        // 替换缓存中的值
        caffeineCache.put(String.valueOf(oldUserInfo.getId()), oldUserInfo);
        return oldUserInfo;
    }

    @Override
    public void deleteById(Integer id) {
        log.info("delete userinfo");
        userInfoMap.remove(id);
        // 从缓存中删除
        caffeineCache.asMap().remove(String.valueOf(id));
    }
}
