package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	private String customerName;
	private String customerEmail;
	
	@ManyToMany(mappedBy = "customers")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PetStore> petStores = new HashSet<>();

}
