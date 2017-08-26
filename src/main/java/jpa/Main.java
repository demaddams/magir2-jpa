package jpa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.entity.Geek;
import jpa.entity.IdCard;
import jpa.entity.Person;
import jpa.entity.Phone;

public class Main {
	private static final Logger LOGGER = Logger.getLogger("JPA");
        private SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
        
	public static void main(String[] args) {
		Main main = new Main();
                main.run();
	}
        
        public void run() {
            LOGGER.log(Level.INFO, "Starting..");
            //code
            EntityManagerFactory factory = null;
            EntityManager entityManager = null;            
            try{
                factory = Persistence.createEntityManagerFactory("PersistenceUnit");
                entityManager = factory.createEntityManager();
                loadSampleIdCards(entityManager);
                persistPerson(entityManager);
                persistGeek(entityManager);
                addPhones(entityManager);
            }
            catch(Exception e)
            {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                e.printStackTrace();
            }
            finally
            {
                if(entityManager != null)
                {
                    entityManager.close();
                }
                if(factory != null)
                {
                    factory.close();
                }
            }
            //fin code
            LOGGER.log(Level.INFO, "Done.");
        }
        
        
        
        private void loadSampleIdCards(EntityManager entityManager) throws Exception{
            EntityTransaction transaction = entityManager.getTransaction();
            
            transaction.begin();
            
            IdCard c1 = new IdCard();
            c1.setIdNumber("id-001");
            //recupe de la date du systeme
            c1.setIssueDate(new Date());
            c1.setValid(Boolean.TRUE);
            entityManager.persist(c1);
            
            IdCard c2 = new IdCard();
            c2.setIdNumber("id-002");
            c2.setIssueDate(sdf.parse("01/02/2017") );
            c2.setValid(Boolean.TRUE);
            entityManager.persist(c2);
            
            transaction.commit();
        }
        
        private void persistPerson(EntityManager entityManager){
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Person person = new Person();
                person.setFirstName("Homer");
                person.setLastName("Simpson");
                entityManager.persist(person);
                
                IdCard c1 = new IdCard();
                c1.setIdNumber("4711");
                c1.setIssueDate(new Date());
                person.setIdCard(c1);
                entityManager.persist(c1);
                transaction.commit();
            
            } catch (Exception e) {
                if(transaction.isActive()){
                    transaction.rollback();
                }
            }
        }
        
        private void persistGeek(EntityManager entityManager){
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Geek geek = new Geek();
            geek.setFirstName("Gavin");
            geek.setLastName("Coffee");
            geek.setFavouriteProgrammingLanguage("Java");
            entityManager.persist(geek);
            
            geek = new Geek();
            geek.setFirstName("Thomas");
            geek.setLastName("Micro");
            geek.setFavouriteProgrammingLanguage("C#");
            entityManager.persist(geek);
            
            geek = new Geek();
            geek.setFirstName("Christian");
            geek.setLastName("Cup");
            geek.setFavouriteProgrammingLanguage("Java");
            entityManager.persist(geek);
            
            transaction.commit();
  
        }
        
        private void addPhones(EntityManager entityManager){
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Person> query =  builder.createQuery(Person.class);
            
            Root<Person> personRoot = query.from(Person.class);
            
            query.where(builder.and(
               builder.equal(personRoot.get("firstName"), "Homer"),
               builder.equal(personRoot.get("lastName"), "Simpson")     
            ));
            
            List<Person> resultList = entityManager.createQuery(query).getResultList();
            
            for(Person person : resultList){
                Phone phone = new Phone();
                phone.setNumber("+225 08 040 2 6");
                entityManager.persist(phone);
                person.getPhones().add(phone);
                phone.setPerson(person);
            }
            
            transaction.commit();
            
        }

}
