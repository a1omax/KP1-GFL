package gfl.kp1.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gfl.kp1.data.Country;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class CountryRepository extends AbstractRepository<Country> {

    private static CountryRepository INSTANCE;

    private CountryRepository(){
        loadData();
    }

    @Override
    public boolean loadData() {
        Type type = new TypeToken<ArrayList<Country>>() {}.getType();
        try {
            loadFromJSON(dataList, type, getDataFile());
        } catch (IOException e) {
            log.error("Error loading from file" ,e);
            return false;
        }
        return true;
    }

    @Override
    protected String getFileName() {
        return "country";
    }

    public static CountryRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new CountryRepository();
        }
        return INSTANCE;
    }


    public boolean add(String name) {
        int newId = dataList.stream().mapToInt(Country::getId).max().orElse(0)+1;
        return add(newId, name);
    }

    private boolean add(int id, String name){
        boolean isUniqueOnName = dataList.stream().noneMatch(c -> c.getName().equalsIgnoreCase(name));
        if (!isUniqueOnName) {
            return false;
        }
        Country country = Country.builder()
                .id(id)
                .name(name)
                .build();

        dataList.add(country);
        saveData();

        return true;
    }


    public boolean updateItem(int oldCountryId, String name) {
        Optional<Country> oldCountryOptional = getById(oldCountryId);
        if(oldCountryOptional.isEmpty()){
            return false;
        }
        Country oldCountry = oldCountryOptional.get();

        dataList.remove(oldCountry);

        if (!add(oldCountryId, name)){
            dataList.add(oldCountry);
            return false;
        }
        return true;
    }
}
