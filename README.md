# pss
> Purchase Sell Stock Management System

[![CI-Tools](https://travis-ci.org/pss-dev/pss.svg?branch=master)](https://travis-ci.org/github/pss-dev/pss)

[^_^]:
    如果编码中有任何约定,请在此声明.

* 后台在 `java/` 下
* 前端在 `web/` 下

### 1. Build Project
#### 1.1 Build Develop Environment
``` shell script
gradlew[.bat] clean build
```

#### 1.2 Build Production Environment
``` shell script
gradlew[.bat] clean build -Pbuildenv=prod
```

### 2. Project Runner
#### 2.1 Startup
``` shell script
gradlew[.bat] server
Or
gradlew[.bat] server -Pbuildenv=prod # Product Mode
```

#### 2.2 Compile
```shell script
gradlew[.bat] classes # 编译 Java 
```

```shell script
gradlew[.bat] web:watch # watch 前端
Or
gradlew[.bat] web:watch -Pbuildenv=prod # watch product mode
```

#### 2.3 Build Release Jar

```shell script
gradlew[.bat] cleanAll bootJar -Pbuildenv=prod
```

#### 2.4 Run Release Jar

```shell script
java -jar pss-ms-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### 3. Server 端编码规范
#### 3.1 Controller
* 所有 Controller 的请求 URL 以 `/api/1.0/` 开头
* 所有 Controller 的 rest api 都使用 `swagger` document.

#### 3.2 Service 
* 事务放在 service 层
* Cache 放在 service 层, 所有的 ServiceImpl 都应该配置 Cache, 可参考 [DepartmentServiceImpl](https://github.com/pss-dev/pss/blob/master/java/src/main/java/com/pssdev/pss/service/impl/DepartmentServiceImpl.java).

#### 3.3 Dao
* 所有 Dao 都继承 `BaseDao` 就默认拥有简单 CURD 功能, 只有特殊业务场景或者需求时才需要在自己的 `daoImpl` 中去扩展
* DELETE 操作使用 entity 实例对象删除而不是 ID

#### 3.4 Audit/Log
* 所有 `Audit` 在 `Service` 层或者 `Controller` 层处理
* 使用 @Audit 注解, 可参考 `DepartmentServiceImpl`

### 4. 代码提交
* Fix bug 携带 bug 号, GitHub 会自动关联, 格式为 `#2 message`

### 5. 授权

> 对于授权的控制访问放在 `Controller` 层. 具体可参考 `com.pssdev.pss.controller.DepartmentController`

> 基本格式为

``` text
level/resource/operator
```

> `level`: 级别,有以下值
>   * **system**: 代表超级管理员
>   * **admin**: 代表非超级管理员的 Administrator
>   * **user**: 普通用户

> `resource`: 受授权管理的系统资源, 具体映射为:
``` javascript
resource: {
    baseInfo: "1",
    product: "2",
    unit: "4",
    price: "8",
    company: "16",
    depot: "32",
    department: "64",
    branch: "128",
    orderForm: "256",
    manage: "512",
    role: "1024",
    employee: "2048",
    account: "4096",
    statistic: "8192",
    log: "16384",
    revenue: "32768",
}
```

> `operator`: 对资源的访问, 具体可参看 `com.pssdev.pss.util.SecurityUtil#getOperatorPermission`

``` java
public static String getOperatorPermission(PermissionEnum permission) {
    switch(permission) {
        case READ:
            return "r";
        case WRITE:
            return "w";
        case DELETE:
            return "d";
        case VERIFY:
            return "v";
        case ADMIN:
            return ALL_PERMISSION;
        default:
            return "";
    }
}
```

