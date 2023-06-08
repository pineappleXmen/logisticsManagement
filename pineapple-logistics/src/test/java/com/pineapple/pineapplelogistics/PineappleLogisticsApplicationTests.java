package com.pineapple.pineapplelogistics;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.entity.Goods;
import com.pineapple.pineapplelogistics.entity.Order;
import com.pineapple.pineapplelogistics.entity.Payment;
import com.pineapple.pineapplelogistics.entity.User;
import com.pineapple.pineapplelogistics.mapper.PaymentMapper;
import com.pineapple.pineapplelogistics.service.IGoodsService;
import com.pineapple.pineapplelogistics.service.IOrderService;
import com.pineapple.pineapplelogistics.service.IPaymentService;
import com.pineapple.pineapplelogistics.utils.*;
import com.pineapple.pineapplelogistics.vo.OrderVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PineappleLogisticsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getTimeStamp(){
        System.out.println(DateUtils.now());
    }

    @Test
    void getUUID(){
        String res = "12346";
        String s1 = DigestUtil.encodePassword(res,UUIDUtils.get());
        System.out.printf(s1);
    }

    @Test
    void snowflake() throws InterruptedException {
        SnowflakeUtils sfu = new SnowflakeUtils(System.currentTimeMillis(),1);
        long last = 0;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    long l = sfu.get();
                    System.out.printf("[thread] %d, %d %d",Thread.currentThread().getId(),l,System.currentTimeMillis());
                    System.out.println();
                }
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }

    @Test
    void phone(){
        boolean valid = PhoneUtils.isValid("1592727818");
        System.out.println(valid);
    }

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    void testRedisTemplate() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User user = new User();


        Map entries = redisTemplate.opsForHash().entries("key");
        System.out.println(entries);
    }

    @Test
    void threadlocalTest(){
        User user = new User();
        ThreadlocalUtils.put("user",user);
        Object o = ThreadlocalUtils.get("user");
        System.out.println(o);
        ThreadlocalUtils.removeKey("user");
    }
    @Test
    void beanMap(){
        User user = new User();
        user.setId(1l);
        user.setAvatar("1111");
        user.setSalt("aaa");
        Map<String, Object> stringObjectMap = BeanUtil.objectToHash(user);
        redisTemplate.opsForHash().putAll("aaa",stringObjectMap);
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("aaa");
        System.out.println(entries);
        User user2 = BeanUtil.hashToObject(entries, User.class);
        System.out.println(user2);
    }

    @Autowired
    private PaymentMapper paymentMapper;
    @Test
    void testMapper(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);

    }

    @Test
    void TestSecurity(){
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context);
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication);

    }


    @Autowired
    private IPaymentService paymentService;
    @Test
    void testPaymentService(){

        Long id = 7053528047865040947L;
        Boolean status = true;
        ResultBean resultBean = paymentService.updatePaymentStatus(id, status);
    }


    @Autowired
    private IGoodsService goodsService;
    @Test
    public void testAddGoods() {
        Goods goods = new Goods();
        goods.setOrderId(1L);
        goods.setName("测试货物");
        goods.setWeightOrVolume(2.5);
        goods.setUnitPrice(10.0);
        goods.setFreight(25.0);
        goods.setCount(3);
        ResultBean resultBean = goodsService.addGoods(goods,0l);
        System.out.println(resultBean);
    }

    @Test
    public void testUpdateGoods() {
        Goods goods = new Goods();
        goods.setId(7053538810415554572L);
        goods.setCount(5);
        goods.setName("111");
        goods.setUnitPrice(3.0);
        goods.setWeightOrVolume(2.3);
        ResultBean resultBean = goodsService.updateGoods(goods);
    }

    @Test
    void testfindGoods(){
        long orderId = 1l;
        ResultBean resultBean = goodsService.getGoodsByOrderId(orderId);
        System.out.println(resultBean);
    }

    @Autowired
    private IOrderService orderService;

    @Test
    public void testCreateOrder() {
        // 生成测试数据
        OrderVo order = new OrderVo();
        order.setSenderName("sender");
        order.setSenderPhone("12345678900");
        order.setSenderAddress("address");
        order.setSenderCity("city1");
        order.setReceiverName("receiver");
        order.setReceiverPhone("12345678901");
        order.setReceiverAddress("address");
        order.setReceiverCity("city2");

        // 调用方法
        ResultBean resultBean = orderService.createOrder(order);

        // 验证结果

        System.out.println(resultBean);
    }

}
