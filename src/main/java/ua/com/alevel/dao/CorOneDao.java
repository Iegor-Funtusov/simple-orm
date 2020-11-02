package ua.com.alevel.dao;

import java.util.Collection;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 9:28 PM
 */

public interface CorOneDao<E, ID> {

    E create(E e);
    E update(E e);
    boolean delete(ID id);
    E findById(ID id);
    Collection<E> findAll();
}
