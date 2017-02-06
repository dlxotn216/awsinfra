package com.taesu.test.binder.service.impl;

import com.taesu.test.binder.mapper.BinderDao;
import com.taesu.test.binder.model.BinderForSelect;
import com.taesu.test.binder.service.BinderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crscube on 2017-02-06.
 */
@Service
public class BinderServiceImpl implements BinderService {
    @Autowired
    private BinderDao binderDao;

    public List<BinderForSelect> readAllBinders(){
        return setHierarchyStructure(binderDao.readAllBinder());
    }

    private List<BinderForSelect> setHierarchyStructure(List<BinderForSelect> input){
        List<BinderForSelect> removeTarget = new ArrayList<>();

        for(BinderForSelect b : input){
            System.out.println("DEBUG check : "+b.getParent());
            if(b.getParent().equals(0)){
                System.out.println("DEBUG CHECK ROOT DEPTH: "+b.getTitle());
                b.setExpanded(true);
            } else {
                System.out.println("DEBUG CHECK Another DEPTH: "+b.getTitle());
                BinderForSelect parent = input.get(b.getParent()-1);
                List<BinderForSelect> children = parent.getChildren();

                if(children == null){
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(b);
                removeTarget.add(b);
            }
        }
        deleteBinderByKey(input, removeTarget);
        return input;
    }
    private void deleteBinderByKey(List<BinderForSelect> input, List<BinderForSelect> removeTarget){
        for(BinderForSelect b : removeTarget){
            input.remove(b);
        }
    }
}
