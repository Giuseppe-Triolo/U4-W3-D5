package java.it.epicode.dao;

import java.it.epicode.Entities.Loan;
import java.it.epicode.Interfaccia.LoanElement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class LoanDAO {
    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public List<LoanElement> findLoanByCardNumber(String cardNumber) {
        UUID uuid = UUID.fromString(cardNumber);
        TypedQuery<LoanElement> query = em.createQuery(
                "SELECT le FROM LoanElement le JOIN le.loans lo WHERE lo.user.cardNumber = :uuid AND lo.returDate IS NULL",
                LoanElement.class);
        query.setParameter("uuid", uuid);
        return query.getResultList();
    }

    public List<Loan> findExpiredLoan() {
        return em.createQuery(
                "SELECT lo FROM Loan lo WHERE lo.expectedReturnDate < CURRENT_DATE AND lo.returDate IS NULL",
                Loan.class).getResultList();
    }

    public void save(Loan loan) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(loan);

        transaction.commit();

        System.out.println("LoanElement " + loan.getLoanElement().getTitle() + " added successfully!");
    }

    public Loan findById(UUID id) {
        return em.find(Loan.class, id);
    }

    public void findByIdAndDelete(UUID id) {
        Loan found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();

            System.out.println("LoanElement " + found.getLoanElement().getTitle() + " deleted successfully!");
        } else {
            System.out.println("Loan with id: " + id + " not found");
        }
    }
}
