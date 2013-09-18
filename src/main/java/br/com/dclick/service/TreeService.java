package br.com.dclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dclick.dao.NodeDAO;
import br.com.dclick.dao.TreeDAO;
import br.com.dclick.model.Node;

@Service
@Transactional
public class TreeService {

	@Autowired
	private TreeDAO treeDAO;
	
	@Autowired
	private NodeDAO nodeDAO;
	
	public Node getFullTree(Long treeId) {
		Node tree = treeDAO.getTree(treeId);
		return tree;
	}
	
	public Node save(Node node) {
		return nodeDAO.save(node);
	}
	
	public Node delete(Long treeId) {
		Node n = nodeDAO.findByTreeIdAndRoot(treeId, true);
		nodeDAO.delete(n);
		return n;
	}
}