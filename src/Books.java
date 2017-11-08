
public class Books {
	private String title;
	private String author;
	public enum status {onShelf, checkOut, overDue}
	private int dueDate;
	
	
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
	
	@Override
	public String toString() {
		return title + "," + author;
	}

}
