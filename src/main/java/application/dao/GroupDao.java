package application.dao;

import application.Dao;
import application.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDao implements Dao<Group> {

    private EntityManager manager;

    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Group group) {
        manager.persist(group);
    }

    @Override
    public void update(Group group) {
        manager.merge(group);
    }

    public List<Group> getGroupsByMountainName(String mountainName) {
        TypedQuery<Group> query = manager.createQuery(
                "SELECT g FROM Group g WHERE g.mountain.mountainName = :mountainName", Group.class);
        query.setParameter("mountainName", mountainName);
        List<Group> groupList = (List<Group>) query.getResultList();
        return groupList;
    }

    public List<Group> getGroupsByOpen(Boolean isOpen) {
        TypedQuery<Group> query = manager.createQuery(
                "SELECT g FROM Group g WHERE g.isOpen = :isOpen", Group.class);
        query.setParameter("isOpen", isOpen);
        List<Group> groupList = (List<Group>) query.getResultList();
        return groupList;
    }
}
