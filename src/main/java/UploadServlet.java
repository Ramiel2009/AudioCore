import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DATA_DIRECTORY = "data";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 200;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200;
	private static final Logger log = Logger.getLogger(UploadServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			return;
		}

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Sets the size threshold beyond which files are written directly to
		// disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java

		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// constructs the folder where uploaded file will be stored
		
		String uploadFolder = "/home/ap/Desktop/" + File.separator
				+ DATA_DIRECTORY;
		log.info("folder for uploading has been set");

		File file = new File(uploadFolder);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);


		try {
			// Parse the request
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				
				String fileName = new File(item.getName()).getName();
				if (!item.isFormField()) {
					String filePath = uploadFolder + File.separator + fileName;
					File uploadedFile = new File(filePath);
					System.out.println(filePath);
					// saves the file to upload directory
					item.write(uploadedFile);
					log.info("trying to run logic");
					new FileUploader().uploadFile(filePath);
				}
				
			}
			// displays done.jsp page after upload finished
			getServletContext().getRequestDispatcher("/done.jsp").forward(
					request, response);
			

		} catch (Exception ex) {
			ex.getMessage();
		}
	}
}