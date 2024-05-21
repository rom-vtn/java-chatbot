package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    
    private static int BOOK_NOT_FOUND = -1;

    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Searches for the given bookId in the `books` ArrayList
     * @param bookId The book ID to look for
     * @return -1 (BOOK_NOT_FOUND) if bookId is null or not found in books; index of book otherwise
     */
    private int getBookIndex(String bookId) {
        if (bookId == null) {
            return BOOK_NOT_FOUND;
        }
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getBookId().equals(bookId)) {
                return i;
            }
        }
        return BOOK_NOT_FOUND;
    }

    /**
     * Checks whether or not the book is contained in the library
     * @param book The book to look for
     * @return Whether the book is in the library
     */
    public boolean contains(Book book) {
        if (book == null) {
            return false;
        }
        return contains(book.getBookId());
    }

    /**
     * Checks whether or not the book is contained in the library
     * @param bookId The book ID to look for
     * @return Whether the book is in the library
     */
    public boolean contains(String bookId) {
        if (bookId == null) {
            return false;
        }
        return getBookIndex(bookId) != BOOK_NOT_FOUND;
    }

    /**
     * Finds a book within the library by ID
     * @param bookId the ID of the book to look for
     * @return the Book object if it's found, null otherwise
     */
    public Book getBook(String bookId) {
        if (bookId == null) {
            return null;
        }
        int index = getBookIndex(bookId);
        if (index == BOOK_NOT_FOUND) {
            return null;
        }
        return books.get(index);
    }

    /**
     * Adds a book to the library
     * @param book The Book object to be added
     * @return whether or not the book was added successfully
     */
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        int index = getBookIndex(book.getBookId());
        if (index != BOOK_NOT_FOUND) {
            return false;
        }
        return books.add(book);
    }

    /**
     * Removes a book from the library
     * @param book The book to be removed
     * @return Whether the book was removed or not
     */
    public boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }
        return removeBook(book.getBookId());
    }
    /**
     * Removes a book from the library
     * @param bookId The book ID to be removed
     * @return Whether the book was removed or not
     */
    public boolean removeBook(String bookId) {
        int index = getBookIndex(bookId);
        if (index == BOOK_NOT_FOUND) {
            return false;
        }
        return removeBookAtIndex(index);
    }
    private boolean removeBookAtIndex(int index) {
        try {
            books.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     * Attempts to take away a book from the library
     * @param book The book to be taken away
     * @return Whether the book was successfully taken away
     */
    public boolean takeBookAway(Book book) {
        if (book == null) {
            return false;
        }
        return takeBookAway(book.getBookId());
    }
    /**
     * Attempts to take away a book from the library
     * @param bookId The ID of the book to be taken away
     * @return Whether the book was successfully taken away
     */
    public boolean takeBookAway(String bookId) {
        if (bookId == null) {
            return false;
        }
        int index = getBookIndex(bookId);
        if (index == BOOK_NOT_FOUND) {
            return false;
        }
        return setBookAvailabilityAtIndex(index, false);
    }

    /**
     * Returns a book to the library
     * @param book The book to be returned
     * @return Whether or not the book was returned
     */
    public boolean returnBook(Book book) {
        if (book == null) {
            return false;
        }
        return returnBook(book.getBookId());
    }

    /**
     * Returns a book to the library
     * @param bookId The ID of the book to be returned
     * @return Whether or not the book was returned
     */
    public boolean returnBook(String bookId) {
        if (bookId == null) {
            return false;
        }
        int index = getBookIndex(bookId);
        if (index == BOOK_NOT_FOUND) {
            return false;
        }
        return setBookAvailabilityAtIndex(index, true);
    }

    private boolean setBookAvailabilityAtIndex(int index, boolean isAvailable) {
        Book book = books.get(index);
        if (isAvailable == book.isAvailable()) {
            // could not change status
            return false;
        }
        // try to change availability
        return book.setBookAvailability(isAvailable);
    }
}
