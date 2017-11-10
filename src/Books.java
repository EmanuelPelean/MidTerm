
public class Books {
	private int bookID;
	private String title;
	private String author;
	private String bookStatus;
	private int dueDate;
	
	public Books() {
		
	}
	public Books(int bookID, String author, String title, String bookStatus, int dueDate) {
		// TODO Auto-generated constructor stub
		this.bookID = bookID;
		this.author = author;
		this.title = title;
		this.bookStatus = bookStatus;
		this.dueDate = dueDate;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(int dueDate) {
		this.dueDate = dueDate;
	}
	
	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	
	@Override
	public String toString() {
		return String.format("%-8s %-25s %-40s %-20s %-15s\n", bookID + ".", author, title, bookStatus, dueDate);
		
		
	}

}
