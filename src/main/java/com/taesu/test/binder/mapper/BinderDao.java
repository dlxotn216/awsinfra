package com.taesu.test.binder.mapper;

import com.taesu.test.binder.model.BinderForSelect;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by crscube on 2017-02-06.
 */
@Repository
public interface BinderDao {
    List<BinderForSelect> readAllBinder();
    List<BinderForSelect> readAllBinderOrderByHierarchy();
}
