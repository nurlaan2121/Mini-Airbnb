package java12.daos;

import java12.entities.House;
import java12.entities.Owner;

import java.util.List;

public interface OwnerDaoInterface {
    String save(Owner newOwner);
    String save(Owner newCustomer, House newHouse);
    Owner findById(Long ownerId);
    String deleteById(Long ownerId);
    String updateByID(Long id,Owner newowner);
    List<Owner> getAllOwners();

    List<Owner> getAllOwnersNameAndAge();
}
