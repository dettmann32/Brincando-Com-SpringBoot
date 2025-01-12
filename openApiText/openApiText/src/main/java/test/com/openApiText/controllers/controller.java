package test.com.openApiText.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    
    @GetMapping("/test")
    public String test() {
        return "test";
    }
    
    @GetMapping("/test2")
    public String test2() {
        return "{\"content\":[],\"pageNumber\":0,\"pageSize\":10,\"totalElements\":0}";

    }
    
    @GetMapping("/test3")
    public String test3() {
        return "test3";
    }
    
    @GetMapping("/test4")
    public String test4() {
        return "test4";
    }
    
    @GetMapping("/test5")
    public String test5() {
        return "test5";
    }
    
    @GetMapping("/test6")
    public String test6() {
        return "test6";
    }
    
   
}
