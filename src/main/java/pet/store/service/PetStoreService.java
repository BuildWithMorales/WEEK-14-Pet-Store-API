package pet.store.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreRepository;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreRepository petStoreRepo;
	
	@Transactional
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		
		petStore.setStoreName(petStoreData.getStoreName());
		petStore.setLocation(petStoreData.getLocation());
		
		petStore = petStoreRepo.save(petStore);
		
		return new PetStoreData(petStore);
	
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		if (petStoreId == null) {
			return new PetStore(); 
		}
		return petStoreRepo.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + " not found."));
	}

}
