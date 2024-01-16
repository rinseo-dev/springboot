package exam5;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Test04_ParameterLike {

	public static void main(String[] args) {
		// 필수
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			TypedQuery<Member5> query = em.createQuery( // select
					// *을 사용할 수 없으므로 테이블 별칭 m 선언된걸 사용함
					"select m from Member5 m "
				   +"where m.name like :name "
				   +"order by m.name"
				   ,Member5.class)
				   .setParameter("name", "%신%"); // name이 ""인 사람을 찾는것
			
			List<Member5> result = query.getResultList();
			
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
