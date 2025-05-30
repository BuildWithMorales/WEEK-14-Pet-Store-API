package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetStoreData {
	private Long petStoreId;
	private String storeName;
	private String location;
	
	
	public PetStoreData(pet.store.entity.PetStore petStore) {
		this.petStoreId = petStore.getPetStoreId();
		this.storeName = petStore.getStoreName();
		this.location = petStore.getLocation();				
	}

}
