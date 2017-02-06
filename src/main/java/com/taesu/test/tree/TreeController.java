package com.taesu.test.tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TreeController {

	@Autowired
	private TreeService treeService;
	
	@ResponseBody
	@RequestMapping(value="/fancytree-nodes", method=RequestMethod.GET)
	public Object getFancyTreeNodes(){
		return treeService.getAllTreeNodes();
	}
}
