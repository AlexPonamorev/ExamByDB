package application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Specification<T> {   //  T - дженерик - тип параметра в аргументах метода интерфейса
    Predicate getPredicate(Root<T> root, CriteriaBuilder builder);
}

