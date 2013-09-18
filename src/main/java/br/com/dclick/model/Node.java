package br.com.dclick.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.codehaus.jackson.map.ObjectMapper;

@Entity
@Table(name = "node")
@NamedQueries({ 
	@NamedQuery(name="findAllNodesWithChildren", query="select n from Node n left join fetch n.children where n.treeId = :treeId"),
	@NamedQuery(name="findNodeByTreeIdAndRoot",  query="select n from Node n where n.treeId = :treeId and n.root = :root")
	})
public class Node {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String nome;

	private Long treeId;
	
	private Boolean root = false;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	@OrderColumn
	private List<Node> children = new LinkedList<Node>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTreeId() {
		return treeId;
	}

	public void setTreeId(Long treeId) {
		this.treeId = treeId;
	}

	public Boolean getRoot() {
		return root;
	}

	public void setRoot(Boolean root) {
		this.root = root;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	public String toJson() throws IOException {
		return new ObjectMapper().writeValueAsString(this);
	}
}
