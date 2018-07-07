package ch5.repository;

import ch5.entity.PrinterQueue;
import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class PrinterQueueRepository extends BaseRepository<PrinterQueue> {

    public PrinterQueueRepository(EntityManager em) {
        super(em);
    }
}
