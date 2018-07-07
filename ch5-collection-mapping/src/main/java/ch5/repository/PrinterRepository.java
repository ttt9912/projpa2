package ch5.repository;

import ch5.entity.Printer;
import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class PrinterRepository extends BaseRepository<Printer> {

    public PrinterRepository(EntityManager em) {
        super(em);
    }
}
