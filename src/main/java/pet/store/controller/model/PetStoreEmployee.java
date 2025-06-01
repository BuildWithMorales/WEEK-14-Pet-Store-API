package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetStoreEmployee {
	
	private Long employeeId;
	private String employeeName;
	private String employeeEmail;
	
	
	public PetStoreEmployee(pet.store.entity.Employee employee) {
		this.employeeId = employee.getEmployeeId();
		this.employeeName = employee.getEmployeename();
		this.employeeEmail = employee.getEmployeeEmail();
	}
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String phone;
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getJobTilte() {
		return jobTitle;
	}
	
	public String getPhone() {
		return phone;
	}

}
