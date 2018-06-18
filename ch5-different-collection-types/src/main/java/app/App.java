package app;

import entity.IPAddress;
import entity.Printer;
import entity.PrinterQueue;
import repository.PrinterQueueRepository;
import repository.PrinterRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class App {

    private static final String PU_NAME = "persistenceUnit";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU_NAME);
        EntityManager em = emf.createEntityManager();

        PrinterRepository printerRepository = new PrinterRepository(em);
        PrinterQueueRepository printerQueueRepository = new PrinterQueueRepository(em);

        // Create and persist Printer and PrinterQueues
        em.getTransaction().begin();
        Printer printer = new Printer();
        Collection<PrinterQueue> printerQueues = printerQueueRepository.persistAll(createRandomPrinterQueues());
        printer.setPrinterQueues((List<PrinterQueue>) printerQueues);
        printer.setIpAddresses(createRandomIpAddresses());
        printerRepository.persist(printer);
        em.getTransaction().commit();

        System.out.println(printer);
    }

    private static List<PrinterQueue> createRandomPrinterQueues() {
        List<PrinterQueue> printerQueues = new ArrayList<>();
        printerQueues.add(new PrinterQueue(5));
        printerQueues.add(new PrinterQueue(10));
        printerQueues.add(new PrinterQueue(2));
        return printerQueues;
    }

    private static List<IPAddress> createRandomIpAddresses() {
        List<IPAddress> ipAddresses = new ArrayList<>();
        ipAddresses.add(new IPAddress("192.168.192.1"));
        ipAddresses.add(new IPAddress("255.1.1.1"));
        ipAddresses.add(new IPAddress("98.0.0.2"));
        return ipAddresses;
    }

}
