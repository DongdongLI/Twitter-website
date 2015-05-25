/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tag;

import business.User;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

/**
 *
 * @author lee
 */
public class UserListTag extends BodyTagSupport{
    private ArrayList<User> users;
    private int count;
    private User user;
    
    
    
    @Override
    public JspWriter getPreviousOut() {
        return super.getPreviousOut(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BodyContent getBodyContent() {
        return super.getBodyContent(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void release() {
        super.release(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            if (count < users.size()) {
                user = (User) users.get(count);
                pageContext.setAttribute("userid",user.getUserid());
                pageContext.setAttribute("firstname", user.getFirstName());
                pageContext.setAttribute("lastname", user.getLastName());
                pageContext.setAttribute("email", user.getEmail());
                count++;
                return EVAL_BODY_AGAIN;
            } else {
                JspWriter out = bodyContent.getEnclosingWriter();
                bodyContent.writeOut(out);
                return SKIP_BODY;
            }
        } catch (IOException ioe) {
            System.err.println("IOException doAfterBody: " + ioe);
            return SKIP_BODY;
        }
    }

    @Override
    public void doInitBody() throws JspException {
        count=0;
        user=users.get(count);
        pageContext.setAttribute("userid",user.getUserid());
        pageContext.setAttribute("firstname", user.getFirstName());
        pageContext.setAttribute("lastname", user.getLastName());
        pageContext.setAttribute("email", user.getEmail());
        count++;
    }

    @Override
    public void setBodyContent(BodyContent b) {
        super.setBodyContent(b); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doStartTag() throws JspException {
        users=(ArrayList<User>) pageContext.findAttribute("users");
        if(users==null || users.size()==0)
            return SKIP_BODY;
        else
            return EVAL_BODY_BUFFERED;
    }
}
