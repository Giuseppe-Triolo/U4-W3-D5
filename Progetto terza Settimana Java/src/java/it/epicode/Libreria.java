package java.it.epicode;

import java.it.epicode.dao.LoanDAO;
import java.it.epicode.dao.LoanElementDAO;
import java.it.epicode.dao.UserDAO;
import java.it.epicode.utils.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class Libreria {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("loanmanagement");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        try {
            LoanElementDAO loanElementDAO = new LoanElementDAO(em);
            UserDAO userDAO = new UserDAO(em);
            LoanDAO loanDAO = new LoanDAO(em);

            saveLoanElementAndUser(loanElementDAO, userDAO, loanDAO);

            System.out.println("---------------------------------------------------");

            System.out.println("Find and delete by UUID");
            System.out.println();

            UUID uuidElem = UUID.fromString("0441225b-ab0c-4106-bc67-acb7574ce966");

            findAndDeleteByUUID(loanElementDAO, uuidElem);

            System.out.println("---------------------------------------------------");

            System.out.println("Find by Year of Publication");
            System.out.println();

            findLoanElementsByYearPublication(loanElementDAO, 1990);

            System.out.println("---------------------------------------------------");

            System.out.println("Find by Author");
            System.out.println();

            findLoanElementsByAuthor(loanElementDAO, "Omer Thompson DDS");

            System.out.println("---------------------------------------------------");

            System.out.println("Find by Title");
            System.out.println();

            findLoanElementsByTitle(loanElementDAO, "the");

            System.out.println("---------------------------------------------------");

            System.out.println("Find loan by cardNumber");
            System.out.println();

            findLoanByCardNumber(loanDAO, "ac15c67e-bc23-4414-b568-2538be786dbd");

            System.out.println("---------------------------------------------------");

            System.out.println("Find expired loans");
            System.out.println();

            findExpiredLoans(loanDAO);

        } catch (Exception ex) {
            System.err.println("Error type: " + ex);
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void saveLoanElementAndUser(LoanElementDAO loanElementDAO, UserDAO userDAO, LoanDAO loanDAO) {
        System.out.println("Save loanElement and user");
        Faker.fullTable(loanElementDAO, userDAO, loanDAO);
    }

    private static void findAndDeleteByUUID(LoanElementDAO loanElementDAO, UUID uuid) {
        System.out.println(loanElementDAO.findById(uuid));
        loanElementDAO.findByIdAndDelete(uuid);
    }

    private static void findLoanElementsByYearPublication(LoanElementDAO loanElementDAO, int year) {
        Faker.isEmpty(loanElementDAO.findByYearPublication(year)).forEach(System.out::println);
        System.out.println();
    }

    private static void findLoanElementsByAuthor(LoanElementDAO loanElementDAO, String author) {
        Faker.isEmpty(loanElementDAO.findByYearAuthor(author)).forEach(System.out::println);
        System.out.println();
    }

    private static void findLoanElementsByTitle(LoanElementDAO loanElementDAO, String title) {
        Faker.isEmpty(loanElementDAO.findByYearTitle(title)).forEach(System.out::println);
        System.out.println();
    }

    private static void findLoanByCardNumber(LoanDAO loanDAO, String cardNumber) {
        Faker.isEmpty(loanDAO.findLoanByCardNumber(cardNumber)).forEach(System.out::println);
        System.out.println();
    }

    private static void findExpiredLoans(LoanDAO loanDAO) {
        Faker.isEmpty(loanDAO.findExpiredLoan()).forEach(System.out::println);
        System.out.println();
    }
}
