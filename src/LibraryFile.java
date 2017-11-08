import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LibraryFile {
	
	public static void bookList() {
		//to show entire list of books formated nicely
	}
	
	public  void searchByAuthor() {
		//return book name/title
	}
	
	public  void searchByTitle() {
		//return book name/title
	}
	
	public  void checkOutBook() {
		// check out book, show status and if it is on shelf, 
		//set due date to 2 weeks from current date
	}
	
	public  void addBook(String bookTitle, String bookAuthor) {
		Books b1 = new Books();
		b1.setTitle(bookTitle);
		b1.setAuthor(bookAuthor);
		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile(); 
		
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(file,true));
			System.out.println("Adding to document");
			out.println(b1);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
			e.printStackTrace();
		}
	}
}

