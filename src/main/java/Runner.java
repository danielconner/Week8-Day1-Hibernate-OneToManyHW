import db.DBHelper;
import models.Author;
import models.Book;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        Author author1 =  new Author("Stephen King");
        DBHelper.save(author1);

        Book book1 = new Book("Stand by Me", author1);
        DBHelper.save(book1);

        Book book2 = new Book("Carrie", author1);
        DBHelper.save(book2);

        Author author2 = new Author("Mark Billingham");
        DBHelper.save(author2);

        Book book3 = new Book("Scaredy Cat", author2);
        DBHelper.save(book3);

        Book book4 = new Book("Bloodgame", author2);
        DBHelper.save(book4);

        book4.setTitle("Bloodline");
        DBHelper.update(book4);

        DBHelper.delete(book2);

        List<Author> authors = DBHelper.getAll("Author");

        List<Book> books = DBHelper.getAll("Book");

        List<Book> booksByAuthor2 = DBHelper.getBooks(author2.getId());

        List<Object> resultById = DBHelper.getById("Book", 3);

        List<Object> anotherResultById = DBHelper.getById("Author", 2);

    }
}
