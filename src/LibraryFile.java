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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LibraryFile {

	public static void bookList() {
		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile();

		try {
			FileReader fr = new FileReader(file);

			BufferedReader reader = new BufferedReader(fr);

			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();

		} catch (IOException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}
	}

	public void searchByAuthor(String byAuthor) {
		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile();

		try {
			FileReader fr = new FileReader(file);

			BufferedReader reader = new BufferedReader(fr);

			String line = reader.readLine();
			String[] lineInput = new String[2];

			while (line != null) {

				HashMap<String, String> hm = new HashMap<String, String>();

				lineInput = line.split(",");
				if (byAuthor.equalsIgnoreCase(lineInput[1])) {

					hm.put(lineInput[1], lineInput[0]);
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

	public void checkOutBook(String lineToRemove) {
		// check out book, show status and if it is on shelf,
		// set due date to 2 weeks from current date
		Path writeFile = Paths.get("LibraryList");
		File file = writeFile.toFile();
		File inFile = new File("LibraryList");
		if (!inFile.isFile()) {
			System.out.println("Cannot find data base");
			return;
		}

		try {
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line;
			// read from original file and write to the new, unless
			// content matches data to move
			while ((line = br.readLine()) != null) {
				if (!line.trim().equals(lineToRemove)) {
					pw.println(line);
					pw.flush();

				}
			}
			pw.close();
			br.close();
			// delete original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file.");
				return;
			}
			// rename new file to original file name
			if (!tempFile.renameTo(inFile)) {
				System.out.println("Could not rename the file.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
