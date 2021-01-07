package application;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Group.class)
public abstract class Group_ extends application.ID_ {

	public static volatile SingularAttribute<Group, Integer> durationDays;
	public static volatile SingularAttribute<Group, Mountain> mountain;
	public static volatile SingularAttribute<Group, Boolean> isOpen;
	public static volatile ListAttribute<Group, Climber> climberList;
	public static volatile SingularAttribute<Group, LocalDate> startDate;

	public static final String DURATION_DAYS = "durationDays";
	public static final String MOUNTAIN = "mountain";
	public static final String IS_OPEN = "isOpen";
	public static final String CLIMBER_LIST = "climberList";
	public static final String START_DATE = "startDate";

}

