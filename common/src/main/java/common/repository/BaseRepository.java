package common.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseRepository<E> {
    protected EntityManager em;

    public BaseRepository(EntityManager em) {
        this.em = em;
    }

    public E createAndSave(E entity) {
        if (em.getTransaction().isActive()) {
            em.persist(entity);
        } else {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }

        System.out.println("[BaseRepository] Persisted Entity: " + entity);

        return entity;
    }

    public List<E> createAndSaveAll(List<E> entities) {
        return entities.stream()
                .map(this::createAndSave)
                .collect(Collectors.toList());
    }

    public List<E> createAndSaveAll(E... entities) {
        return createAndSaveAll(Arrays.asList(entities));
    }

    public E findById(Class<E> clazz, int id) {
        return em.find(clazz, id);
    }

    /**
     * Troubles with inner classes?
     * -> try to use getName() instead of getCanonicalName()
     */
    public List<E> findAll(Class<E> clazz) {
        String className = clazz.getCanonicalName();
        TypedQuery<E> query = em.createQuery("SELECT e FROM " + className + " e", clazz);
        return query.getResultList();
    }

    public void delete(E entity) {
        em.remove(entity);
    }

    public void deleteById(Class<E> clazz, int id) {
        E entity = findById(clazz, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

}
