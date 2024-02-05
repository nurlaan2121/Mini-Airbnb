package java12.services.interfaces;

import java12.entities.Address;
import java12.entities.Agency;
import java12.entities.Owner;

import java.util.List;
import java.util.Map;

public interface AgencyInterface {
    String save(Agency newAgency);
    Agency findById(Long agencyId);
    String deleteById(Long agencyId);
    String updateByID(Long id,Agency newAgency);
    List<Agency> getAllAgencies();

    String assignOwnerTOAgency(Long l, Long l1);

    Map<Address,Agency> getAddressAndAgency();

    Long getTotalCountAgencyInCity(String s);

    Map<String, List<Agency>>  groupByRegion();

    List<Owner> getAllOwnersbyAgencyId(Long agencyID);

    String assignAgencyToRentInfo(Long idAgency, Long renInfoId);
}
