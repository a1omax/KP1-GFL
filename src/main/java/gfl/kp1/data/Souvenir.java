package gfl.kp1.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Value
@AllArgsConstructor
@Builder
@With
public class Souvenir implements Data {
    int id;
    String name;
    String details;
    String date;
    int price;
    int souvenirTypeId;
    int manufacturerId;

    @Override
    public String toString(){
        return name;
    }
    public LocalDate getISODate() {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }
}
