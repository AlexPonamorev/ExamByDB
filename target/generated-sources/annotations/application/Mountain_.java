package application;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mountain.class)
public abstract class Mountain_ extends application.ID_ {

	public static volatile SingularAttribute<Mountain, String> mountainName;
	public static volatile SingularAttribute<Mountain, Integer> mountainHeight;
	public static volatile SingularAttribute<Mountain, String> mountainCountry;
	public static volatile ListAttribute<Mountain, Group> groupListUp;

	public static final String MOUNTAIN_NAME = "mountainName";
	public static final String MOUNTAIN_HEIGHT = "mountainHeight";
	public static final String MOUNTAIN_COUNTRY = "mountainCountry";
	public static final String GROUP_LIST_UP = "groupListUp";

}

