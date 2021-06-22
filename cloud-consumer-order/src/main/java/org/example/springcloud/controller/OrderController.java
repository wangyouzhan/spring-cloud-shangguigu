package org.example.springcloud.controller;


import org.example.CommonResult;
import org.example.Payment;
import org.example.springcloud.lib.LoadBlance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Service;
import java.net.URI;
import java.util.List;

@RestController
public class OrderController {


    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private LoadBlance loadBlance;

    @Resource
    private DiscoveryClient discoveryClient;

    public static final String URL_PAYMENT_GET = "http://cloud-payment-service";

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
//        int r = paymentService.create(payment);
//        log.info("********插入结果" + r);
//        if (r >= 1) {
        CommonResult commonResult = restTemplate.postForObject(URL_PAYMENT_GET + "/payment/create", payment ,CommonResult.class);
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

        CommonResult commonResult = restTemplate.getForObject(URL_PAYMENT_GET + "/payment/getPaymentById/" + id,CommonResult.class);

        return commonResult;
//        return new CommonResult(200, "查询成功");
//        } else {
//            return new CommonResult(444, "没有对应记录，查询ID：" + id + ",port" + serverPort);
//        }

    }


    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(URL_PAYMENT_GET + "/payment/getPaymentById/" + id,CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444, "操作失败");
        }

    }


    @GetMapping(value = "/consumer/payment/1b")
    public String getPaymentLB(){


        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0 ){
            return null;
        }

        ServiceInstance serviceInstance = loadBlance.instance(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/1b", String.class);

    }





}
