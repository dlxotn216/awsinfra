package com.taesu.test.tree.fancytree;

import com.taesu.test.tree.fancytree.model.TreeNode;

import java.util.List;

/**
 * Created by crscube on 2017-02-06.
 */
public interface TreeDao {
    List<TreeNode> readAllTreeNode();
}
