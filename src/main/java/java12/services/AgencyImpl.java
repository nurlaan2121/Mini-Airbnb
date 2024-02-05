package java12.services;

import java12.daos.AgencyDaoInterface;
import java12.daos.daosImpl.AddressDaoImpl;
import java12.daos.daosImpl.AgencyDaoImpl;
import java12.entities.Address;
import java12.entities.Agency;
import java12.entities.Owner;
import java12.entities.RentInfo;
import java12.services.interfaces.AgencyInterface;


import java.util.*;

public class AgencyImpl implements AgencyInterface {
    AgencyDaoImpl agencyDao = new AgencyDaoImpl();
    AddressDaoImpl addressDao = new AddressDaoImpl();

    @Override
    public String save(Agency newAgency) {
        System.out.println("Write name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Write phone nomber: ");
        String phoneNumber = new Scanner(System.in).nextLine();
        if (phoneNumber.length() != 13 && !phoneNumber.startsWith("+996")) {
            phoneNumber = "+996567891234";
        }
        System.out.println("ADDRESS");
        System.out.println("Write city");
        String city = new Scanner(System.in).nextLine();
        System.out.println("Write region");
        String region = new Scanner(System.in).nextLine();
        System.out.println("Write street");
        String street = new Scanner(System.in).nextLine();

        List<Address> allAddressesForCheck = addressDao.getAllAddressesForCheck();
        for (int i = 0; i < allAddressesForCheck.size(); i++) {
            if (allAddressesForCheck.get(i).getStreet().equalsIgnoreCase(street)) {
                System.out.println("Write address again");
                Map<Integer ,String> map = new HashMap<>();
                map.put(1,"rwe");
                map.put(2,"qoupy");
                map.put(3,"weqe");
                map.put(4,"rqqw");
                map.put(5,"rqqrewee");
                map.put(6,"rew");
                map.put(7,"rqyrwee");
                map.put(8,"rwrye");
                map.put(9,"rwuwye");
                StringBuilder uniqStreet = new StringBuilder();
                for (int j = 0; j < map.size(); j++) {
                    Random random = new Random();
                    int integer = random.nextInt(9);
                    uniqStreet.append(map.get(integer));
                }
                newAgency.getAddress().setStreet(uniqStreet.toString());
            }
        }
        newAgency.getAddress().setCity(city);
        if (newAgency.getAddress().getStreet()==null){
            newAgency.getAddress().setStreet(street);
        }
        newAgency.getAddress().setRegion(region);
        newAgency.getAddress().setAgency(newAgency);
        newAgency.setPhoneNumber(phoneNumber);
        newAgency.setName(name);
        List<RentInfo> rentInfoList = new ArrayList<>();
        List<Owner> owners = new ArrayList<>();
        newAgency.setOwners(owners);
        newAgency.setRentInfoList(rentInfoList);

        return agencyDao.save(newAgency);
    }

    @Override
    public Agency findById(Long agencyId) {
        return agencyDao.findById(agencyId);
    }

    @Override
    public String deleteById(Long agencyId) {
        if (agencyId > 0) {
            return agencyDao.deleteById(agencyId);
        } else {
            return "KOICHU";
        }

    }

    @Override
    public String updateByID(Long id, Agency newAgency) {
        if (id > 0) {
            Agency byId = findById(id);
            newAgency.setAddress(byId.getAddress());
            newAgency.setPhoneNumber(byId.getPhoneNumber());
            newAgency.setOwners(byId.getOwners());
            newAgency.setName(byId.getName());
            newAgency.setRentInfoList(byId.getRentInfoList());
            System.out.println("""
                    Choose update
                    1.Phone number
                    2.Name                   """);
            int action = new Scanner(System.in).nextInt();
            switch (action) {
                case 1 -> {
                    System.out.println("Write number");
                    String number = new Scanner(System.in).nextLine();
                    newAgency.setPhoneNumber("0000000000000");
                }
                case 2 -> {
                    System.out.println("Write name: ");
                    String name = new Scanner(System.in).nextLine();
                    newAgency.setName(name);
                }
            }
            return agencyDao.updateByID(id, newAgency);
        } else {
            return "KOICHU";
        }
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyDao.getAllAgencies();
    }

    @Override
    public String assignOwnerTOAgency(Long agencyId, Long ownerId) {
        return agencyDao.assignOwnerToAgency(agencyId, ownerId);
    }

    @Override
    public Map<Address, Agency> getAddressAndAgency() {
        List<Agency> allAgencies = agencyDao.getAllAgencies();
        Map<Address, Agency> addressAgencyMap = new HashMap<>();
        for (int i = 0; i < allAgencies.size(); i++) {
            addressAgencyMap.put(allAgencies.get(i).getAddress(), allAgencies.get(i));
        }
        return addressAgencyMap;
    }

    @Override
    public Long getTotalCountAgencyInCity(String city) {
        return agencyDao.getCountAgencyInCity(city);
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        List<Agency> allAgencies = agencyDao.getAllAgencies();
        Map<String, List<Agency>> listMap = new HashMap<>();
        for (int i = 0; i < allAgencies.size(); i++) {
            List<Agency> agencies = new ArrayList<>();
            String region = allAgencies.get(i).getAddress().getRegion();
            for (int i1 = 0; i1 < allAgencies.size(); i1++) {
                if (region.equalsIgnoreCase(allAgencies.get(i).getAddress().getRegion())) {
                    agencies.add(allAgencies.get(i));
                }
            }
            listMap.put(allAgencies.get(i).getAddress().getRegion(), agencies);
        }
        return listMap;
    }

    @Override
    public List<Owner> getAllOwnersbyAgencyId(Long agencyID) {
        return agencyDao.getAllOwnersbyAgencyId(agencyID);
    }

    @Override
    public String assignAgencyToRentInfo(Long idAgency, Long renInfoId) {
        return agencyDao.assignAgencyToRentInfo(idAgency, renInfoId);
    }
}
