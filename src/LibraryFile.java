import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class LibraryFile {

	// Get the main array of Books and print out each line
	public void bookList(ArrayList<Books> arr) {
		System.out.printf("\n%-8s %-25s %-40s %-11s %-15s\n", "Book#", "Author", "Title", "Status",
				"Due Date");
		System.out.println("_____________________________________________________________________________________________________________________________\n");
		for (Books book : arr) {

			System.out.println(book);
		}

	}

	// Transfer everything from the .txt to our main Books array
	public ArrayList<Books> translateDoc() {
		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile();

		// New ArrayList that will store all of our books
		ArrayList<Books> mainDirectory = new ArrayList<Books>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);

			// create a string variable for each line
			String line = reader.readLine();
			// create a new temporary array that will split each line into separate
			// variables and then store them
			String[] lineInput = new String[5];

			while (line != null) {

				// Split each line at the ","
				lineInput = line.split("/");

				// Create new Book object and give it the parameters from the temp array, then
				// add this object to the Books array
				mainDirectory.add(
						new Books(Integer.valueOf(lineInput[0].toString()), lineInput[1], lineInput[2], lineInput[3]));

				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}

		return mainDirectory;

	}

	public Books searchByAuthor(ArrayList<Books> arr, String author, Scanner scnr) {

		// variable used to specify which book the user selects if there are multiple
		// results
		int userChoice = 0;
		// book object variable used to store a copy of the book the user selects
		Books bookSelected = null;

		// temporary array user to store results
		ArrayList<Books> booksFoundArr = new ArrayList<Books>();
		// for each book in the array, check if the book's author equals the author from
		// the user input
		for (Books book : arr) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				booksFoundArr.add(book); // if there match, add this book to a temporary array that will be used to
											// display these results
			}

		} // display the results
		if (booksFoundArr.size() > 0) {
			for (Books books : booksFoundArr) {
				System.out.println(books);
			}

			// select a book from the results
			userChoice = Validator.getInt(scnr, "Please enter the book ID you would like: ");
			for (Books books : booksFoundArr) {
				if (books.getBookID() == userChoice) {
					if (!books.getBookStatus().equalsIgnoreCase("checked out")) {
						bookSelected = books;
					} else {
						bookSelected = null;
						System.out.println("This book is already checked out! Please feel free to check out another book that is currently 'On Shelf'.");
					}
				}

			}

		} else if (booksFoundArr.size() == 0) {
			bookSelected = null;
		}

		// return a book that the user selected
		return bookSelected;

	}

	public Books searchByTitle(ArrayList<Books> arr, String title, Scanner scnr) {

		// variable used to specify which book the user selects if there are multiple
		// results
		int userChoice = 0;
		// book object variable used to store a copy of the book the user selects
		Books bookSelected = null;

		// temporary array user to store results
		ArrayList<Books> booksFoundArr = new ArrayList<Books>();
		// for each book in the array, check if the book's author equals the author from
		// the user input
		for (Books book : arr) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				booksFoundArr.add(book); // if there match, add this book to a temporary array that will be used to
											// display these results
			}

		} // display the results
		if (booksFoundArr.size() > 0) {
			for (Books books : booksFoundArr) {
				System.out.println(books);
			}

			// select a book from the results
			userChoice = Validator.getInt(scnr, "Please enter the book ID you would like: ");
			for (Books books : booksFoundArr) {
				if (books.getBookID() == userChoice) {
					if (!books.getBookStatus().equalsIgnoreCase("checked out")) {
						bookSelected = books;
					} else {
						bookSelected = null;
						System.out.println("This book is already checked out! Please feel free to check out another book that is currently 'On Shelf'.");
					}
				}
			}

		} else if (booksFoundArr.size() == 0) {
			bookSelected = null;
		}

		// return a book that the user selected
		return bookSelected;

	}

	public ArrayList<Books> checkOutBook(ArrayList<Books> arr, Books book) {
		// check out book, show status and if it is on shelf,
		// set due date to 2 weeks from current date

		book.setBookStatus("Checked Out");
		int noOfDays = 14;

		Calendar c = Calendar.getInstance();
		Date currentDate = Calendar.getInstance().getTime();
		c.setTime(currentDate);

		c.add(Calendar.DAY_OF_YEAR, noOfDays);
		Date date = c.getTime();
		book.setDueDate(date);

		// arr.remove(book);

		return arr;

	}

	public ArrayList<Books> addBook(ArrayList<Books> arr, Scanner scnr) {

		String titleBook = Validator.getStringLine(scnr, "Enter the book title: ");
		String authorBook = Validator.getStringLine(scnr, "Enter book aurthor: ");
		String bookStatus = "Checked in";
		int bookID = 0;

		for (Books book : arr) {

			if (book.getBookID() > bookID) {
				bookID = book.getBookID();
			}

		}
		bookID += 1;

		arr.add(new Books(bookID, authorBook, titleBook, bookStatus));

		return arr;

	}
	
	
	public ArrayList<Books> setToCheckedIn(ArrayList<Books> arr, Scanner scnr) {
		int userIDValue = Validator.getInt(scnr, "Please enter the Book ID of the book you wish to return: ", 1,
				Integer.MAX_VALUE);
		boolean bookFound = false;
		for (Books book : arr) {

			if (book.getBookID() == userIDValue) {
				book.setBookStatus("On Shelf");
				bookFound = true;
				Date currentDate = Calendar.getInstance().getTime();

				book.setDueDate(currentDate);
			}
		}

		if (bookFound == false) {
			System.out.println("Book ID you have entered doesn't match our database, please try again: ");
		}

		return arr;

	}

	// finish this
	public void copyToFile(ArrayList<Books> arr) {

		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile();

		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(file, false));
			for (Books book : arr) {
				out.println(book.toFileString());
			}
			System.out.println("Database updated");
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
			e.printStackTrace();
		}
	}
}
