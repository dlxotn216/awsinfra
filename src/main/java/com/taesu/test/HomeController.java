package com.taesu.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by crscube on 2017-01-25.
 */
@Controller
public class HomeController {
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String home(){
        return "index";
    }

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String index(){
        return "index";
    }
}
