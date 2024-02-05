package java12.services.interfaces;

import java12.entities.House;

import java.sql.Date;
import java.util.List;

public interface HouseInterface {
    String save(House newHouse);
    House findById(Long houseId);
    String deleteById(Long houseId);
    String updateByID(Long id,House newHouse);
    List<House> getAllCustomer();

    List<House> getAllhouseseinOneAgency(Long agencyId);

    List<House> getAllhousesinOneOwner(Long ownerID);

    List<House> getAllHousesBetween(Date in, Date out);
}
