1. Thymeleaf  + Shiro 出现下面的问题`java.lang.NoClassDefFoundError: org/thymeleaf/dialect/AbstractDialect`

   添加依赖，要用springboot1.5，使用2.0还会报错

```
<thymeleaf-layout-dialect.version>2.2.2</thymeleaf-layout-dialect.version>
```

```
<dependency>
    <groupId>com.github.theborakompanioni</groupId>
    <artifactId>thymeleaf-extras-shiro</artifactId>
    <version>2.0.0</version>
</dependency>
```

```
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf</artifactId>
    <version>3.0.9.RELEASE</version>
</dependency>
```

2. Controller 方法使用 返回值 String 渲染不出来来页面是因为没有添加

   ```
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-thymeleaf</artifactId>
   </dependency
   ```

如果pom文件报错，说明和之前的meven下载文件冲突了，建议把所有.m2/下载文件删除，重新下载

3. `No message found under code 'productname.required' for locale 'null'.`说明国际化配置文件出现了问题，没有配置好，访问不到该配置文件的code，需要在yml添加

   ```
   spring:
     messages:
         #国际化资源文件路径
       basename: i18n/messages
   ```

一定要控制好yml文件的格式，不然会无效，验证方法是ctrl+B能够访问到