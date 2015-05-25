/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import business.Tweet;
import business.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sql.SQLUtil;
import sql.TweetDB;
import sql.UserDB;

/**
 *
 * @author lee
 */
public class UserList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="/login.html";
        
        String action=request.getParameter("action");
        if(action.equals("resigter"))
        {
            String firstname=request.getParameter("firstname");
            String lastname=request.getParameter("lastname");
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            
            
            User user=new User();
            user.setEmail(email);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setPassword(password);
            //System.out.println("user password: "+password);
            try {
                UserDB.insert(user);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        else if(action.equals("login"))
        {
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            if(!email.equals("admin"))
            {
                
            User user=null;
            //user.setEmail(email);
            //user.setPassword(password);
            //version 1 to send an html table to the target jsp 
//                try {
//                    if(UserDB.exist(email, password))
//                    {
//                        url="/HomePage.jsp";
//                        HttpSession session=request.getSession();
//                        session.setAttribute("email", email);
//                        session.setAttribute("password", password);
//                    
                try {
                    //                try {
//                    ArrayList<Tweet> tweets=TweetDB.getAll();
//                    //System.out.println("admin!!!:"+users.toString());
//
//
//                    
//                    Class.forName("com.mysql.jdbc.Driver");
//                    String dbURL="jdbc:mysql://localhost:3306/Twitter";
//                    String username="root";
//                    String pass="";
//                    Connection c=DriverManager.getConnection(dbURL,username,pass);
//            
//                    String query="Select * from tweets";
//                    PreparedStatement ps=c.prepareStatement(query);
//                    ResultSet rs=ps.executeQuery();
//               
//                    String resultForm=SQLUtil.getHtmlTable(rs);
//                    //System.out.println("resultForm:"+resultForm.toString());
//                    request.setAttribute("resultForm", resultForm);
//                        } catch (SQLException ex) {
//                            System.out.println(ex);
//                        } catch (ClassNotFoundException ex) {
//                            System.out.println(ex);
//                        }
//                    
//                    }
//                    
//                    user=UserDB.get(email, password);
//                    request.setAttribute("user", user);
//                    
//                } catch (ClassNotFoundException ex) {
//                    System.out.println(ex);
//                }
                    
                    //version 2, use tag
                    if(UserDB.exist(email, password))
                    {
                        url="/HomePage.jsp";
                        HttpSession session=request.getSession();
                        session.setAttribute("email", email);
                        session.setAttribute("password", password);
                        user=UserDB.get(email, password);
                        //request.setAttribute("user", user);
                        session.setAttribute("user", user);
                        ArrayList<Tweet> tweets=TweetDB.getAll();
                        //System.out.println("the size of tweets is "+tweets.size());
                        session.setAttribute("userid", user.getUserid());
                        session.setAttribute("tweets", tweets);
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                } catch (SQLException ex) {
                    Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            else if(email.equals("admin"))//&&password=="admin")
            {
                //System.out.println("before going to admin");
                url="/admin.jsp";
//                try {
//                    ArrayList<User> users=UserDB.getAll(email, password);
//                    //System.out.println("admin!!!:"+users.toString());
//                    url="/admin.jsp";
                
                    //version 1 using sqlutil to generate table                    
//            Class.forName("com.mysql.jdbc.Driver");
//            String dbURL="jdbc:mysql://localhost:3306/Twitter";
//            String username="root";
//            String pass="";
//            Connection c=DriverManager.getConnection(dbURL,username,pass);
            
//            String query="Select * from user";
//            PreparedStatement ps=c.prepareStatement(query);
//            ResultSet rs=ps.executeQuery();
//                    
//            String resultForm=SQLUtil.getHtmlTable(rs);
//            request.setAttribute("resultForm", resultForm);
//                } catch (SQLException ex) {
//                    System.out.println(ex);
//                } catch (ClassNotFoundException ex) {
//                    System.out.println(ex);
//                }
                    //version 2 using custom tag
                ArrayList<User> users=null;
                try {    
                    users=UserDB.getAll(email, password);
                } catch (SQLException ex) {
                    Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                request.getSession().setAttribute("users", users);
                
            }
        }
        
        else if(action.equals("new_tweet"))
        {
            User user=null;
            
                url="/HomePage.jsp";
                
                String new_tweet=(String) request.getParameter("tweet");
                //System.out.println("get the tweet"+new_tweet);//got it
                HttpSession session=request.getSession();
                //System.out.println("get the email in session"+session.getAttribute("email"));//got it
                String mail=(String) session.getAttribute("email");
                String pass=(String) session.getAttribute("password");
                
                
                
            try {
                user=UserDB.get(mail, pass);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.setAttribute("user", user);
                 
                
                try {
                    TweetDB.insert(new Tweet(user.getUserid(), new_tweet));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //send them back to home page done
                //the home page should contain the tweet just send
                
                ArrayList<Tweet> tweets=null;
            try {
                tweets = TweetDB.getAll();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.getSession().setAttribute("tweets", tweets);
            
        }
        
        else if(action.equals("delete"))
        {
            String deletecode=request.getParameter("deletecode");
            try {
                UserDB.delete(deletecode);
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
            ArrayList<User> users=null;
            try {
                users=UserDB.getAll("admin", "admin");
            } catch (SQLException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("users", users);
            url="/admin.jsp";
        }
        
        else if(action.equals("tweet_delete"))
        {
            String tweetid=request.getParameter("tweet_code");
            System.out.println("the tweet id is "+tweetid);
            try {
                
                TweetDB.delete(tweetid);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            url="/HomePage.jsp";
            ArrayList<Tweet> tweets=null;
            try {
                tweets = TweetDB.getAll();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("tweets", tweets);
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}