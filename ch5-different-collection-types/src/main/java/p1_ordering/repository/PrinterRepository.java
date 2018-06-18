package p1_ordering.repository;

import p1_ordering.entity.Printer;

import javax.persistence.EntityManager;

public class PrinterRepository extends BaseRepository<Printer> {

    public PrinterRepository(EntityManager em) {
        super(em);
    }
}
