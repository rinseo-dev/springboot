package exam5;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test01_Insert {

	public static void main(String[] args) {
		// 필수
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			Member5 user;
			user = new Member5("test1@test.com","이순신",LocalDate.now());
			em.persist(user);
			user = new Member5("test2@test.com","강감찬",LocalDate.now());
			em.persist(user);
			user = new Member5("test3@test.com","김유신",LocalDate.now());
			em.persist(user);
			user = new Member5("test4@test.com","연개소문",LocalDate.now());
			em.persist(user);
			user = new Member5("test5@test.com","세종대왕",LocalDate.now());
			em.persist(user);
			user = new Member5("test6@test.com","계백",LocalDate.now());
			em.persist(user);
			user = new Member5("test7@test.com","최영",LocalDate.now());
			em.persist(user);
			user = new Member5("test8@test.com","을지문덕",LocalDate.now());
			em.persist(user);
			user = new Member5("test9@test.com","이성계",LocalDate.now());
			em.persist(user);
			
			em.getTransaction().commit();
			System.out.println("단체 가입이 되었습니다");
		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		em.close();
		emf.close();
	}

}
