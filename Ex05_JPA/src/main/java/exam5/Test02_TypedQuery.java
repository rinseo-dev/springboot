package exam5;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Test02_TypedQuery {

	public static void main(String[] args) {
		// 필수
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			/*
			 * TypedQuery클래스 : sql문을 직접 작성하고자 할 때(영속성에 없는 sql문 작성)
			 *   - 일반 sql문이 아닌 영속성의 객체에 넣을 구문 jpa문법
			 *     select * => 사용할 수 없음(테이블에 별칭을 넣어서 사용해야 함)
			 */
			TypedQuery<Member5> query = em.createQuery("select m from Member5 m order by m.name", Member5.class);
								// em.createQuery(내가 사용하고자 하는 query문, Entity) 형태로 입력
			// 기존 sql문 : select * from Member5 order by name / *를 사용하지않았음
			
			List<Member5> result = query.getResultList(); // 결과를 list형태로 얻어옴
			
			em.getTransaction().commit();
			
			if(result.isEmpty()) {
				System.out.println("테이블이 비어있습니다");
			}else {
				// 람다식 이용
				result.forEach(user->
						System.out.printf("| %s | %s | %tY-%<stm-%<td |\n", 
								user.getEmail(),user.getName(),user.getCreateDate()));
			}
		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		em.close();
		emf.close();
	}
}

