package application;

import application.dao.ClimberDao;
import application.dao.GroupDao;
import application.dao.MountainDao;
import application.specifications.Specification;
import application.specifications.climberSpecifications.getByAgeFromClimber;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Application {
    public static void main(String[] args) {



        Climber climber1 = new Climber("Дмитрий", "Санкт Петербург", 27);
        Climber climber2 = new Climber("Анатолий", "Москва", 27);
        Climber climber3 = new Climber("Евгений", "Санкт Петербург", 35);
        Climber climber4 = new Climber("Алия", "Уфа", 30);
        Climber climber5 = new Climber("Гульнара", "Санкт Петербург", 36);
        Climber climber6 = new Climber("Катя", "Санкт Петербург", 25);
        Climber climber7 = new Climber("Альбина ", "Уфа", 30);


        Mountain mountain1 = new Mountain("Джомолунгма", "Китай", 8848);
        Mountain mountain2 = new Mountain("Пик Независимости", "Таджикистан", 6940);
        Mountain mountain3 = new Mountain("Пик Ленина", "Таджикистан", 7132);


        LocalDate startDate1 = LocalDate.of(2022, Month.JANUARY, 20);
        LocalDate startDate2 = LocalDate.of(2022, Month.APRIL, 13);
        LocalDate startDate3 = LocalDate.of(2022, Month.MARCH, 22);
        Group group1 = new Group(mountain1, true, startDate1, 5);

        group1.addClimber(climber1);
        group1.addClimber(climber2);

        Group group2 = new Group(mountain2, true, startDate2, 3);

        group2.addClimber(climber3);
        group2.addClimber(climber4);

        Group group3 = new Group(mountain1, true, startDate3, 10);

        group3.addClimber(climber5);
        group3.addClimber(climber6);
        /**************************************************************************************************************/

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();

        // генерация таблиц средствами hibernate
        ClimberDao climberDao = new ClimberDao(manager);
        MountainDao mountainDao = new MountainDao(manager);
        GroupDao groupDao = new GroupDao(manager);

        manager.getTransaction().begin();

        mountainDao.add(mountain1);
        mountainDao.add(mountain2);
        mountainDao.add(mountain3);

        climberDao.add(climber1);
        climberDao.add(climber2);

        climberDao.add(climber3);
        climberDao.add(climber4);

        climberDao.add(climber5);
        climberDao.add(climber6);

        groupDao.add(group1);
        groupDao.add(group2);
        groupDao.add(group3);

        manager.getTransaction().commit();

        // запросы
        System.out.println(mountainDao.getMountainsByCountry("Таджикистан"));
        System.out.println(climberDao.getClimbersByAge(25, 35));

        Specification specification = new getByAgeFromClimber(25, 35);
        List<Climber> listByAgeFromTo =  climberDao.getBySpecification(specification);
        System.out.println("Запрос listByAgeFromTo = " + listByAgeFromTo);

        System.out.println(groupDao.getGroupsByMountainName("Джомолунгма"));
        System.out.println(groupDao.getGroupsByOpen(true));

    }
}
