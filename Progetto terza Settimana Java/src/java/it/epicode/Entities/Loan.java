package java.it.epicode.Entities;

import java.it.epicode.Interfaccia.LoanElement;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "loan_element_id")
    private LoanElement loanElement;

    @Column(nullable = false)
    private LocalDate loanDate;

    private LocalDate expectedReturnDate;

    private LocalDate returnDate;

    public Loan() {
    }

    public Loan(User user, LoanElement loanElement) {
        this.user = user;
        this.loanElement = loanElement;
        this.loanDate = generateRandomLoanDate();
        this.expectedReturnDate = loanDate.plusDays(30);
    }

    private LocalDate generateRandomLoanDate() {
        return LocalDate.now().minusDays((long) (Math.random() * 21 + 20));
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoanElement getLoanElement() {
        return loanElement;
    }

    public void setLoanElement(LoanElement loanElement) {
        this.loanElement = loanElement;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "user=" + (user != null ? user.getName() : null) +
                ", loanElement=" + loanElement +
                ", loanDate=" + loanDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
