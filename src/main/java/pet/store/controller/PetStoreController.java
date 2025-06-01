package pet.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;

@RestController
@RequestMapping("/pet_store")
public class PetStoreController {
	
	private static final Logger log = LoggerFactory.getLogger(PetStoreController.class);
	
	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
		return petStoreService.savePetStore(petStoreData);
	}	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId,
			                           @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		return petStoreService.savePetStore(petStoreData);
	
	}
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(HttpStatus.CREATED)
	public PetStoreEmployee addEmployeeToPetStore(
			@PathVariable Long petStoreId,
			@RequestBody PetStoreEmployee petStoreEmployee
			) {
		     return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}
	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(HttpStatus.CREATED)
	public PetStoreCustomer addCustomerToPetStore(
	    @PathVariable Long petStoreId,
	    @RequestBody PetStoreCustomer petStoreCustomer
	) {
	    return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}
	@GetMapping
	public List<PetStoreData> retrieveAllPetStores() {
		log.info("Retrieving all pet store");
		return petStoreService.retrieveAllPetStores();
	}
	@DeleteMapping("/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting pet store with ID={}", petStoreId);
		petStoreService.deletePetStoreById(petStoreId);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Pet store with ID=" + petStoreId + " successfully deleted.");
		return response;
	}
	@GetMapping("/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
	    log.info("Retrieving pet store with ID={}", petStoreId);
	    return petStoreService.retrievePetStoreById(petStoreId);
	}

}
