import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LibraryFile {

	public void bookList(ArrayList<Books> arr) {
		System.out.printf("%-1s %-10s %-10s %-10s %-10s\n", "Book #", "Author", "Title", "Status", "Due Date\n");

		for (Books book : arr) {
			
			System.out.println(book);
		}
		
	}
	
	public ArrayList translateDoc() {
		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile();
		ArrayList<Books> mainDirectory = new ArrayList<Books>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);

			String line = reader.readLine();
			String[] lineInput = new String[5];
		

			while (line != null) {

				lineInput = line.split(",");
				mainDirectory.add(new Books (Integer.valueOf(lineInput[0].toString()),lineInput[1],lineInput[2],lineInput[3],Integer.valueOf(lineInput[4].toString())));
				
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}
		
		return mainDirectory;	
		
	}
	
	
	public int searchByAuthor(ArrayList<Books> arr, String author) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<Books> booksFoundArr = new ArrayList<Books>();
		for (Books book : arr) {
			if (book.getAuthor().equals(author)) {
				booksFoundArr.add(book);
			}
			
		}
		if (booksFoundArr.size() > 1) {
			System.out.println("Multiple matches found. Please select a book.");
			for (Books books : booksFoundArr) {
				System.out.println(books);
			}
			
			
		}
		return Validator.getInt(scnr, "Please enter the ID of the book you would like");
	}

	public void searchLibrary(String userInput) {
		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile();

		try {
			FileReader fr = new FileReader(file);

			BufferedReader reader = new BufferedReader(fr);

			String line = reader.readLine();
			String[] lineInput = new String[2];

			while (line != null) {

				HashMap<String, String> hm = new HashMap<String, String>();

				if (line.indexOf(userInput) != -1) {

					lineInput = line.split(",");

					hm.put(lineInput[0], lineInput[1]);
					Set set = hm.entrySet();
					Iterator i = set.iterator();
					while (i.hasNext()) {
						Map.Entry me = (Map.Entry) i.next();
						System.out.println(me.getKey() + ": " + me.getValue());
					}
				}
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}

		// return book name/title

	}

	public ArrayList<Books> checkOutBook(ArrayList<Books> arr, int bookID) {
		// check out book, show status and if it is on shelf,
		// set due date to 2 weeks from current date
		for (Books book : arr) {
			if (book.getBookID() == bookID) {
				System.out.println(book);
				arr.remove(book);
			}
		}
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
