package java12.daos;

import java12.entities.Agency;
import java12.entities.Customer;
import java12.entities.Owner;

import java.util.List;
import java.util.Map;

public interface AgencyDaoInterface {
    String save(Agency newAgency);
    Agency findById(Long agencyId);
    String deleteById(Long agencyId);
    String updateByID(Long id,Agency newAgency);
    List<Agency> getAllAgencies();

    String assignOwnerToAgency(Long agencyId, Long ownerId);

    Long getCountAgencyInCity(String city);

    List<Owner> getAllOwnersbyAgencyId(Long agencyID);

    String assignAgencyToRentInfo(Long idAgency, Long renInfoId);
}
