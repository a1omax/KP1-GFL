package gfl.kp1.repositories;

import gfl.kp1.data.Country;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CountryRepositoryTest {
    CountryRepository countryRepository = CountryRepository.getInstance();

    @BeforeEach
    void setUp(){
        // remove all data
        countryRepository.dataList = new ArrayList<>();
        countryRepository.saveData();
    }

    @Test
    void loadEmptyData(){
        countryRepository.loadData();

        List<Country> expected = new ArrayList<>();

        Assertions.assertThat(countryRepository.dataList).isEqualTo(expected);
    }

    private List<Country> createCountries(){
        List<Country> countryList = new ArrayList<>();
        Country country1 = Country.builder().id(1).name("Ukraine").build();
        Country country2 = Country.builder().id(2).name("Poland").build();
        Country country3 = Country.builder().id(3).name("Germany").build();
        Country country4 = Country.builder().id(4).name("Norway").build();

        countryList.add(country1);
        countryList.add(country2);
        countryList.add(country3);
        countryList.add(country4);

        return countryList;
    }

    @Test
    void addCountries(){
        // Ukraine, Poland, Germany, Norway
        List<Country> countryList = createCountries();
        countryRepository.add("Ukraine");
        countryRepository.add("Poland");
        countryRepository.add("Germany");
        countryRepository.add("Norway");
        Assertions.assertThat(countryRepository.dataList).isEqualTo(countryList);
    }

    @Test
    void addDuplicate(){
        // Ukraine, Poland, Germany, Norway
        List<Country> countryList = createCountries();
        countryRepository.add("Ukraine");
        countryRepository.add("Poland");
        countryRepository.add("Germany");
        countryRepository.add("Norway");
        countryRepository.add("Norway");
        countryRepository.add("Germany");
        countryRepository.add("Poland");

        Assertions.assertThat(countryRepository.dataList).isEqualTo(countryList);
    }
    @Test
    void getById(){
        // Ukraine, Poland, Germany, Norway
        countryRepository.add("Ukraine");
        countryRepository.add("Poland");
        countryRepository.add("Germany");
        countryRepository.add("Norway");

        int testId = 2;
        Optional<Country> expected = createCountries().stream().filter(c->c.getId()==testId).findAny();

        Assertions.assertThat(countryRepository.getById(testId)).isEqualTo(expected);}

    @Test
    void getByIdThatDoNotExist(){
        // Ukraine, Poland, Germany, Norway
        countryRepository.add("Ukraine");
        countryRepository.add("Poland");
        countryRepository.add("Germany");
        countryRepository.add("Norway");

        int testId = 5;
        Optional<Country> expected = Optional.empty();

        Assertions.assertThat(countryRepository.getById(testId)).isEqualTo(expected);}


    @Test
    void deleteById(){
        // Ukraine, Poland, Germany, Norway
        countryRepository.add("Ukraine");
        countryRepository.add("Poland");
        countryRepository.add("Germany");
        countryRepository.add("Norway");

        int testId = 3;
        boolean result = countryRepository.deleteById(3);

        Assertions.assertThat(result).isEqualTo(true);
        Optional<Country> expected = Optional.empty();
        Assertions.assertThat(countryRepository.getById(testId)).isEqualTo(expected);}


    private Optional<Country> findCountryByName(String name){
        return countryRepository.getAll().stream().filter(c -> c.getName().equals(name)).findAny();
    }

    @Test
    void update(){
        countryRepository.add("Ukraine");
        countryRepository.add("Poland");
        countryRepository.add("Germany");
        countryRepository.add("Norway");

        // getting norway
        Optional<Country> o = findCountryByName("Norway");
        if (o.isEmpty()){
            Assertions.fail("Country should not be null");
            return;
        }
        Country country = o.get();


        // Update Norway
        String newName = "Norway1";

        boolean result = countryRepository.updateItem(country.getId(), newName);
        Assertions.assertThat(result).isEqualTo(true);

        // Id should stay the same so:

        Assertions.assertThat(countryRepository.getById(country.getId()).get().getName()).isEqualTo(newName);
    }


    @Test
    void getAll(){
        List<Country> expectedList = createCountries();

        for(Country c: expectedList){
            countryRepository.add(c.getName());
        }
        Assertions.assertThat(countryRepository.getAll()).isEqualTo(expectedList);
    }

    @Test
    void saveAndLoadSomeData(){
        List<Country> expectedList = createCountries();

        for(Country c: expectedList){
            countryRepository.add(c.getName());
        }
        countryRepository.saveData();

        countryRepository.dataList.clear();

        countryRepository.loadData();
        Assertions.assertThat(countryRepository.getAll()).isEqualTo(expectedList);
    }

}