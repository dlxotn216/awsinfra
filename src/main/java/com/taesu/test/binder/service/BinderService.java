package com.taesu.test.binder.service;

import com.taesu.test.binder.model.BinderForSelect;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by crscube on 2017-02-06.
 */
@Service
public interface BinderService {
    List<BinderForSelect> readAllBinders();
}
