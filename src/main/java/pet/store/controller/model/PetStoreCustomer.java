package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetStoreCustomer {
	
	private Long customerId;
	private String customerName;
	private String customerEmail;
	
	//
	public PetStoreCustomer(pet.store.entity.Customer customer) {
		this.customerId = customer.getCustomerId();
		this.customerName = customer.getCustomerName();
		this.customerEmail = customer.getCustomerEmail();	
		}

}
