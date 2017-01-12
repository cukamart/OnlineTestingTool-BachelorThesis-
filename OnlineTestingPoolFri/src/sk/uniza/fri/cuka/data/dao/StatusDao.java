package sk.uniza.fri.cuka.data.dao;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.entity.Status;

@Repository("statusDao")
public class StatusDao extends AbstractDao<Status, Integer> {

}
