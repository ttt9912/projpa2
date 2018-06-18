package repository;

import javax.persistence.EntityManager;
import java.util.Collection;

public class BaseRepository<E> {
    protected EntityManager em;

    public BaseRepository(EntityManager em) {
        this.em = em;
    }

    public E persist(E entity) {
        em.persist(entity);
        return entity;
    }

    public Collection<E> persistAll(Collection<E> entities) {
        entities.forEach(this::persist);
        return entities;
    }


}
