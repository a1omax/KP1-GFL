package gfl.kp1.models.souvenirTypeTable;

import gfl.kp1.data.SouvenirType;
import gfl.kp1.models.AbstractTableModel;
import gfl.kp1.models.souvenirTable.SouvenirTableModel;
import gfl.kp1.repositories.SouvenirTypeRepository;
import gfl.kp1.data.Souvenir;

import java.util.List;
import java.util.Optional;


public class SouvenirTypeTableModel extends AbstractTableModel<SouvenirType> {

    private static SouvenirTypeTableModel INSTANCE;
    private SouvenirTypeTableModel(){}

    public static SouvenirTypeTableModel getInstance() {
        if (INSTANCE == null){
            INSTANCE = new SouvenirTypeTableModel();
        }
        return INSTANCE;
    }


    SouvenirTypeRepository souvenirTypeRepository = SouvenirTypeRepository.getInstance();
    @Override
    public boolean deleteItemById(int id) {
        if (!souvenirTypeRepository.deleteById(id)){
            return false;
        }
        // Delete related souvenirs
        SouvenirTableModel souvenirTableModel = SouvenirTableModel.getInstance();
        List<Souvenir> souvenirs = souvenirTableModel.filterBySouvenirType(souvenirTableModel.getAll(), id);
        souvenirs.forEach(s->souvenirTableModel.deleteItemById(s.getId()));

        return true;
    }

    @Override
    public List<SouvenirType> getAll() {
        return souvenirTypeRepository.getAll();
    }

    @Override
    public Optional<SouvenirType> getById(int id) {
        return souvenirTypeRepository.getById(id);
    }

    public boolean add(String name) {
        return souvenirTypeRepository.add(name);
    }

    public boolean updateItem(int id, String name) {
        return souvenirTypeRepository.updateItem(id, name);
    }
}
