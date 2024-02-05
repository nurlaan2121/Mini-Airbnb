package java12.services.interfaces;

import java12.entities.Address;

public interface AddressInterface {
    Address findById(Long addressId);
    String updateByID(Long id,Address newAddress);
}
