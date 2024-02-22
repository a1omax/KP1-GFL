package gfl.kp1.models.countryTable;

import gfl.kp1.data.Country;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.models.AbstractTableModel;
import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import gfl.kp1.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

public class CountryTableModel extends AbstractTableModel<Country> {

    private static CountryTableModel INSTANCE;
    private CountryTableModel(){}

    public static CountryTableModel getInstance() {
        if (INSTANCE == null){
            INSTANCE = new CountryTableModel();
        }
        return INSTANCE;
    }


    CountryRepository countryRepository = CountryRepository.getInstance();
    @Override
    public boolean deleteItemById(int id) {
        if (!countryRepository.deleteById(id)){
            return false;
        }
        // Delete related manufacturers
        ManufacturerTableModel manufacturerTableModel = ManufacturerTableModel.getInstance();
        List<Manufacturer> manufacturers = manufacturerTableModel.filterByCountry(manufacturerTableModel.getAll(), id);
        manufacturers.forEach(s->manufacturerTableModel.deleteItemById(s.getId()));



        return true;
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.getAll();
    }

    @Override
    public Optional<Country> getById(int id) {
        return countryRepository.getById(id);
    }

    public boolean add(String name) {
        return countryRepository.add(name);
    }

    public boolean updateItem(int id, String name) {
        return countryRepository.updateItem(id, name);
    }
}
