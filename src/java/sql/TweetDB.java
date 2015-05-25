/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import business.Tweet;
import business.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lee
 */
public class TweetDB {
    public static int insert(Tweet tweet) throws ClassNotFoundException
    {
        try {
            //ConnectionPool pool=ConnectionPool.getInstance();
            //  Connection c=pool.getConnection();
            //PreparedStatement ps=null;
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/Twitter";
            String username="root";
            String password="";
            Connection c=DriverManager.getConnection(dbURL,username,password);
            
            //Statement statement= c.createStatement();
            
                    
                    String query="insert into tweets (tweet,userid)"
                            +"values (?,?)";
               PreparedStatement ps=null;//c.prepareStatement(query);     
                    try {
                        ps=c.prepareStatement(query);
                        ps.setString(1, tweet.getTweet());
                        ps.setString(2, tweet.getUserID());
                        
                        return ps.executeUpdate();
                        
                        
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        return 0;
                    }
                    finally{
                        //pool.freeConnection(c);
                        if(!ps.isClosed())
                            ps.close();
                    }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
    
    public static ArrayList<Tweet> getAll() throws ClassNotFoundException, SQLException
    {
        ArrayList<Tweet> tweets=new ArrayList<Tweet>();
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/Twitter";
            String username="root";
            String password="";
            Connection c=DriverManager.getConnection(dbURL,username,password);
            
            String query="Select * from tweets";
            PreparedStatement ps=c.prepareStatement(query);
            
            
            
            ResultSet rs=ps.executeQuery();
            Tweet tweet=null;
            while(rs.next())
            {
                tweet=new Tweet();
                tweet.setUserID(rs.getString("userid"));
                tweet.setTweet(rs.getString("tweet"));
                tweet.setTweetId(rs.getString("tweetid"));
                
                tweets.add(tweet);
            }
            return tweets;
    }
    public static int delete(String tweetId) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/Twitter";
            String username="root";
            String password="";
            Connection c=DriverManager.getConnection(dbURL,username,password);
            
            String query="delete from tweets where tweetid=?";
            PreparedStatement ps=c.prepareStatement(query);
            ps.setString(1, tweetId);
            
            
            return ps.executeUpdate();
    }
}
