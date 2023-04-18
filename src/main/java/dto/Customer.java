package dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(generator="custid")
	@SequenceGenerator(initialValue=12110111,allocationSize=1,name="custid",sequenceName="custid")
	int cust_id;
	String name;
	String email;
	String password;
	long mobile;
	Date dob;
	String gender;
	
	@OneToMany
	List<BankAccount> accounts;
	
}
