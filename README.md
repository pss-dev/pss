# pss
> Purchase Sell Stock Management System

[![CI-Tools](https://travis-ci.org/pss-dev/pss.svg?branch=master)](https://travis-ci.org/github/pss-dev/pss)

[^_^]:
    如果编码中有任何约定,请在此声明.

* 后台在 `java/` 下
* 前端在 `web/` 下

### 1. Build Project
``` shell script
gradlew[.bat] clean build
```

### 2. Startup Project
``` shell script
gradlew[.bat] bootRun # 先编译后启动
# or
gradlew[.bat] server # 不会编译 classes
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

### 4. 代码提交
* Fix bug 携带 bug 号, GitHub 会自动关联, 格式为 `#2 message`

