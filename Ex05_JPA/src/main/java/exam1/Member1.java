package exam1;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // 필수작성 - Bean파일 하나, Service용 하나 두개를 생성하는 경우가 많음
// @Data // lombok 같이 사용해서 기본생성자 생성 - 지금은 lombok없다는..나는 넣음
public class Member1 { // Bean파일
	@Id // 필수 : primary key
	@GeneratedValue // sequence를 사용해서 값을 자동으로 1개씩 증가
	private Long id;
	/*
	  pk인 id값에는 null이 아닌 값을 가져야 하며, 중복되지 않아야 함.
	  자동 증가 값인 경우도 있어서 숫자형 데이터타입이느 int나 Long이 많이 사용됨.
	  id엔 null값이 들어오면 안되는데 int형은 가능해서 Long을 사용해서 null을 막음
	 */
	private String username;
	
	/*
	  @Column 에서 사용하는 속성 - 쉼표로 구분해서 적어줌
	  - name : 컬럼이름 지정(생략시 변수명과 동일하게 매핑)
	  - unique : unique제약조건 추가(기본값 false) - insert에 사용??
	  - nullable : null상태 허용 여부(기본값 false)
	  - insertable : insert할 때 해당 컬럼을 포함할 것인지 결정(기본값 true)
	  - updatable : update 할 때 해당 컬럼을 포함할 것인지 결정(기본값 true)
	  - length : 문자열 타입의 컬럼 길이 지정(기본값:255)
	  - columnDefinition : 컬럼에 대한 ddl문을 직접 기술할 수 있음
	  	ex) @Column(columnDefinition="verchar2(200)" default'Y')
	 */
	@Column(name="create_date")
	private LocalDate createDate;
	/*
	   LocalDate
	   Date보다 LocalDate를 사용함
	   private LocalDateTime로 시간까지 표시도 가능함
	   
	   예전 사용법
	   @Temporal(TemporalType.TIMESTAMP)
	   private LocalDate createDate;
	 */

	// 생성자 2개 만듦
	public Member1() {} // 비어있는 생성자는 필수

	public Member1(String username, LocalDate createDate) {
		this.username = username;
		this.createDate = createDate;
	}
	
	
	
	
}
