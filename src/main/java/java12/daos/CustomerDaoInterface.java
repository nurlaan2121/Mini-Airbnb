package java12.daos;

import java12.entities.Customer;
import java12.entities.RentInfo;

import java.util.List;

public interface CustomerDaoInterface {
    String save(Customer newCustomer);
    String save(Customer newCustomer, Long idRentInfo);
    Customer findById(Long cusId);
    String deleteById(Long cusId);
    String updateByID(Long id,Customer newCustomer);
    List<Customer> getAllCustomer();

}
