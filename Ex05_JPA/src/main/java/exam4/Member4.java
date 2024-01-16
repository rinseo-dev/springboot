package exam4;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="JpaMember4")
public class Member4 {
	@Id
	private String email;
	private String name;
	@Column(name="create_date")
	private LocalDate createDate;
	
	public Member4() {}

	// 입력할 때 값이 있는 생성자를 사용해서 setMethod가 없어도 됐음
	public Member4(String email, String name, LocalDate createDate) {
		this.email = email;
		this.name = name;
		this.createDate = createDate;
	}

	// 다른 클래스에서 값을 가져다 쓰려면 getMethod가 필요함
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	@Override
	public String toString() {
		return "Member4 [email=" + email + ", name=" + name + ", createDate=" + createDate + "]";
	}
	
	// Test03_update에서 사용하려고 추가
	public void changeName(String newName) {
		name = newName;
	}
	
}
