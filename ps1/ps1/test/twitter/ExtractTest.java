/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");

    private static final Instant d3 = Instant.parse("2019-02-14T11:00:00Z");
    private static final Instant d4 = Instant.parse("2005-09-23T03:15:10Z");
    private static final Instant d5 = Instant.parse("2025-04-23T20:39:39Z");
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);

    private static final Tweet tweet3 = new Tweet(3, "jiangwuyao", "woshinideerzi @shao0320 qwqqwq", d3);
    private static final Tweet tweet4 = new Tweet(4, "xiedi", "woyaochao @NiuNiuyao woxihuanni", d4);
    private static final Tweet tweet5 = new Tweet(5, "shao0320", "I'm pain,god @zhangxujing give mail to szmsimon@126.com", d5);
//    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);


    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespan() {
        //test1
        Timespan timespan1 = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan1.getStart());
        assertEquals("expected end", d2, timespan1.getEnd());
        //test2
        Timespan timespan2 = Extract.getTimespan(Arrays.asList(tweet1, tweet2,tweet3,tweet4,tweet5));
        assertEquals("expected start", d4, timespan2.getStart());
        assertEquals("expected end", d5, timespan2.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        //test1

        Set<String> mentionedUsers1 = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers1.isEmpty());

        //test2

        Set<String> mentionedUsers2 = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2,tweet3,tweet4,tweet5));

        Set<String> answer2=new TreeSet<>();
        answer2.add("shao0320");
        answer2.add("niuniuyao");
        answer2.add("zhangxujing");
        assertEquals("expect equal", answer2,mentionedUsers2);
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
