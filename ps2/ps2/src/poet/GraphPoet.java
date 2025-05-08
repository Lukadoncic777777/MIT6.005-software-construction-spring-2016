/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import graph.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    public String transfer(String word)
    {
        String res="";
        for(int i=0;i<word.length();i++)
        {
            char ch=word.charAt(i);
            if(ch>='a'&&ch<='z')res+=ch;
            else if(ch>='A'&&ch<='Z')res+=(char)(ch-'A'+'a');
        }
        return res;
    }
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(corpus))) {

            String line;
            String now="",lst="";
            Map<Pair<String>,Integer> count=new TreeMap<>();
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                for(int i=0;i<line.length();i++)
                {
                    if(line.charAt(i)==' ')
                    {
                        now=transfer(now);
//                        System.out.println("now="+now+" "+"lst="+lst);
                        if(lst.length()!=0)
                        {
                            Pair<String>pair=new Pair<String>(lst,now);
                            int nowweight=count.getOrDefault(pair,0);
                            count.put(pair,nowweight+1);
                        }
                        lst=now;
                        now="";
                    }
                    else now+=line.charAt(i);
                }
                now=transfer(now);
                if(lst.length()!=0)
                {
                    Pair<String>pair=new Pair<String>(lst,now);
                    int nowweight=count.getOrDefault(pair,0);
                    count.put(pair,nowweight+1);
                }
            }
            for(Pair<String> pair:count.keySet())
            {
//                System.out.println("DEBUG:"+pair.getFirst()+" "+pair.getSecond()+" "+count.get(pair));
                graph.set(pair.getFirst(),pair.getSecond(),count.get(pair));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
//        throw new RuntimeException("not implemented");
    }
    
    // TODO checkRep
    String getAddWord(String source,String target)
    {
        Set<String> vertices=graph.vertices();
        Map<String,Integer> outedge=graph.targets(source);
        Map<String,Integer> inedge=graph.sources(target);
        int weight=0;
        String res="";
        for(String vertex:vertices){
            int nowweight=outedge.getOrDefault(vertex,-1005)+inedge.getOrDefault(vertex,-1005);
            if(nowweight>weight)
            {
                res=vertex;
                weight=nowweight;
            }
        }
//        System.out.println("##########"+source+" "+target+" "+res);
        return res;
    }
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String now="",lst="";
        String[] addWord=new String[1005];
        int cnt=0;
        for(int i=0;i<input.length();i++)
        {
            if(input.charAt(i)==' ')
            {
//                System.out.println("index="+i);
                now=transfer(now);
//                System.out.println("DEBUG!!!"+lst+" "+now);
                if(lst.length()!=0)addWord[++cnt]=getAddWord(lst, now);
                lst=now;
                now="";
            }
            else now+=input.charAt(i);
        }
        now=transfer(now);
//        System.out.println("DEBUG!!!"+lst+" "+now);
        if(lst.length()!=0)addWord[++cnt]=getAddWord(lst, now);
//        throw new RuntimeException("not implemented")
        String res="";
        int nowpointer=0;
//        for(int i=1;i<=cnt;i++)
//        {
//            System.out.println(i+" "+addWord[i]);
//        }
//        Sys/tem.out.println("");
        int flag=0;
        for(int i=0;i<input.length();i++)
        {
            if(input.charAt(i)==' ')
            {
                nowpointer++;
//                System.out.println("nowpointer="+nowpointer);
                if(!addWord[nowpointer].equals(""))res+=" "+addWord[nowpointer];
            }
//            else if(input.charAt(i)==' ')flag=1;
            res+=input.charAt(i);
        }
        return res;
    }
    
    // TODO toString()
    
}
class Pair<L extends Comparable<L>> implements Comparable<Pair<L>> {
    private L first,second;
    public Pair(L first,L second)
    {
        this.first=first;
        this.second=second;
    }
    public L getFirst()
    {
        return first;
    }
    public L getSecond()
    {
        return second;
    }
    @Override
    public int compareTo(Pair<L> o) {
        if(this.first!=o.first) return this.first.compareTo(o.first);
        else return this.second.compareTo(o.second);
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
        Pair<L> other=(Pair<L>) obj;
        return (this.first==other.first) && (this.second==other.second);
    }
}
