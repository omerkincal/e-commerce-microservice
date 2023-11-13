package com.example.basket.library.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
@FeignClient(value = "auth")
public interface AuthFeignClient extends AuthCallableFeignClient{

}
