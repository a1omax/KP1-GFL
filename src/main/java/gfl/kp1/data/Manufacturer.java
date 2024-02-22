package gfl.kp1.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;


@Value
@AllArgsConstructor
@Builder
@With
public class Manufacturer implements Data {
    int id;
    int countryId;
    String name;

    @Override
    public String toString(){
        return name;
    }

}
