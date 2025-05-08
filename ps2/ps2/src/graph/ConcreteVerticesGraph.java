/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L extends Comparable<L>> implements Graph<L>{
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public ConcreteVerticesGraph()
    {

    }
    // TODO checkRep
    
    @Override public boolean add(L vertex) {
        if(vertices.contains(new Vertex<L>(vertex))){
            return false;
        }
        vertices.add(new Vertex<L>(vertex));
        return true;
//        throw new RuntimeException("not implemented");
    }
    private Vertex<L> getVertex(L nameOfVertex)
    {
        for(Vertex<L> vertex:vertices) {
            if(vertex.getName().equals(nameOfVertex))
            {
                return vertex;
            }
        }
        return new Vertex<L>(null);
    }
    @Override public int set(L source, L target, int weight) {
        Vertex<L> src=getVertex(source),tar=getVertex(target);
        if(src.getName()==null){
            src=new Vertex<L>(source);
            vertices.add(src);
        }
        if(tar.getName()==null) {
            tar=new Vertex<L>(target);
            vertices.add(tar);
        }
//        System.out.println("DEBUG!!!!!!!!!"+src.toString()+" "+tar.toString());
        return src.addEdge(src,tar,weight);
//        throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(L vertex) {
        Vertex<L> ver=getVertex(vertex);
        if(!vertices.contains(ver)){
            return false;
        }
        vertices.remove(ver);
        Set<L> beingRemoveSource=new TreeSet<>();
        for(Vertex<L> source:ver.getSource().keySet()) {
            beingRemoveSource.add(source.getName());
        }
        for(L name:beingRemoveSource){
            Vertex<L> now=getVertex(name);
            now.removeEdge(now,ver);
        }
        Set<L> beingRemoveTarget=new TreeSet<>();
        for(Vertex<L> target:ver.getTarget().keySet()) {
            beingRemoveTarget.add(target.getName());
        }
        for(L name:beingRemoveTarget){
            Vertex<L> now=getVertex(name);
            now.removeEdge(ver,now);
        }
        return true;
    }
    @Override public Set<L> vertices() {
        Set<L>res=new TreeSet<>();
        for(Vertex<L> vertex:vertices) {
            res.add(vertex.getName());
        }
        return res;
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Vertex<L> tar=getVertex(target);
        Map<Vertex<L>,Integer>src=tar.getSource();
        Map<L,Integer>res=new TreeMap<>();
        for(Vertex<L> ver:src.keySet()){
            res.put(ver.getName(),src.get(ver));
        }
        return res;
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Vertex<L> src=getVertex(source);
        Map<Vertex<L>,Integer>tar=src.getTarget();
        Map<L,Integer>res=new TreeMap<>();
        for(Vertex<L> ver:tar.keySet()){
            res.put(ver.getName(),tar.get(ver));
        }
        return res;
//        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    @Override public String toString()
    {
        String res="The number of Vertices="+vertices.size()+"\n";
        int cnt=0;
        for(Vertex ver:vertices){
            cnt++;
            res+="The "+cnt+" vertex is:\n"+ver.toString();
        }
        return res;
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class  Vertex<L extends Comparable<L>> implements Comparable<Vertex<L>> {
    
    // TODO fields
    private L name;
    private Map<Vertex<L>,Integer>source;
    private Map<Vertex<L>,Integer>target;

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public Vertex(L name)
    {
        this.name=name;
        this.source=new TreeMap<>();
        this.target=new TreeMap<>();
    }
    // TODO checkRep
    private void checkrep()
    {
        assert this.name!=null;
    }
    // TODO methods
    public L getName(){
        return name;
    }
    public Map<Vertex<L>,Integer>getSource(){
        return source;
    }
    public Map<Vertex<L>,Integer>getTarget(){
        return target;
    }
    private void addTarget(Vertex<L> target,int weight){
        this.target.put(target,weight);
    }
    private void addSource(Vertex<L> source,int weight){
        this.source.put(source,weight);
    }
    private void removeTarget(Vertex<L> target) {
        this.target.remove(target);
    }
    private void removeSource(Vertex<L> source){
        this.source.remove(source);
    }
    public  int addEdge(Vertex<L> src, Vertex<L> tar, int w){
        int res=src.getTarget().getOrDefault(tar,0);
        src.addTarget(tar,w);
        tar.addSource(src,w);
        return res;
    }
    public  boolean removeEdge(Vertex<L> src,Vertex<L> tar) {
        if(!src.getTarget().containsKey(tar))return false;
        src.removeTarget(tar);
        tar.removeSource(src);
        return true;
    }
    @Override public int compareTo(Vertex<L> other)
    {
        return this.name.compareTo(other.name);
    }
    @Override public boolean equals(Object obj)
    {
        if(this==obj)return true;
        if(obj==null){
            return false;
        }
        if(getClass()!=obj.getClass()) {
            return false;
        }
        Vertex<L> other=(Vertex<L>) obj;
        return this.name==other.name;
    }

    // TODO toString()
    @Override public String toString()
    {
        String res="The name of the vertex is:"+name+"\n";
        res+="The source of this vertex is:\n";
        for(Vertex<L> src:source.keySet()) {
            res+=src.getName()+" ";
        }
        res+="\nThe target of this vertex is:\n";
        for(Vertex<L> tar:target.keySet()){
            res+=tar.getName()+" ";
        }
        return res;
    }
}
