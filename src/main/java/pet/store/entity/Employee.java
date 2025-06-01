package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import pet.store.controller.model.PetStoreData;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	private String employeename;
	private String employeeEmail;
	
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String phone;
	
	@ManyToOne
	@JoinColumn(name = "pet_store_id")
	private PetStore petstore;

	public PetStoreData getPetStore() {
		return getPetStore();
	}
	
}
