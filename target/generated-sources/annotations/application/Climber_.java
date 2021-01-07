package application;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Climber.class)
public abstract class Climber_ extends application.ID_ {

	public static volatile SingularAttribute<Climber, String> climberAddress;
	public static volatile SingularAttribute<Climber, Integer> climberAge;
	public static volatile ListAttribute<Climber, Group> groupList;
	public static volatile SingularAttribute<Climber, String> climberName;

	public static final String CLIMBER_ADDRESS = "climberAddress";
	public static final String CLIMBER_AGE = "climberAge";
	public static final String GROUP_LIST = "groupList";
	public static final String CLIMBER_NAME = "climberName";

}

