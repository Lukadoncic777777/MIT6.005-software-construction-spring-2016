/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    @Test public void testgraph(){
        ConcreteVerticesGraph<String> graph= new ConcreteVerticesGraph<String>();
        graph.add("shabi");
        graph.set("shao0320","jiangwuyao",2039);
        graph.set("shao0320","jiangwuyao",3721);
        graph.set("jiangwuyao","jiangchu",111);
        graph.set("jiangwuyao","xiedi",1023);
        graph.set("shao0320","xiedi",2039);
        graph.remove("jiangwuyao");
        System.out.println(graph.toString());
    }
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    
}
