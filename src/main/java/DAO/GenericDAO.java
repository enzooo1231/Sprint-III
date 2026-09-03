package DAO;

import java.util.List;

public interface GenericDAO <T, ID>{

    List<T> listar();
}
