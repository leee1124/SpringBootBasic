package com.example.Sbb.Sbb;

import com.example.Sbb.Sbb.Entity.AnswerEntity;
import com.example.Sbb.Sbb.Entity.QuestionEntity;
import com.example.Sbb.Sbb.Repository.AnswerRepository;
import com.example.Sbb.Sbb.Repository.QuestionRepository;
import com.example.Sbb.Sbb.Service.QuestionService;
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
class SbbApplicationTests {
	void testJPA() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "냉무";
			this.QuestionService.create(subject, content, null);
		}
	}
}
