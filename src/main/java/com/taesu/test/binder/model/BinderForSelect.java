package com.taesu.test.binder.model;

import com.taesu.test.tree.fancytree.model.TreeNode;
import lombok.Data;

import java.util.List;

/**
 * Created by crscube on 2017-02-06.
 */
@Data
public class BinderForSelect {
    private Integer key;
    private String title;
    private Boolean folder;
    private List<BinderForSelect> children;
    private Integer order;
    private Integer parent;
    private Boolean expanded;
}
