import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.tools.schemaframework.SchemaManager;

import javax.persistence.EntityManager;

/*
 * selbstgebastelt
 */

public class DbHelper {

    private static EntityManager entityManager;
    private static SchemaManager schemaManager;

    public DbHelper(EntityManager entityManager) {
        entityManager = entityManager;
        SchemaManager sm = new SchemaManager(entityManager.unwrap(DatabaseSession.class));
    }

    public static void DROP_TABLES(){
        schemaManager.dropTable("table name");
    }
}
