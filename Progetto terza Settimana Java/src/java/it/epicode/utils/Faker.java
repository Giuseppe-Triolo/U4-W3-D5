package java.it.epicode.utils;

import java.it.epicode.dao.LoanDAO;
import java.it.epicode.dao.LoanElementDAO;
import java.it.epicode.dao.UserDAO;
import java.it.epicode.Entities.Book;
import java.it.epicode.Entities.Loan;
import java.it.epicode.Entities.Magazine;
import java.it.epicode.Entities.User;
import java.it.epicode.enumOBJ.periodicità;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Faker {
    private static final com.github.javafaker.Faker FAKER = new com.github.javafaker.Faker(Locale.ENGLISH);
    private static final Random RANDOM = new Random();

    public static Supplier<Book> newBook = () -> {
        return new Book(FAKER.book().title(), RANDOM.nextInt(1980, 2024), RANDOM.nextInt(50, 1000), FAKER.book().author(), FAKER.book().author());
    };

    public static Supplier<Magazine> newMagazine = () -> {
        Periodicity[] values = Periodicity.values();
        return new Magazine(FAKER.book().title(), RANDOM.nextInt(1980, 2024), RANDOM.nextInt(15, 40), values[RANDOM.nextInt(values.length)]);
    };

    public static Supplier<User> newUser = () -> {
        LocalDate now = LocalDate.now();
        return new User(FAKER.dragonBall().character(), FAKER.animal().name(), now.minusDays(RANDOM.nextInt(4380, 29200)));
    };

    public static void fullTable(LoanElementDAO loanElementDAO, UserDAO userDAO, LoanDAO loanDAO) {
        for (int i = 0; i < 5; i++) {
            Book book = newBook.get();
            loanElementDAO.save(book);
            Magazine magazine = newMagazine.get();
            loanElementDAO.save(magazine);
            User user = newUser.get();
            userDAO.save(user);
            loanDAO.save(new Loan(user, i % 2 == 0 ? book : magazine));
        }
    }

    public static <T> List<T> isEmpty(List<T> list) {
        if (list.isEmpty()) {
            System.out.println("Is empty");
        }
        return list;
    }
}
