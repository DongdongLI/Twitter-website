/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tag;

import business.Tweet;
import business.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author lee
 */
public class AllTweetsTag extends BodyTagSupport{
    
    private ArrayList<Tweet> tweets;
    private int count;
    private Tweet tweet;
    private User user;
    
    public int doStartTag() throws JspException {
        tweets=(ArrayList<Tweet>) pageContext.findAttribute("tweets");
        user=(User)pageContext.findAttribute("user");
        if(tweets==null || tweets.size()==0)
            return SKIP_BODY;
        else
            return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public void doInitBody() throws JspException {
        count=0;
        tweet=tweets.get(count);
        pageContext.setAttribute("twi_id", tweet.getTweetId());
        pageContext.setAttribute("twi_userid", tweet.getUserID());
        pageContext.setAttribute("twi_tweet", tweet.getTweet());
        pageContext.setAttribute("userid", user.getUserid());
        count++;
    }

    @Override
    public int doAfterBody() throws JspException {
        if(count<=tweets.size()-1)
        {
            tweet=tweets.get(count);
            pageContext.setAttribute("twi_id", tweet.getTweetId());
            pageContext.setAttribute("twi_userid", tweet.getUserID());
            pageContext.setAttribute("twi_tweet", tweet.getTweet());
            count++;
            return EVAL_BODY_AGAIN;
        }
        else
        {
            JspWriter out=bodyContent.getEnclosingWriter();
            try {
                bodyContent.writeOut(out);
            } catch (IOException ex) {
                Logger.getLogger(AllTweetsTag.class.getName()).log(Level.SEVERE, null, ex);
            }
            return SKIP_BODY;
        }
    }
    
}
