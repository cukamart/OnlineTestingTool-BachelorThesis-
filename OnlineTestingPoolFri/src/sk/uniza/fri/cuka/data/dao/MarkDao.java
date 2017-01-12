package sk.uniza.fri.cuka.data.dao;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.entity.Mark;

@Repository("markDao")
public class MarkDao extends AbstractDao<Mark, String> {

}
