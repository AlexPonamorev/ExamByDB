package application.dao;

import application.Climber;
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

public class ClimberDao implements Dao<Climber> {

    private EntityManager manager;

    public ClimberDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Climber climber) {
        manager.persist(climber);
    }

    @Override
    public void update(Climber climber) {
        manager.merge(climber);
    }


    // запрос на получение по возрасту
    public List<Climber> getClimbersByAge(int from, int to) {
        TypedQuery<Climber> query = manager.createQuery(
                "SELECT g FROM Climber g WHERE g.climberAge >= :from AND g.climberAge < :to", Climber.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Climber> climberList = (List<Climber>) query.getResultList();
        return climberList;
    }

    public List<Climber> getBySpecification(Specification<Climber> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery = builder.createQuery(Climber.class); // создан запрос
        // корневой объект
        Root<Climber> root = criteriaQuery.from(Climber.class);
        Predicate condition = specification.getPredicate(root, builder);
        criteriaQuery.where(condition);
        return manager.createQuery(criteriaQuery).getResultList();
    }

}