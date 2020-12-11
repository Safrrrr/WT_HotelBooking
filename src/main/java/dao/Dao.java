package dao;

import entities.Entity;
import specification.Specification;

import java.util.List;

public interface Dao < T extends Entity>{
    boolean add(T t) throws DaoException;
    boolean remove(T t) throws DaoException;
    boolean update(T t) throws DaoException;
    List<T>  query(Specification specification) throws DaoException;

}


