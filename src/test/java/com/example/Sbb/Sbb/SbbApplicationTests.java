package com.example.Sbb.Sbb;

import com.example.Sbb.Sbb.Entity.QuestionEntity;
import com.example.Sbb.Sbb.Repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void QuestionEntityTestJPA(){
		QuestionEntity questionEntity1 = new QuestionEntity();
		questionEntity1.setSubject("Sbb가 무엇인가요?");
		questionEntity1.setContent("Sbb에 대해서 알고 싶습니다");
		questionEntity1.setCreateDateTime(LocalDateTime.now());
		this.questionRepository.save(questionEntity1);

		QuestionEntity questionEntity2 = new QuestionEntity();
		questionEntity2.setSubject("스프링부트 모델 질문입니다");
		questionEntity2.setContent("id는 자동으로 생성되나요?");
		questionEntity2.setCreateDateTime(LocalDateTime.now());
		this.questionRepository.save(questionEntity2);
	}

	@Test
	void findAllTestJPA(){
		List<QuestionEntity> all = this.questionRepository.findAll();
		assertEquals(2, all.size()); //assertEquals(기대값, 실제값) 동일한지 조사

		QuestionEntity questionEntity = all.get(0);
		assertEquals("Sbb가 무엇인가요?", questionEntity.getSubject());//sbb가 무엇인가요? 제목이 일치하는지 테스트
	}

	@Test
	void findByIdTestJPA(){
		Optional<QuestionEntity> oq = this.questionRepository.findById(1);
		if(oq.isPresent()){
			QuestionEntity questionEntity = oq.get();
			assertEquals("Sbb가 무엇인가요?", questionEntity.getSubject());
		}
	}

	@Test
	void findBySubjectTestJPA(){
		QuestionEntity questionEntity = this.questionRepository.findBySubject("Sbb가 무엇인가요?");
		assertEquals(3, questionEntity.getId());
	}

	@Test
	void findBySubjectAndContentTestJPA(){
		QuestionEntity questionEntity = this.questionRepository.findBySubjectAndContent("Sbb가 무엇인가요?","Sbb에 대해서 알고 싶습니다");
		assertEquals(3, questionEntity.getId());
	}

	@Test
	void findBySubjectLikeTestJPA(){
		List<QuestionEntity> questionEntityList = this.questionRepository.findBySubjectLike("Sbb%");
		QuestionEntity questionEntity = questionEntityList.get(0);
		assertEquals("Sbb가 무엇인가요?", questionEntity.getSubject());
	}

	@Test
	void editSubjectTestJPA(){
		Optional<QuestionEntity> optionalQuestionEntity = this.questionRepository.findById(3);
		assertTrue(optionalQuestionEntity.isPresent());
		QuestionEntity questionEntity = optionalQuestionEntity.get();
		questionEntity.setSubject("수정된 제목");
		this.questionRepository.save(questionEntity);
	}

	@Test
	void deleteTestJPA(){
		assertEquals(2, this.questionRepository.count());
		Optional<QuestionEntity> optionalQuestionEntity = this.questionRepository.findById(3);
		assertTrue(optionalQuestionEntity.isPresent());
		QuestionEntity questionEntity = optionalQuestionEntity.get();
		this.questionRepository.delete(questionEntity);
		assertEquals(1, this.questionRepository.count());
	}


	@Test
	void contextLoads() {
	}

}
