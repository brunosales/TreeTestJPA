package br.com.dclick.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dclick.model.Node;

@Repository
public class TreeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Node getTree(Long treeId) {
		entityManager.createNamedQuery("findAllNodesWithChildren")
				.setParameter("treeId", treeId).getResultList();
		Node root = entityManager
				.createNamedQuery("findNodeByTreeIdAndRoot", Node.class)
				.setParameter("treeId", treeId).setParameter("root", true)
				.getSingleResult();
		return root;
	}

}
