package java.it.epicode.dao;

import java.it.epicode.Interfaccia.LoanElement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class LoanElementDAO {
    private final EntityManager em;

    public LoanElementDAO(EntityManager em) {
        this.em = em;
    }

    public List<LoanElement> findByYearTitle(String title) {
        TypedQuery<LoanElement> query = em.createNamedQuery("find_by_title", LoanElement.class)
                .setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    public List<LoanElement> findByYearAuthor(String author) {
        TypedQuery<LoanElement> query = em.createNamedQuery("find_by_author", LoanElement.class)
                .setParameter("author", author);
        return query.getResultList();
    }

    public List<LoanElement> findByYearPublication(int yearPublication) {
        TypedQuery<LoanElement> query = em.createNamedQuery("find_by_year", LoanElement.class)
                .setParameter("yearPublication", yearPublication);
        return query.getResultList();
    }

    public void save(LoanElement loanElement) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(loanElement);
        transaction.commit();
        System.out.println("LoanElement " + loanElement.getTitle() + " added successfully!");
    }

    public LoanElement findById(UUID id) {
        return em.find(LoanElement.class, id);
    }

    public void findByIdAndDelete(UUID id) {
        LoanElement found = this.findById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("LoanElement " + found.getTitle() + " deleted successfully!");
        } else {
            System.out.println("LoanElement with UUID: " + id + " not found");
        }
    }
}
