/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author lee
 */
public class ConnectionPool {
    static ConnectionPool pool=null;
    static DataSource dataSource=null;
    
    ConnectionPool()
    {
        try {
            InitialContext ic=new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/murach");
            
        } catch (NamingException ex) {
            System.out.println(ex);
        }
    }
    public static synchronized ConnectionPool getInstance()
    {
        if(pool==null)
            pool=new  ConnectionPool();
        return pool;
    }
    public Connection getConnection()
    {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    public  void  freeConnection(Connection c)
    {
        try {
            if(!c.isClosed())
             c.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
