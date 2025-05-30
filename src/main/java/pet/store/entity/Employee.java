package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;
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
	
	@ManyToOne
	@JoinColumn(name = "pet_store_id")
	private PetStore petstore;

}
