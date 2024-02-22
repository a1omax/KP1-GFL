package gfl.kp1.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gfl.kp1.data.Country;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.data.SouvenirType;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class SouvenirTypeRepository extends AbstractRepository<SouvenirType> {
    private static SouvenirTypeRepository INSTANCE;

    private SouvenirTypeRepository(){
        loadData();
    }

    public static SouvenirTypeRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new SouvenirTypeRepository();
        }
        return INSTANCE;
    }

    @Override
    public boolean loadData() {
        Type type = new TypeToken<ArrayList<SouvenirType>>() {}.getType();
        try {
            loadFromJSON(dataList, type, getDataFile());
        } catch (IOException e) {
            log.error("Error loading from file" ,e);
            return false;
        }
        return true;
    }


    public boolean add(String name) {
        int newId = dataList.stream().mapToInt(SouvenirType::getId).max().orElse(0) + 1;
        return add(newId, name);
    }
    private boolean add(int id, String name){

        boolean isUniqueOnName = dataList.stream().noneMatch(s -> s.getName().equalsIgnoreCase(name));
        if (!isUniqueOnName) {
            return false;
        }
        SouvenirType souvenirType = SouvenirType.builder()
                .id(id)
                .name(name)
                .build();

        dataList.add(souvenirType);
        saveData();
        return true;

    }

    @Override
    protected String getFileName() {
        return "souvenirType";
    }

    public boolean updateItem(int oldSouvenirTypeId, String name) {
        Optional<SouvenirType> oldSouvenirTypeOptional = getById(oldSouvenirTypeId);
        if(oldSouvenirTypeOptional.isEmpty()){
            return false;
        }
        SouvenirType oldSouvenirType = oldSouvenirTypeOptional.get();

        dataList.remove(oldSouvenirType);

        if (!add(oldSouvenirTypeId, name)){
            dataList.add(oldSouvenirType);
            return false;
        }
        return true;
    }
}

