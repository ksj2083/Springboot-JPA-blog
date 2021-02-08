package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; //섬머노트 라이브러리 <html> 테그가 섞여서 디자인된다 그래서 큰 용량 필요
		
	private int count; //조회수
	
	//foreign key
	@ManyToOne // Many = Board, User = One
	@JoinColumn(name="userId")
	private User user;  
	
	//글 상세보기시 바로 댓글을 뿌려주기 위해서 LAZY 안쓰고 EAGER 전략 사용
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy -> 연관관계의 주인이 아니다(난 FK가 아님) DB에 컬럼을 만들지 않는다
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
