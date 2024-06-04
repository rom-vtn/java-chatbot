package de.htwsaar.sose2024.ase.fourpeopleteam;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private String bookId;

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public String getBookId() {
        return bookId;
    }

    public boolean setBookAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
        return true;
    }

    public Book(String title, String author, String isbn, String bookId, boolean isAvailable) throws IllegalArgumentException {
        this.title = checkForNull("title", title);
        this.author = checkForNull("author", author);
        this.bookId = checkForNull("bookId", bookId);
        this.isbn = checkForNull("isbn", isbn);
        this.isAvailable = isAvailable;
    }

    private String checkForNull(String fieldName, String toCheck) throws IllegalArgumentException {
        if (toCheck == null) {
            if (fieldName != null) {
                fieldName = "";
            }
            throw new IllegalArgumentException("Got a null string for field " + fieldName);
        }
        return toCheck;
    }

    @Override
    public String toString() {
        return "Book{"
            + "title=" + getTitle()
            +",author=" + getAuthor()
            +",isbn=" + getIsbn()
            +",bookId" + getBookId()
            +",isAvailable" + isAvailable()
            +"}";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Book book = (Book) other;
        return
            getAuthor().equals(book.getAuthor()) &&
            getBookId().equals(book.getBookId()) &&
            getIsbn().equals(book.getIsbn()) &&
            getTitle().equals(book.getTitle()) &&
            isAvailable() == book.isAvailable;
    }
}