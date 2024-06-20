package oscar.awardService.persistence.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import oscar.electionServices.persistence.OscarPersistenceUnitInfo;

public class EntityManagerProvider {
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager entityManager = null;

    private EntityManagerProvider() {
    }

    public static synchronized EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            PersistenceUnitInfo pui = new OscarPersistenceUnitInfo();
            EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                    .createContainerEntityManagerFactory(pui,null);
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static synchronized void dispose() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}