
public class LibraryApp {

	
	
	public static void main(String[] args) {
		LibraryFile lib1 = new LibraryFile();
		//lib1.addBook("randomBook", "randomAuthor");
		
		//LibraryFile readFile = new LibraryFile();
		
		// formatting is needed.
		//lib1.searchByAuthor("DeAnte");
		//lib1.searchLibrary("DeAnte");
		lib1.bookList();
		lib1.checkOutBook("TreeSurgeon, Tom");
		lib1.bookList();
	}
	
	

}
