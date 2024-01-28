package java.it.epicode.Interfaccia;

import java.it.epicode.Entities.Loan;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "loan_element")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category")
@NamedQuery(name = "find_by_year", query = "SELECT l FROM LoanElement l WHERE l.yearPublication=:yearPublication")
@NamedQuery(name = "find_by_title", query = "SELECT l FROM LoanElement l WHERE LOWER(l.title) LIKE LOWER(:title)")
@NamedQuery(name = "find_by_author", query = "SELECT b FROM Book b WHERE b.author=:author")
public abstract class LoanElement {
    @Column(nullable = false)
    protected String title;
    @Column(nullable = false)
    protected int yearPublication;
    protected int numberPAge;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_isbn", nullable = false)
    private UUID codeISBN;

    @OneToMany(mappedBy = "loanElement")
    private Set<Loan> loans;


    protected LoanElement() {
    }

    public LoanElement(String title, int yearPublication, int numberPAge) {
        this.title = title;
        this.yearPublication = yearPublication;
        this.numberPAge = numberPAge;
    }

    public UUID getCodeISBN() {
        return codeISBN;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public int getNumberPAge() {
        return numberPAge;
    }

    public void setNumberPAge(int numberPAge) {
        this.numberPAge = numberPAge;
    }
}
