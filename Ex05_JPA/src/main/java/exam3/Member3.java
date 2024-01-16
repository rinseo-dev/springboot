package exam3;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="JpaMember3") 
public class Member3 {
	@Id
	// 사용할 시퀀스 생성
	@SequenceGenerator(
		name="mySequence01", // 시퀀스의 고유한 이름
		sequenceName="JpaMember3_seq", // 실제 DB에 만들어지는 sql 시퀀스의 이름
		initialValue=1, // 초기값
		allocationSize=1 // 1씩 증가
	)
	@GeneratedValue(generator="mySequence01") // 사용할 시퀀스 이름 넣어줌
	private Long id;
	
	@Access(AccessType.FIELD) // 필드를 통해서 데이터에 접근(기본값)
	private String username;
	
	@Access(AccessType.PROPERTY) // get/set메소드를 통해 데이터 접근함. getter/setter필요
	private String password;
	
	@Transient // 영속 대상에서 제외(DB에 없으므로 제외)
	private String addr;
	transient private String addr2; // 앞에 명시해줘도 됨
	
	public Member3() {}
	
	public Member3(String username, String password) {
		this.username = username;
		this.password = password;
	}



	// password만 getter/setter 만들어줌
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
