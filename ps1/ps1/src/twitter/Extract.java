/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        Instant begintime=tweets.get(0).getTimestamp(),endtime=tweets.get(0).getTimestamp();
        for(int i=1;i<tweets.size();i++)
        {
            Instant now=tweets.get(i).getTimestamp();
            if(begintime.isAfter(now))
            {
                begintime=now;
            }
            if(endtime.isBefore(now))
            {
                endtime=now;
            }
        }
        return (new Timespan(begintime,endtime));
//        throw new RuntimeException("not implemented");
    }
    public static boolean is_legal_char(char ch)
    {
        /*
        检查字符是否合法
         */
        if(ch>='A'&&ch<='Z')return true;
        if(ch>='a'&&ch<='z')return true;
        if(ch>='0'&&ch<='9')return true;
        if(ch=='_'||ch=='-')return true;
        return false;
    }
    public static char translate(char ch)
    {
        //将大写转换为小写字母
        if(ch>='A'&&ch<='Z')return (char)(ch-'A'+'a');
        return ch;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     **/

    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> res= new TreeSet<>();
        for(int i=0;i<tweets.size();i++)
        {
//            System.out.println();
            String text=tweets.get(i).getText();
            char[] textarray=new char[1005];
            textarray=text.toCharArray();
            for(int j=0;j<textarray.length;j++)
            {
                if(textarray[j]=='@'&&(j==0||is_legal_char(textarray[j-1])==false))
                {
                    int ed=j+1;
                    while(ed<textarray.length&&is_legal_char(textarray[ed])==true) {
                        ed++;
                    }
                    String NewUserName="";
                    for(int k=j+1;k<ed;k++)
                    {
                        NewUserName+=translate(textarray[k]);
                    }
                    res.add(NewUserName);
                    j=ed-1;
                }
            }
        }
        return res;
//        throw new RuntimeException("not implemented");
    }

}
