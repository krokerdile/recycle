package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.dto.question.QuestionTempCreateUseCaseRequest;
import com.zzaug.review.domain.model.question.QuestionTemp;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class QuestionTempConverter {

    public QuestionTemp from (QuestionTempCreateUseCaseRequest source){
        return QuestionTemp.builder()
                .t_id(source.getT_id())
                .content(source.getContent())
                .author(source.getAuthor())
                .author_id(Long.valueOf(source.getAuthor_id()))
                .created_at(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
                .build();
    }
}