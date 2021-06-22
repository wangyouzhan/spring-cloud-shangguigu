package org.example.springcloud.lib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBlance {


    ServiceInstance instance(List<ServiceInstance> serviceInstances);


}
