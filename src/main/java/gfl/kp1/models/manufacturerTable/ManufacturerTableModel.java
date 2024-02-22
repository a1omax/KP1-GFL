package gfl.kp1.models.manufacturerTable;
import gfl.kp1.data.Country;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.data.Souvenir;
import gfl.kp1.models.AbstractTableModel;
import gfl.kp1.models.countryTable.CountryTableModel;
import gfl.kp1.models.souvenirTable.SouvenirTableModel;
import gfl.kp1.repositories.CountryRepository;
import gfl.kp1.repositories.ManufacturerRepository;
import gfl.kp1.repositories.SouvenirRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ManufacturerTableModel extends AbstractTableModel<Manufacturer> {

    private static ManufacturerTableModel INSTANCE;
    private ManufacturerTableModel(){
    }

    public static ManufacturerTableModel getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ManufacturerTableModel();
        }
        return INSTANCE;
    }

    ManufacturerRepository manufacturerRepository = ManufacturerRepository.getInstance();


    public Country getCountry(Manufacturer manufacturer){
        return CountryTableModel.getInstance().getById(manufacturer.getCountryId()).get();
    }

    @Override
    public boolean deleteItemById(int id) {
        if (!manufacturerRepository.deleteById(id)){
            return false;
        }
        // Delete related souvenirs
        SouvenirTableModel souvenirTableModel = SouvenirTableModel.getInstance();
        List<Souvenir> souvenirs = souvenirTableModel.filterByManufacturer(souvenirTableModel.getAll(), id);
        souvenirs.forEach(s->souvenirTableModel.deleteItemById(s.getId()));

        return true;
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerRepository.getAll();
    }

    @Override
    public Optional<Manufacturer> getById(int id) {
        return manufacturerRepository.getById(id);
    }

    public boolean add(String text, int id) {
        return manufacturerRepository.add(text, id);
    }

    public boolean updateItem(int id, String text, int countryId) {
        return manufacturerRepository.updateItem(id, text, countryId);
    }

    public List<Manufacturer> filterByCountry(List<Manufacturer> manufacturers, int country_id) {
        return manufacturers.stream().filter(m->m.getCountryId()==country_id).toList();
    }
}
