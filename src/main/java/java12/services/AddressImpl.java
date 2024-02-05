package java12.services;

import java12.daos.daosImpl.AddressDaoImpl;
import java12.daos.daosImpl.AgencyDaoImpl;
import java12.entities.Address;
import java12.entities.Agency;
import java12.services.interfaces.AddressInterface;

import java.util.Scanner;

public class AddressImpl implements AddressInterface {
    AddressDaoImpl addressDao = new AddressDaoImpl();
    AgencyDaoImpl agencyDao = new AgencyDaoImpl();

    @Override
    public Address findById(Long addressId) {
        return addressDao.findById(addressId);
    }

    @Override
    public String updateByID(Long id, Address newAddress) {
        Address byId = addressDao.findById(id);
        newAddress.setAgency(byId.getAgency());
        newAddress.setCity(byId.getCity());
        newAddress.setHouse(byId.getHouse());
        newAddress.setStreet(byId.getStreet());
        newAddress.setRegion(byId.getRegion());
        System.out.println("""
                Choose update: 
                1.City
                2.Region
                3.Street     
                """);
        int action = new Scanner(System.in).nextInt();
        switch (action) {
            case 1 -> {
                System.out.println("Write city: ");
                String city = new Scanner(System.in).nextLine();
                newAddress.setCity(city);
            }
            case 2 -> {
                System.out.println("Write region: ");
                newAddress.setRegion(new Scanner(System.in).nextLine());
            }
            case 3 -> {
                System.out.println("Write street");
                newAddress.setStreet(new Scanner(System.in).nextLine());
            }
//            case 4 -> {
//                System.out.println("Write id agency:");
//                Long idAgency = new Scanner(System.in).nextLong();
//                Agency agency = agencyDao.findById(idAgency);
//                agency.setAddress(newAddress);
//                newAddress.setAgency(agency);
//                agencyDao.updateByID(idAgency, agency);
//            }case 5->{
//
//            }
        }
        return addressDao.updateByID(id, newAddress);
    }
}
