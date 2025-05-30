package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class PetStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petStoreId;
	
	private String storeName;
	private String location;
	
	@OneToMany(mappedBy = "petstore", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employee;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "pet_Store_customer",
			joinColumns = @JoinColumn(name = "pet_Store_id"),
			inverseJoinColumns = @JoinColumn(name = "customer_id")
	)
	private Set<Customer> customers;
}
