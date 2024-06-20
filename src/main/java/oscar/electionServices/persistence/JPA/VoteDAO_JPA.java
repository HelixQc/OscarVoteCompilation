package oscar.electionServices.persistence.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import oscar.awardService.persistence.JPA.EntityManagerProvider;
import oscar.electionServices.persistence.OscarPersistenceUnitInfo;
import oscar.electionServices.model.Vote;
import oscar.electionServices.persistence.IVoteDAO;

import java.util.List;

public class VoteDAO_JPA implements IVoteDAO {

    EntityManager em = null;

    public VoteDAO_JPA(){

      /*  PersistenceUnitInfo pui = new OscarPersistenceUnitInfo();
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(pui, null);
        this.em = emf.createEntityManager();*/
        this.em = EntityManagerProvider.getEntityManager();

    }

    @Override
    public Vote findVoteById(int id){
        return this.em.find(Vote.class, id);
    }

    @Override
    public void createVote(Vote v) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(v);
            transaction.commit();
        } catch (Exception ee) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ee.printStackTrace(); // or handle the exception accordingly
        }
    }

    @Override
    public void deleteVote(Vote v) {

    }
    @Override
    public List<Vote> readVote() {
        Query query = em.createQuery("select v FROM Vote v");
        return query.getResultList();
    }
    @Override
    public void updateVote(Vote e) {

    }
}
