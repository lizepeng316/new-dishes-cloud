## Spring Cloud 练习

### 1.工程结构
```
dishes-cloud               父工程   pom
  cloud-common             pom
     common-core              jar（公共的基础工具包）
     common-jwt               jar（创建、解析 JWT 令牌）
     common-web               jar（Servlet、Spring MVC）
     common-redis             jar（编写 Redis 工具，封装 StringRedisTemplate）
  cloud-model              jar（实体类、POJO 等）
  cloud-auth               jar（登录等微服务）      port = 9003
  cloud-gateway            jar（网关微服务）        port = 9999
  cloud-modules            pom（管理业务微服务）
    dishes-service           jar（食材、菜品微服务） port = 9001
    upload-service           jar（文件上传微服务）   port = 9002
    order-service            jar（订单微服务）      port = 9004
    cancel-service           jar（订单取消微服务）   port = 9005