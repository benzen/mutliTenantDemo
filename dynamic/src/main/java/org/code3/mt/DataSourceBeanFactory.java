package org.code3.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.BeanFactory;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanFactoryAware;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;

class DataSourceBeanFactory {

  @Autowired
  BeanFactory beanFactory;

  @Autowired
  Properties tenantProperties;

  public Map<String, DataSource> tenantIdToDataSource;

  Logger logger;


  @PostConstruct
  void init(){
    try{
      logger = LoggerFactory.getLogger( this.getClass() );
      tenantIdToDataSource = new HashMap();
       
      for(String tenantId : getTenantList() ){
        treatTenantDataSource( tenantId );
      }
       
      
    }catch(Exception e){
      logger.error(e.getMessage() );
    }
  }
  List<String> getTenantList(){
     String s = tenantProperties.getProperty("tenant.list");
      // logger.info( "Tenant list is "+s );
      String[] tenantsId = s.split(",");
      List tenants = new ArrayList();
      for( int i =0; i<tenantsId.length; i++){

        String trimmedTenantId  = tenantsId[i].trim();
        tenants.add( trimmedTenantId );
      }
      return tenants;
  }
  void treatTenantDataSource(String tenantId ) throws Exception{
    String dbUrl = tenantProperties.getProperty( "db.url." + tenantId );
    if(dbUrl==null){ 
      throw new Exception("No url is defined the db of tenant " + tenantId );
    }
    DataSource ds = (DataSource) beanFactory.getBean("parentDataSource");
    ds.getClass().getMethod("setUrl", String.class ).invoke(ds, dbUrl);

    tenantIdToDataSource.put(tenantId, ds);
  }

}