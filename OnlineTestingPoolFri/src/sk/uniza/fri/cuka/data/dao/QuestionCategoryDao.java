package sk.uniza.fri.cuka.data.dao;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.entity.QuestionCategory;

@Repository("questionCategoryDao")
public class QuestionCategoryDao extends AbstractDao<QuestionCategory, Long> {

}
