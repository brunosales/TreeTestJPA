package br.com.dclick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.dclick.model.Node;
import br.com.dclick.service.TreeService;

@Controller
@RequestMapping("/tree")
public class TreeController {
	
	@Autowired
	private TreeService treeService;
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Node create(@RequestBody Node node) {
		return this.treeService.save(node);
	}
	
	@RequestMapping(value="{treeId}", method=RequestMethod.GET)
	@ResponseBody
	public Node getTree(@PathVariable("treeId") Long treeId) {
		return treeService.getFullTree(treeId);
	}
	
	@RequestMapping(value="{treeId}", method=RequestMethod.DELETE)
	@ResponseBody
	public Node delete(@PathVariable("treeId") Long treeId) {
		return treeService.delete(treeId);
	}

}
