package com.etoak.system.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.common.constant.CommonConstant;
import com.etoak.common.exception.ParamException;
import com.etoak.common.jwt.JwtUtil;
import com.etoak.common.redis.RedisService;
import com.etoak.system.dto.LoginDTO;
import com.etoak.system.entity.User;
import com.etoak.system.mapper.UserMapper;
import com.etoak.system.service.UserService;
import com.etoak.system.vo.TokenVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author etoak
 * @since 2026-04-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    RedisService redisService;

    public UserServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public TokenVO login(LoginDTO loginDTO) {
        // 1. 验证码校验
        String code = redisService.get(CommonConstant.REDIS_CODE_PREFIX + loginDTO.getUuid());
        if (!Strings.CI.equals(code, loginDTO.getCode())){
            throw new ParamException("验证码错误");
        }

        // 2. 密码校验
        String password = MD5.create().digestHex(loginDTO.getPassword());
        User user = lambdaQuery().eq(User::getUsername, loginDTO.getUsername())
                .eq(User::getPassword, password)
                .one();
        if (ObjectUtils.isEmpty( user)){
            throw new ParamException("用户名或密码错误");
        }
        // 3. 生成token
        Map<String,Object> userMap = new HashMap<>();
        userMap.put(CommonConstant.LOGIN_USER_ID, user.getId());
        userMap.put(CommonConstant.LOGIN_USERNAME, user.getUsername());
        String jwt = JwtUtil.create(userMap);
        return new TokenVO(user.getUsername(), jwt);
    }
}
