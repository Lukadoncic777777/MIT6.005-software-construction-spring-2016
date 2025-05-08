/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    @Test
    public void testgraph()
    {
        ConcreteEdgesGraph graph=new ConcreteEdgesGraph();
        graph.add("shabi");
        graph.set("shao0320","jiangwuyao",2039);
        graph.set("shao0320","jiangwuyao",3721);
        graph.set("jiangwuyao","jiangchu",111);
        graph.set("jiangwuyao","xiedi",1023);
        graph.set("shao0320","xiedi",2039);
        graph.remove("jiangwuyao");
        System.out.println(graph.toString());
    }

    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testEdges()
    {
        Edge edge=new Edge("shao0320","jiangniuyao",2039);
        System.out.println(edge.toString());
    }

    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    
    // TODO tests for operations of Edge
    
}
