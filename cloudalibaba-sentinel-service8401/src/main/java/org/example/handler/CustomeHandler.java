package org.example.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.example.CommonResult;

public  class CustomeHandler {

    public static CommonResult handlerException(BlockException blockException) {
        return new CommonResult(300, "handlerException");
    }

    public static CommonResult handlerException2(BlockException blockException) {
        return new CommonResult(300, "handlerException2");
    }
}
