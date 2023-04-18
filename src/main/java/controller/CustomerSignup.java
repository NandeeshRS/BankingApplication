package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customerdao;
import dto.Customer;

@WebServlet("/customersignup")
public class CustomerSignup extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 Customerdao customerdao=new Customerdao();
	
	 long mobile= Long.parseLong(req.getParameter("mobile"));
	 String email=req.getParameter("email");
	  
	  
	  Date date=Date.valueOf(req.getParameter("dob"));
	  int age=Period.between(date.toLocalDate(), LocalDate.now()).getYears();
	  
	  if(age<18){
		  resp.getWriter().print("<h1> you have to be 18+ to create an bank account</h1>");
		  req.getRequestDispatcher("Signup.html").include(req, resp);
	  }else{
		  if(customerdao.check(mobile).isEmpty()&&customerdao.check(email).isEmpty()){
			  Customer customer=new Customer();
			  customer.setName(req.getParameter("name"));
			  customer.setPassword(req.getParameter("password"));
			  customer.setGender(req.getParameter("gender"));
			  customer.setDob(date);
			  customer.setEmail(email);
			  customer.setMobile(mobile);
			  
			  customerdao.save(customer);
			  
			 Customer customer2=customerdao.check(email).get(0);
			 int id=customer2.getCust_id();
			 
			 resp.getWriter().print("<h1>Account created successfully</h1>");
			 if(customer2.getGender().equals("male")){
				 resp.getWriter().print("<h1>Hello Sir</h1>");
			 }else{
				 resp.getWriter().print("<h1>Hello Madam</h1>");
			 }
			 resp.getWriter().print("<h1>Your id is"+id+"</h1>");
			 req.getRequestDispatcher("Home.html").include(req, resp);
		  }
		  else{
			  resp.getWriter().print("<h1>Your email and mobile number is already exist</h1>");
			  req.getRequestDispatcher("Signup.html").include(req, resp);
		  }
	  }

}
}
