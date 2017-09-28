package com.spring.study.config;

import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DataSourceConfig {
	private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	
	    @Value("${spring.datasource.url}")  
	    private String dbUrl;  
	      
	    @Value("${spring.datasource.type}")  
	    private String dbType;  
	      
	    @Value("${spring.datasource.username}")  
	    private String username;  
	      
	    @Value("${spring.datasource.password}")  
	    private String password;  
	      
	    @Value("${spring.datasource.driver-class-name}")  
	    private String driverClassName;  
	      
	    @Value("${spring.datasource.initial-size}")  
	    private int initialSize;  
	      
	    @Value("${spring.datasource.min-idle}")  
	    private int minIdle;  
	      
	    @Value("${spring.datasource.max-active}")  
	    private int maxActive;  
	      
	    @Value("${spring.datasource.max-wait}")  
	    private int maxWait;  
	      
	    @Value("${spring.datasource.time-between-eviction-runs-millis}")  
	    private int timeBetweenEvictionRunsMillis;  
	      
	    @Value("${spring.datasource.min-evictable-idle-time-millis}")  
	    private int minEvictableIdleTimeMillis;  
	      
	    @Value("${spring.datasource.validation-query}")  
	    private String validationQuery;  
	      
	    @Value("${spring.datasource.test-while-idle}")  
	    private boolean testWhileIdle;  
	      
	    @Value("${spring.datasource.test-on-borrow}")  
	    private boolean testOnBorrow;  
	      
	    @Value("${spring.datasource.test-on-return}")  
	    private boolean testOnReturn;  
	      
	    @Value("${spring.datasource.pool-prepared-statements}")  
	    private boolean poolPreparedStatements;  
	      
	    /*@Value("${spring.datasource.filters}")  
	    private String filters;  */
	      
	    @Value("${spring.datasource.connection-properties}")  
	    private String connectionProperties;  

	    /*    
	    @Value("${spring.datasource.useGlobalDataSourceStat}")  
	    private boolean useGlobalDataSourceStat;  
	      
	   @Value("${spring.datasource.druidLoginName}")  
	    private String druidLoginName;  
	      
	    @Value("${spring.datasource.druidPassword}")  
	    private String druidPassword;  */
	      
	    
	    @ConfigurationProperties(prefix = "spring.datasource")
	    @Bean(name="dataSource",destroyMethod = "close", initMethod="init")  
	    @Primary //不要漏了这  
	    public DataSource dataSource(){
	    	//Date date = new Date();
	    	logger.info("DataSource 实例化开始" + new Date());
	        DruidDataSource datasource = new DruidDataSource(); 
	        try {
	        	datasource.setUrl(this.dbUrl);    
	        	datasource.setDbType(dbType);  
	        	datasource.setUsername(username);    
	        	datasource.setPassword(password);    
	        	datasource.setDriverClassName(driverClassName);    
	        	datasource.setInitialSize(initialSize);    
	        	datasource.setMinIdle(minIdle);    
	        	datasource.setMaxActive(maxActive);    
	        	datasource.setMaxWait(maxWait);    
	        	datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);    
	        	datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);    
	        	datasource.setValidationQuery(validationQuery);    
	        	datasource.setTestWhileIdle(testWhileIdle);    
	        	datasource.setTestOnBorrow(testOnBorrow);    
	        	datasource.setTestOnReturn(testOnReturn);    
	        	datasource.setPoolPreparedStatements(poolPreparedStatements);    
	        	datasource.setConnectionProperties(connectionProperties);    
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				logger.info("DataSource 实例化结束" + new Date());
			}
	        return datasource;
	    }
	    
	    /////////  下面是druid 监控访问的设置  /////////////////  
	    @Bean  
	    public ServletRegistrationBean druidServlet() {  
	        ServletRegistrationBean reg = new ServletRegistrationBean();  
	        reg.setServlet(new StatViewServlet());  
	        reg.addUrlMappings("/druid/*");  //url 匹配  
	        reg.addInitParameter("allow", "223.71.249.186,127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
	        reg.addInitParameter("deny", "192.168.16.111"); //IP黑名单 (存在共同时，deny优先于allow)  
	        reg.addInitParameter("loginUsername", "dingxingkai");//登录名  
	        reg.addInitParameter("loginPassword", "123456");//登录密码  
	        reg.addInitParameter("resetEnable", "false"); // 禁用HTML页面上的“Reset All”功能  
	        return reg;  
	    }  
	  /**
	   * 配置druid过滤规则
	   * @return
	   */
	    @Bean(name="druidWebStatFilter")  
	    public FilterRegistrationBean filterRegistrationBean() {  
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
	        filterRegistrationBean.setFilter(new WebStatFilter());  
	        filterRegistrationBean.addUrlPatterns("/*");  
	        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); //忽略资源  
	        filterRegistrationBean.addInitParameter("profileEnable", "true");  
	        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");  
	        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");  
	        return filterRegistrationBean;  
	    }  

}
