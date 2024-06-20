package oscar.electionServices.persistence.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import oscar.awardService.persistence.JPA.EntityManagerProvider;
import oscar.electionServices.persistence.OscarPersistenceUnitInfo;
import oscar.electionServices.model.Elector;
import oscar.electionServices.persistence.IElectorDAO;

import java.util.List;

public class ElectorDAO_JPA implements IElectorDAO {

    EntityManager em = null;

    public ElectorDAO_JPA(){
       /* PersistenceUnitInfo pui = new OscarPersistenceUnitInfo();
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(pui, null);
        this.em = emf.createEntityManager();*/
        this.em = EntityManagerProvider.getEntityManager();
    }

    public Elector findElectorById(int id){
        return em.find(Elector.class, id );
    }

    @Override
    public void createElector(Elector e) {

        EntityTransaction transaction = em.getTransaction();
        try {

            transaction.begin();
            em.persist(e);
            transaction.commit();

        } catch (Exception eee) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            eee.printStackTrace();
        }
    }

    @Override
    public List<Elector> readElector() {
        Query query = em.createQuery("SELECT e FROM Elector e");
        return query.getResultList();
    }



    @Override
    public Elector updateElector(Elector e) {
        return null;
    }
    @Override
    public void deleteElector(Elector e) {

    }


    public Elector findElectorByWeight(int weight) {
        return null;
    }
}
