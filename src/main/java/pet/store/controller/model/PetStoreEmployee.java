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

}
