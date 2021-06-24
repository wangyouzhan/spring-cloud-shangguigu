package org.example.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.CommonResult;
import org.example.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;


    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
//        int r = paymentService.create(payment);
//        log.info("********插入结果" + r);
//        if (r >= 1) {
            return new CommonResult(200, "数据库插入成功");
//        } else {
//            return new CommonResult(444, "数据插入失败" + serverPort);
//        }
    }


    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable(name = "id") Long id) {

//        Payment payment = paymentService.getPaymentById(id);
//        log.info("********查询");
//        if (payment != null) {
        log.info("--------------------serverPort:" + serverPort);
            return new CommonResult(200, "查询成功" + serverPort);
//        } else {
//            return new CommonResult(444, "没有对应记录，查询ID：" + id + ",port" + serverPort);
//        }

    }


    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> applications = discoveryClient.getServices();
        for (int i = 0; i < applications.size(); i++) {
            log.info("********8");
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
       for(ServiceInstance instance : instances){
           log.info(instance + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
       }


        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return  serverPort;
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() throws InterruptedException {
        Thread.sleep(3);
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    public String getPaymentZipkin(){
        return  "hi, zipkin";
    }


}
