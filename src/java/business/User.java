package business;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lee
 */
public class User implements Serializable{
    String FirstName;
    String LastName;
    String Email;
    String Password;
    String Userid;

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String Userid) {
        this.Userid = Userid;
    }
    
    
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    ArrayList<String> allTweet;
    
    public User(){
        FirstName ="";
        LastName="";
        Email="";
        Password="";
        Userid="";
    }
    
    
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public ArrayList<String> getAllTweet() {
        return allTweet;
    }

    public void setAllTweet(ArrayList<String> allTweet) {
        this.allTweet = allTweet;
    }

    @Override
    public String toString() {
        return "User{" + "FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email + ", Password=" + Password + ", Userid=" + Userid + '}';
    }
    
    
}
