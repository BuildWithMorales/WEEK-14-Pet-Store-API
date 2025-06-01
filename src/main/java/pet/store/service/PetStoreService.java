package pet.store.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerRepository;
import pet.store.dao.EmployeeRepository;
import pet.store.dao.PetStoreRepository;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
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
	@Transactional
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		Employee employee = findOrCreateEmployee(petStoreId, petStoreEmployee.getEmployeeId());
		copyEmployeeFields(employee, petStoreEmployee);
		
		employee.setPetstore(petStore);
		petStore.getEmployee().add(employee);
		
		Employee saved = employeeRepo.save(employee);
		return new PetStoreEmployee(saved);
				
		
	}
	private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
	    if (employeeId == null) return new Employee();
	    return findEmployeeById(petStoreId, employeeId);
	}

	private Employee findEmployeeById(Long petStoreId, Long employeeId) {
	    Employee employee = employeeRepo.findById(employeeId)
	        .orElseThrow(() -> new NoSuchElementException("Employee not found"));

	    if (!employee.getPetStore().getPetStoreId().equals(petStoreId)) {
	        throw new IllegalArgumentException("Pet store ID mismatch");
	    }

	    return employee;
	}

	private void copyEmployeeFields(Employee employee, PetStoreEmployee dto) {
	    employee.setFirstName(dto.getFirstName());
	    employee.setLastName(dto.getLastName());
	    employee.setJobTitle(dto.getJobTitle());
	    employee.setPhone(dto.getPhone());
	}
	@Autowired
	private EmployeeRepository employeeRepo;


@Transactional
public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
    PetStore petStore = findOrCreatePetStore(petStoreId);

    Customer customer = findOrCreateCustomer(petStoreId, petStoreCustomer.getCustomerId());
    copyCustomerFields(customer, petStoreCustomer);

    customer.getPetStores().add(petStore);
    petStore.getCustomers().add(customer);

    Customer saved = customerRepo.save(customer);
    return new PetStoreCustomer(saved);
}

private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
    if (customerId == null) return new Customer();
    return findCustomerById(petStoreId, customerId);
}

private Customer findCustomerById(Long petStoreId, Long customerId) {
    Customer customer = customerRepo.findById(customerId)
        .orElseThrow(() -> new NoSuchElementException("Customer not found"));

    boolean found = customer.getPetStores().stream()
        .anyMatch(store -> store.getPetStoreId().equals(petStoreId));

    if (!found) {
        throw new IllegalArgumentException("Customer not related to pet store ID=" + petStoreId);
    }

    return customer;
}

private void copyCustomerFields(Customer customer, PetStoreCustomer dto) {
    customer.setCustomerName(dto.getCustomerName());
    customer.setCustomerEmail(dto.getCustomerEmail());
}
@Autowired
private CustomerRepository customerRepo;

@Transactional 
public List<PetStoreData> retrieveAllPetStores() {
	return petStoreRepo.findAll().stream()
         .map(PetStoreData::new)
         .collect(Collectors.toList());
}
	
	
    @Transactional
	public void deletePetStoreById(Long petStoreId) {
    	PetStore petStore = findOrCreatePetStore(petStoreId);
		petStoreRepo.delete(petStore);
		
    }
		
	
    @Transactional
    public PetStoreData retrievePetStoreById(Long petStoreId) {
        PetStore petStore = findOrCreatePetStore(petStoreId);
        return new PetStoreData(petStore);
    }
} 

