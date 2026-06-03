package com.etoak.system.controller;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.etoak.common.constant.CommonConstant;
import com.etoak.common.redis.RedisService;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.vo.CodeVO;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    DefaultKaptcha kaptcha;

    @Autowired
    RedisService redisService;

    // pom.xml => cloud-model
    @GetMapping("/getCode")
    public ResultVO<CodeVO> getCode() throws IOException {
        // 1. 生成文本 1+1=2?@2
        String text = kaptcha.createText();
        String[] data = text.split(StrUtil.AT);
        // 2. 将答案data[1]存储到Redis
        String uuid = IdUtil.simpleUUID();
        //              code:uuid
        redisService.setex(CommonConstant.REDIS_CODE_PREFIX + uuid, data[1], 5, TimeUnit.MINUTES);
        // 3. 根据1+1=?  data[0]生成验证码图片 BufferedImage
        BufferedImage bufferedImage = kaptcha.createImage(data[0]);
        // 4. BufferedImage => ImageIO => ByteArrayOutputStream
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        // 5. 获取 byte[]
        byte[] byteArray = os.toByteArray();
        // 6. Base64Encoder 参数byte[] => base64编码格式
        String url = Base64Encoder.encode(byteArray);
        String codeUrl = "data:image/png;base64," + url;
        // 7. 组装返回数据CodeVO
        CodeVO vo = new CodeVO(uuid, codeUrl);
        // 8. 返回
        return ResultVO.success(vo);
    }

}
