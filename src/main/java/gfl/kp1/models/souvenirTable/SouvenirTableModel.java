package gfl.kp1.models.souvenirTable;
import gfl.kp1.data.Country;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.data.Souvenir;
import gfl.kp1.data.SouvenirType;
import gfl.kp1.models.AbstractTableModel;
import gfl.kp1.repositories.CountryRepository;
import gfl.kp1.repositories.ManufacturerRepository;
import gfl.kp1.repositories.SouvenirRepository;
import gfl.kp1.repositories.SouvenirTypeRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class SouvenirTableModel extends AbstractTableModel<Souvenir> {


    private static SouvenirTableModel INSTANCE;

    private SouvenirTableModel(){};

    public static SouvenirTableModel getInstance() {
        if (INSTANCE == null){
            INSTANCE = new SouvenirTableModel();
        }
        return INSTANCE;
    }


    SouvenirRepository souvenirRepository = SouvenirRepository.getInstance();





    public List<Souvenir> filterByMaxPrice(List<Souvenir> souvenirList, int maxPrice) {
        return souvenirList.stream()
                .filter((s) -> s.getPrice() <= maxPrice)
                .toList();
    }
    public List<Souvenir> filterByYear(List<Souvenir> souvenirList, int year) {

        return souvenirList.stream()
                .filter((s) -> s.getISODate().getYear() == year)
                .toList();
    }


    public List<Souvenir> filterByManufacturer(List<Souvenir> souvenirList, int manufacturerId) {
        return souvenirList.stream()
                .filter((s) -> s.getManufacturerId() == manufacturerId)
                .toList();
    }
    public List<Souvenir> filterBySouvenirType(List<Souvenir> souvenirList, int souvenirTypeId) {
        return souvenirList.stream()
                .filter((s) -> s.getSouvenirTypeId() == souvenirTypeId)
                .toList();
    }

    public List<Souvenir> filterByCountry(List<Souvenir> souvenirList, int countryId) {
        ManufacturerRepository manufacturerModel = ManufacturerRepository.getInstance();
        return souvenirList.stream()
                .filter((s)->{
                    Optional<Manufacturer> optionalManufacturer = manufacturerModel.getById(s.getManufacturerId());
                    return optionalManufacturer.filter(manufacturer -> manufacturer.getCountryId() == countryId).isPresent();
                }).toList();
    }

    public Manufacturer getManufacturer(Souvenir souvenir){
        return ManufacturerRepository.getInstance().getById(souvenir.getManufacturerId()).get();
    }

    public SouvenirType getSouvenirType(Souvenir souvenir){
        return SouvenirTypeRepository.getInstance().getById(souvenir.getSouvenirTypeId()).get();
    }

    public Country getCountry(Souvenir souvenir){
        Manufacturer manufacturer = getManufacturer(souvenir);
        return CountryRepository.getInstance().getById(manufacturer.getCountryId()).get();
    }


    @Override
    public boolean deleteItemById(int id) {
        return souvenirRepository.deleteById(id);
    }

    @Override
    public List<Souvenir> getAll() {
        return souvenirRepository.getAll();
    }

    @Override
    public Optional<Souvenir> getById(int id) {
        return souvenirRepository.getById(id);
    }

    public boolean add(String name, String details, String date, int price, int souvenirTypeId, int manufacturerId) {
        return souvenirRepository.add(name, details, date, price, souvenirTypeId, manufacturerId);

    }

    public boolean updateItem(int id, String name, String details, String date, int price, int souvenirTypeId, int manufacturerId) {
        return souvenirRepository.updateItem(id, name, details, date, price, souvenirTypeId, manufacturerId);
    }

}
