package com.p2p.webapp.common.intercepts;
/**  
 * ��ҳ��������  
 */  
import java.sql.Connection;   
import java.sql.PreparedStatement;   
import java.sql.ResultSet;   
import java.util.HashMap;
import java.util.Properties;   
  
import org.apache.commons.jxpath.JXPathContext;   
import org.apache.commons.jxpath.JXPathNotFoundException;   
import org.apache.ibatis.executor.Executor;   
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;   
import org.apache.ibatis.mapping.BoundSql;   
import org.apache.ibatis.mapping.MappedStatement;   
import org.apache.ibatis.mapping.MappedStatement.Builder;   
import org.apache.ibatis.mapping.ParameterMapping;   
import org.apache.ibatis.mapping.SqlSource;   
import org.apache.ibatis.plugin.Interceptor;   
import org.apache.ibatis.plugin.Intercepts;   
import org.apache.ibatis.plugin.Invocation;   
import org.apache.ibatis.plugin.Plugin;   
import org.apache.ibatis.plugin.Signature;   
import org.apache.ibatis.session.ResultHandler;   
import org.apache.ibatis.session.RowBounds;   

import com.p2p.webapp.common.page.Page;
  
@Intercepts({@Signature(type=Executor.class,method="query",args={ MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })})   
public class PageIntercept implements Interceptor{   
  
  public Object intercept(Invocation invocation) throws Throwable {   
       
    //��ǰ���� MappedStatement��BoundSql����sqlȡ��   
    MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];
    Object parameter = invocation.getArgs()[1];    
    BoundSql boundSql = mappedStatement.getBoundSql(parameter);
    String originalSql = boundSql.getSql().trim();   
    Object parameterObject = boundSql.getParameterObject();
    //Page�����ȡ������ʹ��������������   
    Page page = searchPageWithXpath(boundSql.getParameterObject(),".","page","*/page");

  
    if(page!=null ){
      //Page������ڵĳ��ϣ���ʼ��ҳ����   
      String countSql = getCountSql(originalSql);   
      Connection connection=mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
      PreparedStatement countStmt = connection.prepareStatement(countSql);
      BoundSql countBS = copyFromBoundSql(mappedStatement, boundSql, countSql);   
      DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, countBS);   
      parameterHandler.setParameters(countStmt);   
      ResultSet rs = countStmt.executeQuery();   
      int totpage=0;   
      if (rs.next()) {
        totpage = rs.getInt(1);     
      }   
      rs.close();
      countStmt.close();     
      connection.close();   
         
      //��ҳ����   
      page.setTotalRecord(totpage);   
         
      //��ԭʼSql׷��limit   
      int offset = (page.getPageNo() - 1) * page.getPageSize();   
      StringBuffer sb = new StringBuffer();   
      sb.append(originalSql).append(" limit ").append(offset).append(",").append(page.getPageSize());   
      BoundSql newBoundSql = copyFromBoundSql(mappedStatement, boundSql, sb.toString());   
      MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));     
      invocation.getArgs()[0]= newMs;     
    }
    return invocation.proceed();   
       
  }   
     
  /**  
   * ���ݸ�����xpath��ѯPage����  
   */  
  private Page searchPageWithXpath(Object o,String... xpaths) {   
    JXPathContext context = JXPathContext.newContext(o);   
    Object result;   
    for(String xpath : xpaths){   
      try {
        result = context.selectSingleNode(xpath);   
      } catch (JXPathNotFoundException e) {   
        continue;   
      }
      if ( result instanceof Page ){   
        return (Page)result;   
      }   
    }   
    return null;   
  }   
  
  /**  
   * ����MappedStatement����  
   */  
  private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {   
    Builder builder = new Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());   
       
    builder.resource(ms.getResource());   
    builder.fetchSize(ms.getFetchSize());   
    builder.statementType(ms.getStatementType());   
    builder.keyGenerator(ms.getKeyGenerator());   
    //builder.keyProperty(ms.getKeyProperties()[0]);   
    builder.timeout(ms.getTimeout());   
    builder.parameterMap(ms.getParameterMap());   
    builder.resultMaps(ms.getResultMaps());   
    builder.resultSetType(ms.getResultSetType());   
    builder.cache(ms.getCache());   
    builder.flushCacheRequired(ms.isFlushCacheRequired());   
    builder.useCache(ms.isUseCache());   
       
    return builder.build();   
  }   
  
  /**  
   * ����BoundSql����  
   */  
  private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {   
    BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),sql, boundSql.getParameterMappings(), boundSql.getParameterObject());   
    for (ParameterMapping mapping : boundSql.getParameterMappings()) {   
        String prop = mapping.getProperty();   
        if (boundSql.hasAdditionalParameter(prop)) {   
            newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));   
        }   
    }   
    return newBoundSql;   
  }   
  
  /**  
   * ����ԭSql����ȡ��Ӧ�Ĳ�ѯ�ܼ�¼����Sql���  
   */  
  private String getCountSql(String sql) {   
    return "SELECT COUNT(*) FROM (" + sql + ") aliasForPage";   
  }   
  
  public class BoundSqlSqlSource implements SqlSource {     
      BoundSql boundSql;     
      public BoundSqlSqlSource(BoundSql boundSql) {     
        this.boundSql = boundSql;     
      }     
      public BoundSql getBoundSql(Object parameterObject) {     
        return boundSql;     
      }     
    }     
  public Object plugin(Object arg0) {   
     return Plugin.wrap(arg0, this);   
  }   
  public void setProperties(Properties arg0) {   
  }   
}  