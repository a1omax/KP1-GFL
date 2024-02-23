package gfl.kp1.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gfl.kp1.data.Country;
import gfl.kp1.data.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

@Slf4j
public abstract class AbstractRepository<T extends Data> {

    private static final Gson gson = new Gson();
    protected static final String FILE_EXT = ".json";
    protected List<T> dataList = new ArrayList<>();

    protected abstract String getFileName();


    public List<T> getAll() {
        return new ArrayList<>(dataList);
    }


    public Optional<T> getById(int id) {
        return dataList.stream()
                .filter(s -> s.getId() == id)
                .findAny();
    }
    public boolean deleteById(int id) {
        Optional<T> deleteElement = getById(id);
        return deleteElement.map(t -> dataList.remove(t)).orElse(false);
    }

    protected File getDataFile() throws IOException {
        URL resourceUrl = AbstractRepository.class.getResource("/data/" + getFileName() + FILE_EXT);


        return new File(resourceUrl.getFile());
    }

    public boolean saveData(){

        try(FileWriter fw = new FileWriter(getDataFile())) {
            gson.toJson(dataList, fw);
        } catch (IOException e) {
            log.error("Error writing data in file ", e);
            return false;
        }
        return true;
    }

    abstract public boolean loadData();

    protected static <T> boolean loadFromJSON(Collection<T> collection, Type type, File file){
        collection.clear();
        try (FileReader reader = new FileReader(file)) {
            Collection<T> loadedCollection = gson.fromJson(reader, type);

            collection.clear();
            collection.addAll(loadedCollection);
        } catch (IOException e) {
            log.error("Error reading data from file ", e);
            return false;
        }

        return true;
    }
}
