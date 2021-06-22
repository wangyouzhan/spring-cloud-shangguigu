package org.example.controller;


import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.example.CommonResult;
import org.example.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {


    @Autowired
    private PaymentFeignService paymentFeignService;


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable(name = "id") Long id) {

        return paymentFeignService.getPaymentById(id);

    }

    @GetMapping("/consumer/payment/timeout")
    public String timeout() {

        return paymentFeignService.timeout();

    }



}










