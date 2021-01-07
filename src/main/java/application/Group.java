package application;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "groups")
public final class Group extends ID {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Mountain mountain;

    @JoinTable(name = "groups_climbers",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "climbers_id"))
    // одна группа - много студентов
    @JoinColumn(nullable = true)
    @OneToMany( fetch = FetchType.EAGER)
    // CascadeType.ALL - доступно каскадное обновление добавление и удаление студентов
    private List<Climber> climberList;


    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private int durationDays;

    public Group(Mountain mountain, boolean isOpen, LocalDate startDate, int duration) {
        this.mountain = mountain;
        this.isOpen = isOpen;
        setStartDate(startDate);
        this.durationDays = duration;
        this.climberList = new ArrayList<>(7);
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Запись не может начаться раньше текущей даты!");
        this.startDate = startDate;
    }

    public boolean checkingTime() {
        LocalDate localDate = LocalDate.now();
        if (localDate.isBefore(this.startDate))
            return true;
        if (localDate.isAfter(this.startDate.plusDays(this.durationDays)))
            return true;
        else {
            System.out.println(" В данный момент набора нет, приходите после " + this.startDate.plusDays(this.durationDays));
            return false;
        }
    }

    public void addClimber(Climber climbers) {
        if (checkingTime()) {
            if (isOpen && climberList.size() < 7) {
                for (Climber climber : climberList) {
                    if (!climber.equals(climbers)) {
                        this.climberList.add(Objects.requireNonNull(climbers));
                        climbers.getClimberGroupsList().add(this);
                    }
                }
            }
        }
    }
    @Override
    public String toString() {
        return " Group {" +
                "mountain= " + mountain +
                ", climbersList= " + climberList +
                ", isOpen= " + isOpen +
                ", startDate= " + startDate +
                ", durationDays= " + durationDays +
                '}';
    }

}
