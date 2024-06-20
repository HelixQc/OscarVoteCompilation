package oscar.awardService.persistence.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.spi.PersistenceUnitInfo;
import oscar.electionServices.persistence.OscarPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import oscar.awardService.model.Award;
import oscar.awardService.persistence.IAwardDAO;

import java.util.List;


public class AwardDAO_JPA implements IAwardDAO {

    EntityManager em = null;

    public AwardDAO_JPA(){
       /* PersistenceUnitInfo pui = new OscarPersistenceUnitInfo();
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(pui,null);
        this.em = emf.createEntityManager();*/
        this.em = EntityManagerProvider.getEntityManager();
    }

    public Award FindAwardById(int award_id){
        return em.find(Award.class, award_id);
    }

    @Override
    public Award findAwardByName(String name) {
        Query query = em.createQuery("SELECT a FROM Award a WHERE a.name = :name", Award.class);
        query.setParameter("name", name);
        List<Award> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public void createAward(Award award) {

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(award);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAward(Award award) {

    }

    @Override
    public Award UpdateAward(Award award) {
        return null;
    }

    @Override
    public List<Award> findAllAward() {
        Query query = em.createQuery("SELECT a FROM Award a");
        return query.getResultList();
    }

}
