package java.it.epicode.Entities;

import java.it.epicode.Interfaccia.LoanElement;

import javax.persistence.*;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends LoanElement {

    @Enumerated(EnumType.STRING)
    @Column(name = "periodicity")
    private Periodicity periodicity;

    public Magazine() {
    }

    public Magazine(String title, int yearPublication, int numberPage, Periodicity periodicity) {
        super(title, yearPublication, numberPage);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                ", title='" + title + '\'' +
                ", yearPublication=" + yearPublication +
                ", numberPage=" + numberPage +
                '}';
    }
}
