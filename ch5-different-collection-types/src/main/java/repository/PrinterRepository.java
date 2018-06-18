package repository;

import entity.Printer;

import javax.persistence.EntityManager;

public class PrinterRepository extends BaseRepository<Printer> {

    public PrinterRepository(EntityManager em) {
        super(em);
    }
}
