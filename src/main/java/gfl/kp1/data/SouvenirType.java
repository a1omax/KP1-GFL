package gfl.kp1.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@AllArgsConstructor
@Builder
@Value
@With
public class SouvenirType implements Data {

    int id;
    String name;

    @Override
    public String toString(){
        return name;
    }
}
