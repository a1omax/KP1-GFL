package gfl.kp1.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@AllArgsConstructor
@Builder
@With
public class Country implements Data {
    int id;
    String name;

    @Override
    public String toString(){
        return name;
    }
}
