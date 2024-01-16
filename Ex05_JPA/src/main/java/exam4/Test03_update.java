package exam4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;

public class Test03_update {

	public static void main(String[] args) {
		// 필수
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			Member4 user = em.find(Member4.class, "test@test.com");
			/*
			 * find()를 하면 영속성 안에 find된 table이 존재하게 됨
			 * 그 테이블에서 update를 해야 값이 바뀌게 되는 것.
			 * find()가 선행되어야 update를 할 수 있음
			 * 
			 * insert, select는 영속성 안에 테이블이 존재하지 않아도 됨
			 */
			if(user==null) {
				System.out.println("존재하지 않습니다");
				return;
			}
			
			/* Member4 user = new Member4();
			 * user.changeName("이나은");
			 * 
			 * Member4()여기 아무 값이 없으므로 null값ㅇ ㅣ되는 것
			 * 영속성 안에 table이 있어야 값이 변경됨
			 */
			user.changeName("이나은"); // java객체를 통해 영속 켄텍스트의 값을 변경
			
			em.getTransaction().commit();
			System.out.println("이름을 변경했습니다");
		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		em.close();
		emf.close();
	}

}
