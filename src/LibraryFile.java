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
import java.util.Scanner;

public class LibraryFile {

	// Get the main array of Books and print out each line
	public void bookList(ArrayList<Books> arr) {
		System.out.printf("%-1s %-10s %-10s %-10s %-10s\n", "Book #", "Author", "Title", "Status", "Due Date\n");

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
				lineInput = line.split(",");

				// Create new Book object and give it the parameters from the temp array, then
				// add this object to the Books array
				mainDirectory.add(new Books(Integer.valueOf(lineInput[0].toString()), lineInput[1], lineInput[2],
						lineInput[3], Integer.valueOf(lineInput[4].toString())));

				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}

		return mainDirectory;

	}

	public Books searchByAuthor(ArrayList<Books> arr, String author) {
		Scanner scnr = new Scanner(System.in);
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
			if (book.getAuthor().equals(author)) {
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
					bookSelected = books;
				}
			}

		} else if (booksFoundArr.size() == 0) {
			bookSelected = null;
		}

		// return a book that the user selected
		return bookSelected;

	}

	public Books searchByTitle(ArrayList<Books> arr, String title) {
		Scanner scnr = new Scanner(System.in);
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
			if (book.getTitle().equals(title)) {
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
					bookSelected = books;
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

		book.setBookStatus("checked out");

		// arr.remove(book);

		return arr;

	}

	public void addBook(String bookTitle, String bookAuthor) {
		Books b1 = new Books();
		b1.setTitle(bookTitle);
		b1.setAuthor(bookAuthor);
		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile();

		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(file, true));
			System.out.println("Adding to document");
			out.println(b1);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
			e.printStackTrace();
		}
	}
}
