package br.com.dclick.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.dclick.init.WebAppConfig;
import br.com.dclick.model.Node;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebAppConfig.class })
public class TreeControllerTest {

	@Autowired
	public WebApplicationContext wac;

	public MockMvc mockMvc;
	
	@Before
	public void init() {
		Assert.assertNotNull(wac);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testTree() throws IOException, Exception {
		Node tree1 = createNode(1, 3l);
		tree1.setRoot(true);
		System.out.println(tree1.toJson());
		
		Node tree2 = createNode(1, 2l);
		tree2.setRoot(true);
		
		mockMvc.perform(put("/tree").contentType(MediaType.APPLICATION_JSON).content(tree1.toJson()));
		mockMvc.perform(put("/tree").contentType(MediaType.APPLICATION_JSON).content(tree2.toJson()));
		
		mockMvc.perform(get("/tree/{treeId}", tree1.getTreeId())).andExpect(jsonPath("$.treeId", is(1))).andExpect(jsonPath("$.children", hasSize(3)));
		mockMvc.perform(get("/tree/{treeId}", tree2.getTreeId())).andExpect(jsonPath("$.treeId", is(2))).andExpect(jsonPath("$.children", hasSize(3)));
		
		mockMvc.perform(delete("/tree/{treeId}", tree1.getTreeId()));
		mockMvc.perform(delete("/tree/{treeId}", tree2.getTreeId()));
	}
	
	public Node createNode(Integer depth, Long treeId) {
		Node n = new Node();
		n.setNome("Depth " + depth);
		n.setTreeId(treeId);
		if (depth <= 4) {
			n.getChildren().addAll(this.createChildrens(depth+1, treeId));
		}
		return n;
	}
	
	public List<Node> createChildrens(Integer depth, Long treeId) {
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i <= depth; i++) {
			nodes.add(this.createNode(depth, treeId));
		}
		return nodes;
	}
}
