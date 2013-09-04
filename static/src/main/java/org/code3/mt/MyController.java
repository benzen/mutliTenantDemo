package org.code3.mt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import javax.sql.DataSource;
import java.sql.ResultSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;


@Controller
public class MyController{

  @Autowired
  DataSource routingDataSource;

  @Autowired 
  private HttpServletRequest request;
  
  @RequestMapping("/*")
  @ResponseBody
  public String  handle(){
    String url = request.getRequestURL().toString();
    

      String s = "";
      s += " <h1>Mt-demonstration</h1>"+"<br/><br/><br/><br/>";
      s += "Current URL: "+url+"<br/>";
      s += "Key is based on the header 'ifsrTenantId', current one is: "+getTenantId()+"<br/>";
      s += "in "+getTenantId()+"'s db there is "+getNumberOfRecordForTheCurrentTennant() +" contact(s) <br/>";
    
      return s;
  }
  private int getNumberOfRecordForTheCurrentTennant(){
    try{
      String stmt = "select count(*)  from contact;";
      ResultSet rs = routingDataSource.getConnection().createStatement().executeQuery(stmt);
      rs.next();
      int count = rs.getInt("count");
      return count;
    }catch(Exception e){
      e.printStackTrace();
    }
    return -1;
  }
  private String getTenantId(){
    return request.getHeader("ifsrTenantId");
  }
}