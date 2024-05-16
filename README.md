# Linzepore / ctbcbank
中國信托商業銀行API調用案例

### 参考文档
本项目是基于中國信托商業銀行提供的API文档开发的，开发方式为URL的方式
文档一般在中國信托商業銀行测试环境特点管理系统下载，具体文档如下：
* [POS_URL_手冊_1.7.6.pdf]()

### 依赖
1：上传jar包到本地maven仓库
```shell
mvn install:install-file -Dfile=【newposapi_j_npg.jar本地路径】 -DgroupId=com.hyweb -DartifactId=posapi_npg -Dversion=1.0.0 -Dpackaging=jar  
```

2：使用依赖
```xml
<!-- 中國信托商業銀行依赖 -->
<dependency>
	<groupId>com.hyweb</groupId>
	<artifactId>posapi_npg</artifactId>
	<version>1.0.0</version>
</dependency>
```

### 流程
1. 申请测试账号获取特店編號、MerchantID等信息
2. 填写`application.yaml`配置文件
3. 上传jar包到本地maven仓库
4. 启动项目
5. `http://127.0.0.1:7979/ctbc-bank/get-url-enc` API调用
6. 访问`sslauth.html`填入上一步获取的`urlenc`、`merID`等信息
7. 点击提交按钮，会跳转到中國信托商業銀行的支付页面
8. 模拟支付
9. 查看回调接口返回的数据

### API调用
http://127.0.0.1:7979/ctbc-bank/get-url-enc
- 请求方式：POST
- 请求参数：
    ```json
    {
        "lidm": "2964842056672184",
        "purchAmt": 3188
    }
    ```
- 返回参数：
    ```json
    {
        "data": {
            "urlenc": "1F9SFASDFGFGDSG332341456JHGFJFDGD285ED5744821F5A973CDBE0B0BB83F7124EC2CEC9258B798FCF6A9BECECE3C33F07DFD0EF7F7B021EDE7F23F233069D5B3640D98E79285793864551CDF97F941A114ADE794CAA1960D0076F613DFA259CCE89739E516C68439E69786903F2E5C2BBB2B811353697D0BFC3112A40537B42D7C80E04B32D86C2AFCEBD88FCDE7A978EFBBEB0AA285CDFA1B5B218543F09892DA85F2CDB1D49B6393061604675DAD1E26C28B4E01C1120E9252D204CFE69CD5E6744CC623A3D9F9",
            "merID": "88888"
        },
        "message": "success",
        "code": "SUCCESS"
    }
    ```
