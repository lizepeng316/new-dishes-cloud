package com.etoak.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.etoak.system.dto.LoginDTO;
import com.etoak.system.entity.User;
import com.etoak.system.vo.TokenVO;
import jakarta.validation.Valid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author etoak
 * @since 2026-04-29
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param loginDTO 请求参数
     * @return TokenVO
     */
    TokenVO login(@Valid LoginDTO loginDTO);
}
