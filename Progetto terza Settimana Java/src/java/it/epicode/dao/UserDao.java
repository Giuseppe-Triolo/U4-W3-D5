package java.it.epicode.dao;

import java.it.epicode.Entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class UserDAO {
    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.out.println("User " + user.getName() + " added successfully!");
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error occurred while saving user: " + ex.getMessage());
        }
    }

    public User findById(UUID id) {
        return em.find(User.class, id);
    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("User " + found.getName() + " deleted successfully!");
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Error occurred while deleting user: " + ex.getMessage());
            }
        } else {
            System.out.println("User with id: " + id + " not found");
        }
    }
}
}