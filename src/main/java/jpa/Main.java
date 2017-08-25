package jpa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import jpa.entity.IdCard;

public class Main {
	private static final Logger LOGGER = Logger.getLogger("JPA");

	public static void main(String[] args) throws Exception {
		Main main = new Main();
                
                main.run();
		
	}
        
        public void run() throws Exception {
            LOGGER.log(Level.INFO, "Starting..");
            
            EntityManagerFactory factory = null;
            EntityManager entityManager = null;
            
            
            
            try {
                factory = Persistence.createEntityManagerFactory("PersistenceUnit");
                
                entityManager = factory.createEntityManager();
            } catch (Exception e) {
                
                LOGGER.log(Level.SEVERE, e.getMessage(),e);
                e.printStackTrace();
            }finally{
                if(entityManager != null) entityManager.close();
                
                if(factory != null) entityManager.close();
            }
            
            loadSampleIdCards(entityManager);
            
            LOGGER.log(Level.INFO, "Done.");
        }
        
        private SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
        
        private void loadSampleIdCards(EntityManager entityManager)throws Exception{
            
            EntityTransaction  transaction = entityManager.getTransaction();
            
            transaction.begin();
            
            IdCard c1 = new IdCard();
            c1.setIdNumber("id-001");
            c1.setIssueDate(new Date());
            c1.setValid(Boolean.TRUE);
            
            entityManager.persist(c1);
            
            IdCard c2 = new IdCard();
            c2.setIdNumber("id-001");
            c2.setIssueDate(sdf.parse("01/02/2017"));
            c2.setValid(Boolean.TRUE);
            
            entityManager.persist(c2);
            
            transaction.commit();
            
        }

}
