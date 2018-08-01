package ch7.entity;

import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class PhoneRepository extends BaseRepository<Phone> {

    public PhoneRepository(final EntityManager em) {
        super(em);
    }
}
