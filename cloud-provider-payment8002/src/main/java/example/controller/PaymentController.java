package example.controller;

import lombok.extern.slf4j.Slf4j;

import org.example.CommonResult;
import org.example.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;


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


    @GetMapping(value = "/payment/1b")
    public String getPaymentLB(){
        return  serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() throws InterruptedException {
        Thread.sleep(3);
        return serverPort;
    }


}
