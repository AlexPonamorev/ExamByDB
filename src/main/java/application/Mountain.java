package application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mountains")
public final class Mountain extends ID {
    @Column(nullable = false, length = 30)
    private String mountainName;
    @Column(nullable = false, length = 30)
    private String mountainCountry;
    @Column(nullable = false, length = 4)
    private int mountainHeight;

    @OneToMany(mappedBy = "mountain")
    private List<Group> groupListUp = new ArrayList<>();

    public Mountain(String mountainName, String mountainCountry, int mountainHeight) {
        setMountainName(mountainName);
        setMountainCountry(mountainCountry);
        setMountainHeight(mountainHeight);
    }

    public void setMountainName(String mountainName) {
        if (mountainName == null || mountainName.trim().length() < 4 || mountainName.trim().length() > 30)
            throw new IllegalArgumentException("Название горы не должно быть короче 4 и длиннее 30 символов!");
        this.mountainName = mountainName;
    }

    public void setMountainCountry(String mountainCountry) {
        if (mountainCountry == null || mountainCountry.trim().length() < 4 || mountainName.trim().length() > 30)
            throw new IllegalArgumentException("Страна расположения горы не должна быть короче 4 и длиннее 30 символов!");
        this.mountainCountry = mountainCountry;
    }

    public int getMountainHeight() {
        return mountainHeight;
    }

    public void setMountainHeight(int mountainHeight) {
        if (mountainHeight < 100 || mountainHeight > 8849)
            throw new IllegalArgumentException("Высота горы не может быть меньше 100 и больше 8849 метров!");
        this.mountainHeight = mountainHeight;
    }

    @Override
    public String toString() {
        return "Гора " + mountainName + " (" + mountainCountry + ", " + mountainHeight + " м)";
    }
}
