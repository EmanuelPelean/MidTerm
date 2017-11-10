import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LibraryFile lib1 = new LibraryFile();
		boolean proceed = true;

		ArrayList<Books> booksArray = lib1.translateDoc();

		//System.out.println("Welcome to the Grand Circus Library Terminal\n");
		System.out.println("   ______                     __   _______                         __    _ __                             \r\n" + 
				"  / ____/________ _____  ____/ /  / ____(_)___________  _______   / /   (_) /_  _________ ________  __    \r\n" + 
				" / / __/ ___/ __ `/ __ \\/ __  /  / /   / / ___/ ___/ / / / ___/  / /   / / __ \\/ ___/ __ `/ ___/ / / /    \r\n" + 
				"/ /_/ / /  / /_/ / / / / /_/ /  / /___/ / /  / /__/ /_/ (__  )  / /___/ / /_/ / /  / /_/ / /  / /_/ /     \r\n" + 
				"\\____/_/   \\__,_/_/ /_/\\__,_/   \\____/_/_/   \\___/\\__,_/____/  /_____/_/_.___/_/   \\__,_/_/   \\__, /      \r\n" + 
				"                                                                                             /____/       ");
		while (proceed) {
			int userSelection = Validator.getInt(scan,
					"\nPlease select from the following options (enter an number):\n "
							+ "\n1. Display the entire list of books: " + "\n2. Search for a book by author: "
							+ "\n3. Search for a book by title: " + "\n4. Return a book: "
							+ "\n5. Add a book to database:  " + "\n6. Exit.1 \n\n",1, 6);

			switch (userSelection) {
			case 1:
				lib1.bookList(booksArray);
				break;
			case 2:
				String userInputAuthor = Validator.getStringLine(scan, "Please enter author name: ");
				Books bookReturnedByAuthor = lib1.searchByAuthor(booksArray, userInputAuthor, scan);
				if (bookReturnedByAuthor != null) {
					String yn = Validator.getStringYN(scan, "Do you want to checkout this book? (y/n): ");
					if (yn.equalsIgnoreCase("y")) {
						booksArray = lib1.checkOutBook(booksArray, bookReturnedByAuthor);
						System.out.println(bookReturnedByAuthor.getBookStatus());
					}
				}
				break;
			case 3:
				String userInputTitle = Validator.getStringLine(scan, "Please enter the book title: ");
				Books bookReturnedByTitle = lib1.searchByTitle(booksArray, userInputTitle, scan);
				if (bookReturnedByTitle != null) {
					String yn = Validator.getStringYN(scan, "Do you want to checkout this book? (y/n): ");
					if (yn.equalsIgnoreCase("y")) {
						booksArray = lib1.checkOutBook(booksArray, bookReturnedByTitle);
						System.out.println(bookReturnedByTitle.getBookStatus());
					}
				}
				break;
			case 4:
				lib1.bookList(booksArray);
				//booksArray = lib1.checkInBook(booksArray, scan);
				booksArray = lib1.setToCheckedIn(booksArray, scan);
				break;
			case 5:
				booksArray = lib1.addBook(booksArray, scan);
				break;

			case 6: 
				lib1.copyToFile(booksArray);
				proceed = false;
				System.out.println("We hope you enjoyed your book!");
				
			default:
				
			}

		}
	}
}
