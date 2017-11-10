import java.util.Calendar;
import java.util.Date;

public class Books {
	private int bookID;
	private String title;
	private String author;
	private String bookStatus;
	private Date dueDate;
	
	public Books() {
		
	}
	public Books(int bookID, String author, String title, String bookStatus) {
		// TODO Auto-generated constructor stub
		this.bookID = bookID;
		this.author = author;
		this.title = title;
		this.bookStatus = bookStatus;
		Date currentDate = Calendar.getInstance().getTime();
		this.dueDate = currentDate;
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
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date date) {
		this.dueDate = date;
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
		return String.format("%-8s %-25s %-40s %-11s %-15s", bookID + ".", author, title, bookStatus, dueDate);
		
		
	}
	
	public String toFileString() {
		return (bookID + "/" + author + "/" + title + "/" + bookStatus + "/" + dueDate);
	}

}
