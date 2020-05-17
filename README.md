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
* 所有 Controller 的请求 URL 以 `/api/1.0/` 开头
* 所有 Controller 的 rest api 都使用 `swagger` document.
* 事务放在 service 层

### 4. 代码提交
* Fix bug 携带 bug 号, GitHub 会自动关联, 格式为 `#2 message`

