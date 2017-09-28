package com.springbootRabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springbootRabbitmq.queue.HelloSender1;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {
	@Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender1 helloSender2;
    
    /*@PostMapping("/hello")*/
    @RequestMapping(value="/hello",method=RequestMethod.GET)
    public void hello() {
        helloSender1.send();
    }

}
