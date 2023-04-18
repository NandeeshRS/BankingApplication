<%@page import="dto.Customer"%>
<%@page import="dto.BankAccount"%>
<%@page import="dao.BankDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Balance</title>
</head>
<body>
<% Customer customer=(Customer)session.getAttribute("customer");
if(customer==null){
	response.getWriter().print("<h1>Session Expired</h1>");
	request.getRequestDispatcher("Login.html").include(request, response);
}
else{
%>
<%
long acno=(Long) session.getAttribute("acno");
BankDao bankDao=new BankDao();
 BankAccount account=bankDao.find(acno);

 %>
 
 <h1>
 Hello <%if(account.getCustomer().getGender().equals("male")) {%>Mr.<%}else {%>Ms.<%} %>
 <%=account.getCustomer().getName() %>
 </h1>
 
 <h1>Your <%=account.getType()%> account balance is <%=account.getAmmount() %></h1>
 
 <br><br><br>
 
 <a href="AccountHome.jsp"><button type="submit">Back</button></a>
 
<%} %>
</body>
</html>