package java12.daos;

import java12.entities.Customer;
import java12.entities.House;

import java.util.List;

public interface HouseDaoInterface {
    String save(House newHouse);
    House findById(Long houseId);
    String deleteById(Long houseId);
    String updateByID(Long id,House newHouse);
    List<House> getAllCustomer();

    List<House> getAllhouseseinOneAgency(Long agencyId);

    List<House> getAllhousesinOneOwner(Long ownerID);
}
