package br.com.dclick.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.dclick.model.Node;

public interface NodeDAO extends CrudRepository<Node, Long> {
	
	public Node findByTreeIdAndRoot(Long treeId, Boolean root);

}
