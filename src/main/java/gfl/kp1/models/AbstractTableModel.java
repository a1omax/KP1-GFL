package gfl.kp1.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public abstract class AbstractTableModel<T>{

    public abstract boolean deleteItemById(int id);
    public abstract List<T> getAll();

    public abstract Optional<T> getById(int id);

    private T selectedTableItem;

}
