package gfl.kp1.repositories;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gfl.kp1.data.Country;
import gfl.kp1.data.Manufacturer;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;
@Slf4j
public class ManufacturerRepository extends AbstractRepository<Manufacturer> {

    private static ManufacturerRepository INSTANCE;

    private ManufacturerRepository(){
        loadData();
    }

    @Override
    public boolean loadData() {
        Type type = new TypeToken<ArrayList<Manufacturer>>() {}.getType();
        try {
            loadFromJSON(dataList, type, getDataFile());
        } catch (IOException e) {
            log.error("Error loading from file" ,e);
            return false;
        }
        return true;
    }



    public static ManufacturerRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ManufacturerRepository();
        }
        return INSTANCE;
    }


    @Override
    protected String getFileName() {
        return "manufacturer";
    }

    public boolean add(String name, int countyId) {
        int nextMaxId = dataList.stream().mapToInt(Manufacturer::getId).max().orElse(0) + 1;
        return add(nextMaxId, name, countyId);
    }

    public boolean add(int id, String name, int countyId) {
        boolean isUniqueOnName = dataList.stream().noneMatch(m -> m.getName().equalsIgnoreCase(name));
        if (!isUniqueOnName) {
            return false;
        }
        Manufacturer manufacturer = Manufacturer.builder()
                .id(dataList.stream().mapToInt(Manufacturer::getId).max().orElse(0) + 1)
                .name(name)
                .countryId(countyId)
                .build();

        dataList.add(manufacturer);
        saveData();

        return true;
    }

    public boolean updateItem(int oldManufacturerId, String name, int countryId) {
        Optional<Manufacturer> oldManufacturerOptional = getById(oldManufacturerId);
        if(oldManufacturerOptional.isEmpty()){
            return false;
        }
        Manufacturer oldManufacturer = oldManufacturerOptional.get();

        dataList.remove(oldManufacturer); // direct remove

        if (!add(oldManufacturerId, name, countryId)){
            dataList.add(oldManufacturer);
            return false;
        }
        return true;
    }
}
