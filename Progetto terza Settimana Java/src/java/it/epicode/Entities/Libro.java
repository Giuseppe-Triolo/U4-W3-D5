package java.it.epicode.Entities;

import java.it.epicode.Interfaccia.LoanElement;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
public class Book extends LoanElement {
    @Column(length = 30)
    private String author;
    @Column(length = 30)
    private String type;

    public Book() {
    }

    public Book(String title, int yearPublication, int numberPAge, String author, String type) {
        super(title, yearPublication, numberPAge);
        this.author = author;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", yearPublication=" + yearPublication +
                ", numberPAge=" + numberPAge +
                '}';
    }
}
