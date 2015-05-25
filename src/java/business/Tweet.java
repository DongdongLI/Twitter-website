/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;

/**
 *
 * @author lee
 */
public class Tweet implements Serializable{
    String userID;
    String tweet;
    String tweetId;

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }
    
    public Tweet()
    {
        userID="";
        tweet="";
    }

    public Tweet(String userID, String tweet) {
        this.userID = userID;
        this.tweet = tweet;
    }
    
    
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    @Override
    public String toString() {
        return "Tweet{" + "userID=" + userID + ", tweet=" + tweet + '}';
    }

   
}
