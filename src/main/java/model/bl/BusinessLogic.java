package model.bl;

import java.util.List;

public interface BusinessLogic<T, I> {
    T save(T t) throws Exception;

    T update(T t) throws Exception;

    T delete(I id) throws Exception;

    List<T> findAll() throws Exception;

    T findById(I id) throws Exception;
}
