package application.dao;

import application.Dao;
import application.Group;
import application.specifications.Specification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public List<Group> getBySpecification(Specification<Group> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = builder.createQuery(Group.class); // создан запрос
        // корневой объект
        Root<Group> root = criteriaQuery.from(Group.class);
        Predicate condition = specification.getPredicate(root, builder);
        criteriaQuery.where(condition);
        return manager.createQuery(criteriaQuery).getResultList();
    }
}
