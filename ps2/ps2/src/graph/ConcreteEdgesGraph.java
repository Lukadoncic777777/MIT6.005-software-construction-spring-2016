/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L> > edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public ConcreteEdgesGraph()
    {

    }

    // TODO checkRep
    
    @Override public boolean add(L vertex) {
        if(vertices.contains(vertex)){
            return false;
        }
        vertices.add(vertex);
        return true;
//        throw new RuntimeException("not implemented");
    }
    
    @Override public int set(L source, L target, int weight) {
        if(!vertices.contains(source)){
            vertices.add(source);
        }
        if(!vertices.contains(target)){
            vertices.add(target);
        }
        for(Edge<L> edge:edges)
        {
            L u=edge.getU(),v= edge.getV();
            int w=edge.getW();
            if(u.equals(source)&&v.equals(target))
            {
                edges.remove(edge);
                if(weight!=0) {
                    edges.add(new Edge(source,target,weight));
                }
                return w;
            }
        }
        if(weight!=0){
            edges.add(new Edge(source,target,weight));
        }
        return 0;
//        edges.add(new Edge(source,target,weight));
//        throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(L vertex) {
        if(!vertices.contains(vertex)){
            return false;
        }
        List<Edge<L> > removedEdges=new ArrayList<>();
        for(Edge<L> edge:edges)
        {
            if(edge.getU().equals(vertex) || edge.getV().equals(vertex))
            {
                removedEdges.add(edge);
            }
        }
        for(Edge<L> edge:removedEdges)
        {
            edges.remove(edge);
        }
        vertices.remove(vertex);
        return true;
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Set<L> vertices() {
        return vertices;
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer>res = null;
        for(Edge<L> edge:edges)
        {
            if(edge.getV().equals(target))
            {
                res.put(edge.getU(), edge.getW());
            }
        }
        return res;
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer>res = null;
        for(Edge<L> edge:edges)
        {
            if(edge.getU().equals(source))
            {
                res.put(edge.getV(), edge.getW());
            }
        }
        return res;
//        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    @Override public String toString()
    {
        String res="The Graph:\nThe Vertices:\n";
        for(L vertex:vertices)
        {
            res+=vertex+" ";
        }
        res+="\nThe Edges:\n";
        for(Edge<L> edge:edges)
        {
            res+=edge.toString()+"\n";
        }
        return res;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    private final L u,v;
    private final int w;
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public Edge(L u,L v,int w) {
        this.u=u;
        this.v=v;
        this.w=w;
        checkrep();
    }

    // TODO checkRep
    public void checkrep() {
        assert this.u!=null;
        assert this.v!=null;
        assert this.w>0;
    }

    // TODO methods
    public L getU() {
        return u;
    }
    public L getV() {
        return v;
    }
    public int getW() {
        return w;
    }
    // TODO toString()
    /**
     * @author shao0320
     * @return A String which is a readable sentence includes the vertices and weight of an edge.
     */
    @Override public String toString()
    {
        String res="the First Vertex is:"+u+" "+"the Second Vertex is:"+v+" "+"the Weight is:"+w+"\n";
        return res;
    }
}
