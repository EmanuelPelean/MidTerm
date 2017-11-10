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
			System.out.println("\n");
			int userSelection = Validator.getInt(scan,
					"Please select from the following options (enter an number):\n "
							+ "\n1. Display the entire list of books.\n " + "\n2. Search for a book by author.\n"
							+ "\n3. Search for a book by title.\n " + "\n4. Retun a book: \n"
							+ "\n5. Add a book to database:\n " + "\n6. Exit \n\n",1, 6);

			switch (userSelection) {
			case 1:
				lib1.bookList(booksArray);
				break;
			case 2:
				Books bookReturnedByAuthor = lib1.searchByAuthor(booksArray, "Emanuel", scan);
				if (bookReturnedByAuthor != null) {
					String yn = Validator.getStringYN(scan, "Do you want to checkout this book");
					if (yn.equalsIgnoreCase("y")) {
						booksArray = lib1.checkOutBook(booksArray, bookReturnedByAuthor);
						System.out.println(bookReturnedByAuthor.getBookStatus());
					}
				}
				break;
			case 3:
				Books bookReturnedByTitle = lib1.searchByTitle(booksArray, "driver", scan);
				if (bookReturnedByTitle != null) {
					String yn = Validator.getStringYN(scan, "Do you want to checkout this book");
					if (yn.equalsIgnoreCase("y")) {
						booksArray = lib1.checkOutBook(booksArray, bookReturnedByTitle);
						System.out.println(bookReturnedByTitle.getBookStatus());
					}
				}
			case 4:
				lib1.bookList(booksArray);
				//booksArray = lib1.checkInBook(booksArray, scan);
				booksArray = lib1.setToCheckedIn(booksArray, scan);
				break;
			case 5:
				booksArray = lib1.addBook(booksArray, scan);
				break;

			case 6: 
				proceed = false;
				System.out.println("We hope you enjoyed your book!");
				
			default:
				
			}

		}
	}
}
