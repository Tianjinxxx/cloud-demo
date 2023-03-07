package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        //1. 查询订单
        Order order = orderMapper.findById(orderId);
        //2. 利用Feign发起http请求，远程查询user
        User user = userClient.findById(order.getUserId());
        //3. 封装user到order
        order.setUser(user);
        //4 .返回order
        return order;
    }

//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //2.远程查询user
//        //2.1 url地址
//        String url = "http://userservice/user/" + order.getUserId();
//        //2.2 发起调用
//        User user = restTemplate.getForObject(url, User.class);
//        //2.3 封装user到order
//        order.setUser(user);
//        //3.返回order
//        return order;
//    }
}
