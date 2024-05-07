package de.htwsaar.sose2024.ase.fourpeopleteam;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testAdder() {
        assertEquals(2, App.add(1, 1));
    }

    @Test
    public void testExceptionThrown() {
        assertThrows(NullPointerException.class, ()->{App.throwError();});
    }

    @Test
    public void bookCreationTest() {
        new Book("Sample title", "Sample author", "111111", "ID", false);
        assertThrows(IllegalArgumentException.class, ()->{new Book(null, "an author", "111111", "ID", false);});
        assertThrows(IllegalArgumentException.class, ()->{new Book("a title", null, "111111", "ID", false);});
        assertThrows(IllegalArgumentException.class, ()->{new Book("a title", "an author", null, "ID", false);});
        assertThrows(IllegalArgumentException.class, ()->{new Book("a title", "an author", "111111", null, false);});
    }

    @Test
    public void bookFieldTest() {
        String title = "a title";
        String author = "an author";
        String isbn = "9758555-1";
        String bookId = "123456";
        boolean isAvailable = false;
        Book b = new Book(title, author, isbn, bookId, isAvailable);
        
        assertEquals(title, b.getTitle());
        assertEquals(author, b.getAuthor());
        assertEquals(isbn, b.getIsbn());
        assertEquals(bookId, b.getBookId());
        assertEquals(isAvailable, b.isAvailable());
    }

    @Test
    public void libraryTest() {
        String title = "a title";
        String author = "an author";
        String isbn = "9758555-1";
        String bookId = "123456";
        boolean isAvailable = true;
        Book b = new Book(title, author, isbn, bookId, isAvailable);

        Library l = new Library();

        assertFalse(l.contains(b));
        assertFalse(l.contains(b.getBookId()));

        assertNull(l.getBook("nonexistent ID"));

        assertTrue(l.addBook(b));
        assertFalse(l.addBook(b));

        assertNull(l.getBook("nonexistent ID"));

        assertTrue(l.contains(b));
        assertTrue(l.contains(bookId));

        assertNotNull(l.getBook(bookId));
        assertTrue(l.getBook(bookId).isAvailable());

        assertTrue(l.takeBookAway(bookId));
        assertFalse(l.takeBookAway(bookId));

        assertFalse(l.getBook(bookId).isAvailable());

        assertTrue(l.returnBook(bookId));
        assertFalse(l.returnBook(bookId));

        assertTrue(l.getBook(bookId).isAvailable());

        assertTrue(l.removeBook(bookId));
        assertFalse(l.removeBook(bookId));

        assertFalse(l.contains(bookId));
    }
}
