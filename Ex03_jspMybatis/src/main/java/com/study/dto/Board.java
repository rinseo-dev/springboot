package com.study.dto;

import lombok.Data;
/*
 @Getter getMethod()
 @Setter setMethod()
 @RequiredArgsConstructor
 @ToString
 @EqualsAndHashCode
 --------------------------
 @Data : 여기까지 총 5개 annotation을 합침
 
 @Data는 생성자 1개만 생성됨
 	- @NonNull이 붙어있는 필드만 매개변수로 한 생성자
 	- @NonNull이 하나도 없을 때는 매개변수가 없는 생성자
 */
//@NoArgsConstructor // 필드가 없는 생성자
//@AllArgsConstructor // 모든 필드가 있는 생성자

//@NoArgsConstructor
@Data
public class Board {
	/*
	 * @NonNull (import)lombok
	 * 	-> 필드에 지정해놓은 곳에는 null값을 가질 수 없음
	 * 	   null값이 들어오면 오류(Exception)가 발생함
	 */
	private int no;
	private String title;
	private String writer;
	private String content;
}