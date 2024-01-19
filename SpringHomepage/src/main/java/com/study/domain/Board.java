package com.study.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Board {
	@Id
	@SequenceGenerator(
			name="myBoardSEQ",
			sequenceName="Board_SEQ",
			allocationSize=1
	)
	@GeneratedValue(generator="myBoardSEQ")
	private Long bno;
	@NonNull // lombok import
	private String title;
	@NonNull
	private String content;
	@NonNull
	private String writer;// member name에서 writer에 FK걸어서 사용해야하지만그냥씀
	
	@Column(insertable=false,columnDefinition="NUMBER DEFAULT 0")
	// insert때 count에는 값이 들어가지않게
	// 영속성에서는 안넣고 실제 DB에 들어갈때 입력됨
	private Long count;
	
	// 생성할 때 시간 등록
	@CreatedDate
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	// 수정할 때 시간 등록
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updatteDate;
}
