package com.example.projectspring.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// RestAPI용 컨트롤러 -> 디폴트로 JSON 반환
@RestController
public class FirstApiController {

@GetMapping("/api/hello")
    public String hello() {
    return "Hello World";
}

}
