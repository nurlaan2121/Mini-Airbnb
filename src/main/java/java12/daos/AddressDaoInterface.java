package java12.daos;

import java12.entities.Address;
import java12.entities.Agency;

import java.util.List;

public interface AddressDaoInterface {
    Address findById(Long addressId);
    String updateByID(Long id,Address newAddress);
    List<Address> getAllAddressesForCheck();

}
