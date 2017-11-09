import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LibraryFile lib1 = new LibraryFile();
		boolean proceed = true;

		ArrayList<Books> booksArray = lib1.translateDoc();

		System.out.println("Welcome to the Grand Circus Library Terminal\n");

		while (proceed) {
			System.out.println("\n\n");
			int userSelection = Validator.getInt(scan,
					"Please select from the following options: (Please enter an option number)"
							+ "\n1. Display the entire list of books.  " + "\n2. Search for a book by author."
							+ "\n3. Search for a book by title." + "\n4. Select a book from the list to check out."
							+ "\n5. Return a book.",
					1, 5);

			switch (userSelection) {
			case 1:
				lib1.bookList(booksArray);
				break;
			case 2:
				Books bookReturnedByAuthor = lib1.searchByAuthor(booksArray, "Emanuel");
				if (bookReturnedByAuthor != null) {
					String yn = Validator.getStringYN(scan, "Do you want to checkout this book");
					if (yn.equalsIgnoreCase("y")) {
						booksArray = lib1.checkOutBook(booksArray, bookReturnedByAuthor);
						System.out.println(bookReturnedByAuthor.getBookStatus());
					}
				}
				break;
			case 3:
				Books bookReturnedByTitle = lib1.searchByTitle(booksArray, "driver");
				if (bookReturnedByTitle != null) {
					String yn = Validator.getStringYN(scan, "Do you want to checkout this book");
					if (yn.equalsIgnoreCase("y")) {
						booksArray = lib1.checkOutBook(booksArray, bookReturnedByTitle);
						System.out.println(bookReturnedByTitle.getBookStatus());
					}
				}
			case 4:

				break;
			case 5:
				lib1.addBook("randomBook", "randomAuthor");
				break;

			default:
				proceed = false;
				System.out.println("We hope you enjoyed your book!");
			}

		}
	}
}
