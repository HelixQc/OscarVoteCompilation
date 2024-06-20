package oscar.awardService.persistence.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import oscar.electionServices.persistence.JPA.VoteDAO_JPA;
import oscar.electionServices.persistence.OscarPersistenceUnitInfo;
import oscar.awardService.model.Nomination;
import oscar.awardService.persistence.INominationDAO;

import java.util.ArrayList;
import java.util.List;

public class NominationDAO_JPA implements INominationDAO {

    EntityManager em = null;

    public NominationDAO_JPA(){
       /* PersistenceUnitInfo pui = new OscarPersistenceUnitInfo();
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(pui, null);
        this.em = emf.createEntityManager();*/
        this.em = EntityManagerProvider.getEntityManager();
    }


    @Override
    public Nomination findNominationById(int i) {
        return em.find(Nomination.class, i);
    }

    @Override
    public List<Nomination> findAllNomination() {
        Query query = em.createQuery("SELECT n FROM Nomination n");
        return query.getResultList();
    }


    @Override
    public void createNomination(Nomination n) {

        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.persist(n);
            transaction.commit();
        }catch (Exception e){
         if(transaction.isActive()){
             transaction.rollback();
         }
         e.printStackTrace();
        }
    }

    @Override
    public void deleteNomination(Nomination n) {

    }
    @Override
    public Nomination UpdateNomination(Nomination n) {
        return null;
    }

}
