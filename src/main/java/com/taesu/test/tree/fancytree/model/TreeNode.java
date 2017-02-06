package com.taesu.test.tree.fancytree.model;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode {
	private Integer key;
	private String title;
	private Boolean folder;
	private List<TreeNode> children;
	
}
