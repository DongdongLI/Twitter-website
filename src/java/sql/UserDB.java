/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;
import business.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lee
 */
public class UserDB {
    public static int insert(User user) throws ClassNotFoundException
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
            //System.out.println("connected");
            //Statement statement= c.createStatement();
            
                    
                    String query="insert into user (email,firstname,lastname,pass)"
                            +"values (?,?,?,?)";
               PreparedStatement ps=null;//c.prepareStatement(query);     
                    try {
                        ps=c.prepareStatement(query);
                        ps.setString(1, user.getEmail());
                        ps.setString(2, user.getFirstName());
                        ps.setString(3, user.getLastName());
                        ps.setString(4, user.getPassword());
                        
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
    public static boolean exist(String email,String pass) throws ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/Twitter";
            String username="root";
            String password="";
            Connection c=DriverManager.getConnection(dbURL,username,password);
            
            String query="Select * from user where email=? and pass =?";
            PreparedStatement ps=c.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, pass);
            
            ResultSet rs=ps.executeQuery();
            
            
            return rs.next();
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    public static User get(String email,String pass) throws ClassNotFoundException
    {
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/Twitter";
            String username="root";
            String password="";
            Connection c=DriverManager.getConnection(dbURL,username,password);
            
            String query="Select * from user where email=? and pass =?";
            PreparedStatement ps=c.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, pass);
            
            ResultSet rs=ps.executeQuery();
            User user=null;
            while(rs.next())
            {
                user=new User();
                user.setUserid(rs.getString("Userid"));
                user.setEmail(rs.getString("Email"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ArrayList<User> getAll(String admin,String adminPass) throws SQLException, ClassNotFoundException
    {
        if(admin.equals("admin") && adminPass.equals("admin"))
        {
            ArrayList<User> users=new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/Twitter";
            String username="root";
            String password="";
            Connection c=DriverManager.getConnection(dbURL,username,password);
            
            String query="Select * from user";
            PreparedStatement ps=c.prepareStatement(query);
            
            
            
            ResultSet rs=ps.executeQuery();
            User user=null;
            while(rs.next())
            {
                user=new User();
                user.setUserid(rs.getString("Userid"));
                user.setEmail(rs.getString("Email"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                
                users.add(user);
            }
            return users;
        }
        else
            return null;
    }
    public static int delete(String userid) throws ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/Twitter";
            String username="root";
            String password="";
            Connection c=DriverManager.getConnection(dbURL,username,password);
            
            String query="delete from user where userid=?";
            PreparedStatement ps=c.prepareStatement(query);
            ps.setString(1, userid);
            
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }
}
