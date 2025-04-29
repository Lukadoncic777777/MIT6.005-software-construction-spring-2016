/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.*;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {
    public static String translate_users(String username)
    {
        String res="";
        for(int i=0;i<username.length();i++)
        {
            res+=Extract.translate(username.charAt(i));
        }
        return res;
    }
    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        Map<String,Set<String>> res=new TreeMap<>();
        Set<String> empty=new TreeSet<>();
        for(Tweet tweet : tweets)
        {
            Set<String> Atname=Extract.getMentionedUsers(Arrays.asList(tweet));
            if(Atname.size()!=0)
            {
                String nowuser=translate_users(tweet.getAuthor());
                Set<String> tmp= res.getOrDefault(nowuser,empty);
                for(String name :Atname)
                {
                    name=translate_users(name);
                    if(!name.equals(nowuser))tmp.add(name);
                }
                res.put(nowuser,tmp);
            }
        }
        return res;
//        throw new RuntimeException("not implemented");
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        List<String> res=new ArrayList<>();
        Map<Integer,List<String> > mp=new TreeMap<>();
        mp.clear();
        Map<String, Set<String>> allusers_followsGraph=followsGraph;
        final List<String> emptyList=new ArrayList<>();
        Map<String,Integer> fans=new TreeMap<>();
        emptyList.clear();
        final Set<String> emptySet=new TreeSet<>();
        emptyList.clear();
        for(String nowuser:followsGraph.keySet())
        {
            Set<String> nowinfluencer=followsGraph.get(nowuser);
            for(String name:nowinfluencer)
            {
                int nowsize=followsGraph.getOrDefault(name,emptySet).size();
                if(nowsize==0){
                    allusers_followsGraph.put(name,emptySet);
                }
            }
        }
        for(String nowuser:allusers_followsGraph.keySet())
        {
            fans.put(nowuser,0);
        }
        for(String nowuser:allusers_followsGraph.keySet())
        {
//            fans.put(nowuser,allusers_followsGraph.get(nowuser).size());
            Set<String> Atname=allusers_followsGraph.get(nowuser);
            for(String name:Atname) {
                int now = fans.getOrDefault(name, 0);
                fans.put(name, now + 1);
            }
        }
        List<String> tmp0=mp.getOrDefault(-1,emptyList);
//        System.out.println("*****"+tmp0);
        for(String nowuser:fans.keySet())
        {
//            System.out.println("input!:"+nowuser);
            int now=fans.get(nowuser);
            List<String> tmp=new ArrayList<>(mp.getOrDefault(-now,emptyList));
//            System.out.println("now="+now+"contain?"+mp.containsKey(-now)+"emptyList="+emptyList);
//            System.out.println("$$$$"+nowuser+" "+(-now)+" "+tmp);
            tmp.add(nowuser);
//            System.out.println("!!!!"+nowuser+" "+(-now)+" "+tmp);
            mp.put(-now,tmp);
        }
        for(Integer outdegree:mp.keySet())
        {
            List<String> now=mp.get(outdegree);
            for(String name:now)
            {
//                System.out.println("now="+now+" name="+name+" outdegree="+outdegree);
                res.add(name);
            }
        }
        for(String name:res)
        {
            System.out.println(name);
        }
        return res;
//        throw new RuntimeException("not implemented");
    }

}
