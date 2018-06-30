package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Set;

/*
 * Stateful Session Bean
 *
 * Hat state
 * Eignet sich für die Kapselung von Business Services.
 * Entweder mit oder ohne Interface.
 *
 * Lifecycle Callbacks:
 *  @PostConstruct, @PreDestroy (wie Stateless Session Bean)
 *  @Remove: Instanz wird nach der Methode zerstört
 *  @PrePassivate: vor Serialisierung der Instanz
 *  @PostActivate: nach Deserialisierung der Instanz
 */

@Stateful
public class ShoppingCart {
    Connection con;
    private HashMap<String, Integer> items = new HashMap<>(); // state

    @PostConstruct
    void init(){
        // aquire Connection
    }

    @PrePassivate
    void passivate(){
        // release Connection
    }

    @PostActivate
    void activate(){
        // aquire Connection
    }

    @PreDestroy
    void shutdown(){
        // release Connection
    }

    @Remove
    public void checkout(int paymentId){
        // store items to db
    }

    @Remove
    public void cancel(){
    }

    public void addItem(String item, int quantity) {
        Integer orderQuantity = items.get(item);
        if (orderQuantity == null) {
            orderQuantity = 0;

        }
        orderQuantity += quantity;
        items.put(item, orderQuantity);
    }

    public Set<String> listOrders(){
        return items.keySet();
    }
}
