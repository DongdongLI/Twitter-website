/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tag;

import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.DataFormat;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author lee
 */
public class currentDateTag extends TagSupport{
    
    

    @Override
    public int doStartTag() throws JspException {
        Date date=new Date();
        DateFormat format=DateFormat.getDateInstance(DateFormat.LONG);
        String formatDate=format.format(date);
        
        JspWriter out=pageContext.getOut();
        try {
            out.println(formatDate);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return SKIP_BODY;
    }
    
}
