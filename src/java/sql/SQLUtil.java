/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author lee
 */
public class SQLUtil {
    public static String getHtmlTable(ResultSet results) throws SQLException
    {
        StringBuilder htmlTable =new StringBuilder();
        ResultSetMetaData metaData=results.getMetaData();
        int columnCount=metaData.getColumnCount();
        
        htmlTable.append("<table>");
        
        //add header row
        htmlTable.append("<tr>");
        for(int i=1;i<=columnCount;i++)
        {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr>");
        
        //add data row(s)
        while(results.next())
        {
            htmlTable.append("<tr>");
            for(int i=1;i<=columnCount;i++)
            {
                htmlTable.append("<th>");
                htmlTable.append(results.getString(i));
                htmlTable.append("</th>");
            }
//            htmlTable.append("<form>");
//            htmlTable.append("<input type="+"submit"+" value=delete " 
//                    + "action="
//                    + "UserList "
//                    + ">");
//            
//            htmlTable.append("<input type="+"hidden "+"name="+"delete");
//            htmlTable.append("value="+"personID ");
//            htmlTable.append(">");
            htmlTable.append("</form>");
            
            htmlTable.append("</tr>");
        }
        
        htmlTable.append("</table>");
        return htmlTable.toString();
    }
}
