package pe.edu.cibertec.spring_data_jpa_s.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.spring_data_jpa_s.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    
}
