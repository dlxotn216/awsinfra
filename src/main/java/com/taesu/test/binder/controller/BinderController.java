package com.taesu.test.binder.controller;

import com.taesu.test.binder.service.BinderService;
import com.taesu.test.binder.service.impl.BinderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by crscube on 2017-02-06.
 */
@Controller
public class BinderController {
    @Autowired
    private BinderServiceImpl binderService;

    @ResponseBody
    @RequestMapping(value = "/binders", method = RequestMethod.GET)
    public Object readAllBinders(){
        return binderService.readAllBinders();
    }
}
