# logisticsManagement
this project has three parts of implements

## 1.quick start-configuration

you need to implement the configuration of springboot to make spring bootstrap well

you have to replace the "xxx" in pineapple-logistics/src/main/resources/application.yml

1.email settings your email which to send certificate code when sign up new user

```yml
  mail:
    host: smtp.163.com //if you not use 163 as email sender，you may change this
    username: xxx  //your eamil address
    password: xxx  //you need to apply for smtp service and password you got
    port: 465
    properties:
      from: xxx // your email address
      mail:
        smtp:
          socketFactory:
            class: javax.net.ssl.SSLSocketFactory
```

2.MySQL configurations

```yml
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/management?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai //your MySQL address you need create a table named "management"
    username: xxx   //your MySQL username
    password: xxx  //your MySQL password
    hikari:
      pool-name: DateHikariCP
      minimum-idle:  5
      idle-timeout:  180000
      maximum-pool-size: 10
      auto-commit: true
      max-lifetime: 180000
      connection-timeout: 30000
      connection-test-query: SELECT 1
```

3. redis configurations

```yml
 redis:
      port: 6379
      host: xxx //your redis host
      database: 0
      timeout: 10000ms
      password: xxx //your redis password
      lettuce:
        pool:
          max-active: 8
          max-wait: 10000ms
          max-idle: 200
          min-idle: 5
```

## 2.start the project at SpringBoot

start the project at PineappleLogisticsApplication.java. Please make sure that port 8080 not being occupied.

## 3.design of SQL structure

**1.t_user** to save the user's data 

you may look this table contents in entity/User

**2.t_order** to save the order

you may look this table contents in entity/Order

**3.t_Payment** to save the payment of Order

you may look this table contents in entity/Payment

**4.t_Goods** to save the goods details

you may look this table contents in entity/Goods

## 4.Utils classes for programming

BeanUtil for easy to transform between Java Object to HashMap

CookieUtil for easy set cookies in Http request

DateUtill for get current datestamps

DigestUtil for encode password to save in datebase

PhoneUtil to check if a number sequence is valid phone number

SnowflakeUtil for generate snowflake num(which based on the time and index of the machine and is always auto increment) to save as the primary index in MySQL

ThreadLocalUtils to make ThreadLocal more easier to save the data for User

UUIDUtil to generate the UUID index

## 5.Wrapper for request and response

to make a standare format for message which will being sending to front-end. Using the ResultBean to answer it.There is also many standare answers in ResultEnum

## 6.Interceptor when login

we using SpringSecurity for login check. the configuration in config/SecurityConfig

## 7.Factory model function to create Orders

In factory/ dir ,we implement factory function for Goods Order and payment to make it easy to create new Orders

## 8.Mybatis to access Mysql

we using mapper dir to implement to create delete update select function.