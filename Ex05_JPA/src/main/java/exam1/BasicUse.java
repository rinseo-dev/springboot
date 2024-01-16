package exam1;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class BasicUse {

	public static void main(String[] args) {
		// 무조건 필수 -  JpaEx01를 사용한 Persistence를 만들어서 emf에 넣음.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
				// persistence.xml에서 설정한 persistence-unit name="JpaEx01" 이름 사용
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
					// entityManager를 통해서 Transaction을 가져옴
		
		try {
			transaction.begin(); // DB에 commit을 하면 여기서부터, rollback하면 여기서부터 그런거 하는 begin
			
			Member1 user = new Member1("홍길동", LocalDate.now()); // Bean에서 만든 생성자 사용
			
			// .persist는 영속성으로 객체에 데이터를 입력(메모리에 insert해주는 부분)
			entityManager.persist(user);
			
			// 실제 DB에 insert
			transaction.commit();
		
		}catch(Exception e) {
			e.printStackTrace();
			transaction.rollback(); // 오류 났을 경우 begin으로 rollback
		}finally {
			entityManager.close();
		}
		emf.close();
	}

}
