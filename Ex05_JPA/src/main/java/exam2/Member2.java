package exam2;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="JpaMember2") // 만들어질 테이블 이름 지정해줌
public class Member2 {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	
	@Column(name="create_date")
	private LocalDate createDate;

	public Member2() {}

	public Member2(String username, LocalDate createDate) {
		this.username = username;
		this.createDate = createDate;
	}
	
	
	
	
	
}
