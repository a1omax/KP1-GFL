package gfl.kp1.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.data.Souvenir;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
public class SouvenirRepository extends AbstractRepository<Souvenir> {

    private static SouvenirRepository INSTANCE;

    private SouvenirRepository(){
        loadData();
    }

    public static SouvenirRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new SouvenirRepository();
        }
        return INSTANCE;
    }


    @Override
    public boolean loadData() {
        Type type = new TypeToken<ArrayList<Souvenir>>() {}.getType();
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
        return "souvenir";
    }

    public boolean add(String name, String details, String date, int price, int souvenirTypeId, int manufacturerId) {

        Souvenir newSouvenir = Souvenir.builder()
                .id(dataList.stream().mapToInt(Souvenir::getId).max().orElse(0) + 1)
                .name(name)
                .details(details)
                .date(date)
                .price(price)
                .souvenirTypeId(souvenirTypeId)
                .manufacturerId(manufacturerId)
                .build();

        return add(newSouvenir);
    }
    protected boolean add(Souvenir souvenir){
        boolean isUniqueOnManufacturerAndName = dataList.stream()
                .noneMatch(s -> s.getManufacturerId() == souvenir.getManufacturerId() && s.getName().equalsIgnoreCase(souvenir.getName()));

        if (!isUniqueOnManufacturerAndName) {
            return false;
        }
        dataList.add(souvenir);
        saveData();
        return true;
    }


    // add + delete
    public boolean updateItem(int oldSouvenirId, String name, String details, String date, int price, int souvenirTypeId, int manufacturerId){
        Optional<Souvenir> optionalSouvenir = this.getById(oldSouvenirId);
        if (optionalSouvenir.isEmpty()){
            return false;
        }
        Souvenir old = optionalSouvenir.get();

        this.dataList.remove(old);

        Souvenir newSouvenir = Souvenir.builder()
                .id(oldSouvenirId)
                .name(name)
                .details(details)
                .date(date)
                .price(price)
                .souvenirTypeId(souvenirTypeId)
                .manufacturerId(manufacturerId)
                .build();

        if (!this.add(newSouvenir)){
            this.dataList.add(old);
            return false;
        }
        saveData();
        return true;
    }


    public List<Souvenir> getByManufacturerId(int manufacturerId){
        return dataList.stream()
                .filter(souvenir -> souvenir.getManufacturerId() == manufacturerId)
                .toList();
    }

}
