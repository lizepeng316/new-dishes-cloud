package com.etoak.system.controller;



import com.etoak.common.vo.ResultVO;
import com.etoak.system.dto.LoginDTO;
import com.etoak.system.service.UserService;
import com.etoak.system.vo.TokenVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author etoak
 * @since 2026-04-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResultVO<TokenVO> login(@Valid @RequestBody LoginDTO loginDTO) {
        TokenVO tokenVO = userService.login(loginDTO);
        return ResultVO.success(tokenVO);
    }
}

