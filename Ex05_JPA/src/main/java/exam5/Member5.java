package exam5;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="JpaMember5")
public class Member5 {
	@Id
	private String email;
	private String name;
	@Column(name="create_date")
	private LocalDate createDate;
	
	public Member5() {}

	public Member5(String email, String name, LocalDate createDate) {
		this.email = email;
		this.name = name;
		this.createDate = createDate;
	}

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
		return "Member5 [email=" + email + ", name=" + name + ", createDate=" + createDate + "]";
	}
	
}
