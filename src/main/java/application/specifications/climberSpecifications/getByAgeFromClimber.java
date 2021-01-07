package application.specifications.climberSpecifications;

import application.Climber_;
import application.specifications.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class getByAgeFromClimber implements Specification {
    private int from;
    private int to;
    public getByAgeFromClimber(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Predicate getPredicate(Root root, CriteriaBuilder builder) {

        Predicate conditionFrom = builder.ge(root.get(Climber_.climberAge), from); // выборка всех значений "ОТ" по полю climberAge св сущности Climber
        Predicate conditionTo = builder.lt(root.get(Climber_.climberAge), to);// выборка всех значений "ДО"
        Predicate condFinal = builder.and(conditionFrom,conditionTo);  // условие ОТ в пересечении с ДО
        return condFinal;
    }
}
