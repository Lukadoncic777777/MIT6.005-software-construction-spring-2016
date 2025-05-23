/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> res=new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (username.equals(tweet.getAuthor())) {
                res.add(tweet);
            }
        }
        return res;
//        throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> res=new ArrayList<>();
        Instant bgn=timespan.getStart(),ed=timespan.getEnd();
        for (Tweet now : tweets) {
            if(now.getTimestamp().isAfter(bgn) && now.getTimestamp().isBefore(ed))
            {
                res.add(now);
            }
        }
        return res;
//        throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        /*
        先将所有 word 转换为小写，再将 tweets 转换为小写进行判断，最后将其原文塞入res中来返回
         */
        List<String> translated_word=new ArrayList<>();
        List<Tweet> res=new ArrayList<>();
        for(String word: words)
        {
            String now="";
            for(int i=0;i<word.length();i++)
            {
                now+=Extract.translate(word.charAt(i));
            }
            translated_word.add(now);
        }
        for(Tweet now:tweets)
        {
            String nowText=now.getText(),translatedNowText="";
            for(int i=0;i<nowText.length();i++)
            {
                translatedNowText+=Extract.translate(nowText.charAt(i));
            }
            for(String nowWord:translated_word) {
                if (translatedNowText.contains(nowWord)) {
                    res.add(now);
                    break;
                }
            }
        }
        return res;
//        throw new RuntimeException("not implemented");
    }

}
