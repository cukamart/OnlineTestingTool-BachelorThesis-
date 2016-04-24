package sk.uniza.fri.cuka.data.dao;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.entity.StudentAnswer;
import sk.uniza.fri.cuka.data.entity.ids.StudentAnswerId;

@Repository("studentAnswerDao")
public class StudentAnswerDao extends AbstractDao<StudentAnswer, StudentAnswerId> {

}
