package com.samplecode.chapter1;

import com.samplecode.util.Kit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RestController
@RequestMapping("/ctrl")
public class MyController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/func1")
    public void func1() {
        HttpEntity<byte[]> entity = new HttpEntity<>(Kit.getBytesFromFile("C:\\Users\\我爱毛毛\\Desktop\\20231124\\2023年陇剑杯赛题\\1-hard_web\\2.bin"));
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange("http://localhost:8080/chapter1.jsp", HttpMethod.POST, entity, byte[].class);
        System.out.println(new String(Chapter1.x(responseEntity.getBody(), false)));
    }

    @RequestMapping("/func2")
    public String func2(@RequestParam String str) {
        byte[] c = Chapter1.formatParameter(Chapter1.x(Kit.hexToBytes(str), false));
        return new String(c);
    }

    @RequestMapping("/func3")
    public String func3(@RequestParam String str) {
        byte[] c = Chapter1.formatParameter(Chapter1.x(Base64.getDecoder().decode(str), false));
        return new String(c);
    }
}
