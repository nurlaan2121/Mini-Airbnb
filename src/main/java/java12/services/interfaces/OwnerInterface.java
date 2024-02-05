package java12.services.interfaces;

import java12.entities.House;
import java12.entities.Owner;

import java.util.List;

public interface OwnerInterface {
    String save(Owner newOwner);
    String save(Owner newCustomer, House newHouse);
    Owner findById(Long ownerId);
    String deleteById(Long ownerId);
    String updateByID(Long id,Owner newowner);
    List<Owner> getAllOwners();

    List<Owner> getAllOwnersNameAndAge();
}
