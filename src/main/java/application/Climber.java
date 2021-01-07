package application;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "climbers")
public final class Climber extends ID {
    @Column(nullable = false, length = 30)
    private String climberName;
    @Column(nullable = false, length = 30)
    private String climberAddress;
    @Column(nullable = false, length = 3)
    private int climberAge;


    //много альпинистов - в разные группы // связаны по полю "climbersList"

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "climberList"/*,cascade = CascadeType.ALL*/)
    private List<Group> groupList;

    public Climber(String climberName, String climberAddress, int climberAge) {
        setClimberName(climberName);
        setClimberAddress(climberAddress);
        setClimberAge(climberAge);
        groupList = new ArrayList<>(7);
    }

    public void setClimberName(String climberName) {
        if (climberName == null || climberName.trim().length() < 3 || climberName.trim().length() > 30)
            throw new IllegalArgumentException("Имя альпиниста не должно быть короче 3 или длиннее 30 символов!");
        this.climberName = climberName;
    }

    public void setClimberAddress(String climberAddress) {
        if (climberAddress == null || climberAddress.trim().length() < 3 || climberAddress.trim().length() > 50)
            throw new IllegalArgumentException("Адрес проживания альпиниста не должен быть короче 3 или длиннее 50 символов!");
        this.climberAddress = climberAddress;
    }


    public void setClimberAge(int climberAge) {
        if (climberAge < 18 || climberAge > 70)
            throw new IllegalArgumentException("Возраст альпиниста не должен быть меньше 18 и больше 70 лет!");
        this.climberAge = climberAge;
    }

    public List<Group> getClimberGroupsList() {
        return this.groupList;
    }


    @Override
    public String toString() {
        return "Climber{" +
                "climberName='" + climberName + '\'' +
                ", climberAge=" + climberAge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Climber)) return false;
        Climber climber = (Climber) o;
        return climberAge == climber.climberAge && climberName.equals(climber.climberName) && climberAddress.equals(climber.climberAddress) && groupList.equals(climber.groupList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(climberName, climberAddress, climberAge, groupList);
    }
}