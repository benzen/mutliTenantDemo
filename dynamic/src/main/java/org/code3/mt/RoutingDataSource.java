package org.code3.mt;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
 
 @Service
 @Scope("request")
class RoutingDataSource extends AbstractRoutingDataSource {

   @Autowired 
   private HttpServletRequest request;


   @Override
   protected Object determineCurrentLookupKey() {
   	if(request == null ){throw new RuntimeException("Pas de request");}
   	  return request.getHeader("ifsrTenantId");
   }

}
