package com.taesu.test.tree;

import com.taesu.test.tree.fancytree.model.TreeNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeService {
	public List<TreeNode> getAllTreeNodes(){
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		TreeNode n1 = new TreeNode();
		n1.setTitle("parent1");
		n1.setFolder(true);
		n1.setKey(0);
		
		List<TreeNode> children1 = new ArrayList<TreeNode>();
		TreeNode child = new TreeNode();
		child.setTitle("child1");
		child.setFolder(false);
		child.setKey(1);
		children1.add(child);
		
		child = new TreeNode();
		child.setTitle("child2");
		child.setFolder(false);
		child.setKey(2);		
		children1.add(child);
		
		child = new TreeNode();
		child.setTitle("child3");
		child.setFolder(false);
		child.setKey(3);		
		children1.add(child);
		
		n1.setChildren(children1);
		
		TreeNode n2 = new TreeNode();
		n2.setTitle("parent2");
		n2.setFolder(true);
		n2.setKey(7);
		
		TreeNode n3 = new TreeNode();
		n3.setTitle("parent3");
		n3.setFolder(true);
		n3.setKey(8);
		
		
		children1 = new ArrayList<TreeNode>();
		child = new TreeNode();
		child.setTitle("child11");
		child.setFolder(false);
		child.setKey(1);
		children1.add(child);
		
		child = new TreeNode();
		child.setTitle("child12");
		child.setFolder(false);
		child.setKey(2);		
		children1.add(child);
		
		child = new TreeNode();
		child.setTitle("child13");
		child.setFolder(false);
		child.setKey(3);		
		children1.add(child);
		
		n3.setChildren(children1);
		
		list.add(n1);
		list.add(n2);
		list.add(n3);
		
		child = new TreeNode();
		child.setTitle("child4");
		child.setFolder(false);
		child.setKey(4);
		
		list.add(child);
		
		return list;		
	}
}
