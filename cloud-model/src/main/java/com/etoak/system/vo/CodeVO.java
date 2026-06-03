package com.etoak.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeVO {
    // code:uuid=值
    // redis中的key组成部分
    private String uuid;
    // BufferImage => base64编码形式
    private String codeUrl;

}
