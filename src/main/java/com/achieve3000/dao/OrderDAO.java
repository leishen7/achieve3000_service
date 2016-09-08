package com.achieve3000.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class OrderDAO {
	private final JdbcTemplate jt;
	private static final Logger log=LoggerFactory.getLogger(OrderDAO.class);

	private final static String QUERY_CUSTOMER_BY_ORDER_NUM = "select CustomerName from Customers where CustomerID  in (select CustomerID from  orders group by CustomerID having count(*) =?)";
	
	private final static String QUERY_CUSTOMER_BY_ORDER_NUM_0 = "select CustomerName from Customers where CustomerID not in (select distinct CustomerID from  orders)"; 
	
	private final static String QUERY_CUSTOMER_HIGH_VALUE="select CustomerName from Customers where CustomerID  in "+
	"(select CustomerID from (select o.CustomerID,o.OrderID,SUM(od.Quantity*p.Price) amount from  orders o INNER JOIN orderdetails od ON o.OrderID=od.OrderID" +
	" INNER JOIN Products p ON od.ProductID=p.ProductID group by CustomerID,OrderID) co group by CustomerID having avg(amount)>=1000)";

	
	@Autowired
	public OrderDAO(final DataSource dataSource){
		jt=new JdbcTemplate(dataSource);
	}
	public List<String> getCustomerList(int orderNum) throws Exception{	
		
		String sqlStr;		
		if(orderNum==0){
			sqlStr=QUERY_CUSTOMER_BY_ORDER_NUM_0;
		}else{
			sqlStr=QUERY_CUSTOMER_BY_ORDER_NUM;
		}
		return jt.queryForList(sqlStr,new Object[]{new Integer(orderNum)},String.class);
	}
	
	public List<String> getHighValueCustomerList() throws Exception{			
	
		return jt.queryForList(QUERY_CUSTOMER_HIGH_VALUE,new Object[]{},String.class);
	}
	
	
	
}
