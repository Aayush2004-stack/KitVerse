/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.utilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.time.LocalDateTime;

/**
 * Utility class for handling image upload operations in a servlet-based web
 * application.
 * <p>
 * This class provides helper methods to extract file names, generate unique
 * file names using timestamps, and upload image files to a server directory.
 * </p>
 *
 * <p>
 * Typical usage involves receiving a {@link jakarta.servlet.http.Part} object
 * from a multipart request and storing it in a specified folder within the
 * deployed application directory.</p>
 *
 * @author ACER
 */
public class ImageUtil {

    /**
     * Retrieves the original file name from the uploaded file part.
     *
     * @param file the {@link jakarta.servlet.http.Part} object representing the
     * uploaded file
     * @return the original file name submitted by the client
     */
    public static String getFileName(Part file) {
        return file.getSubmittedFileName();
    }

    /**
     * Generates a unique file name by appending the current timestamp to the
     * original file name.
     * <p>
     * This helps prevent file name collisions when multiple files with the same
     * name are uploaded.
     * </p>
     *
     * @param originalName the original file name including extension
     * @return a modified file name with timestamp appended for uniqueness
     */
    public static String nameWithTimeStamp(String originalName) {
        int lastDotIndex = originalName.lastIndexOf(".");
        String fname = originalName.substring(0, lastDotIndex);
        String extension = originalName.substring(lastDotIndex);
        String withTime = fname + LocalDateTime.now();
        return withTime.replace(':', '-') + extension;
    }

    /**
     * Uploads an image to the server's deployment directory.
     * <p>
     * The method saves the uploaded file into the specified folder inside the
     * application deployment path. If the folder does not exist, it will be
     * created automatically.
     * </p>
     *
     * @param request the {@link jakarta.servlet.http.HttpServletRequest} object
     * used to get application path
     * @param image the uploaded file part
     * @param uploadFolder the folder name where the image will be stored
     * @return the relative file path of the uploaded image, or {@code null} if
     * upload fails
     */
    public static String uploadImage(HttpServletRequest request, Part image, String uploadFolder) {
        //read the file/image object from the request
        String fileName = getFileName(image);  //get the filename from the uploaded file itself

        //Get path of deployment folder
        String storePath = request.getServletContext().getRealPath("");
        //prepared file path like photos\fileName.jpg w.r.t the deployment folder
        // add timestamp on the file for uniqness
        String fileName_ = nameWithTimeStamp(fileName);
        String filePath = uploadFolder + File.separator + fileName_;
        System.out.println(storePath);
        System.out.println(filePath);

        //Create upload folder inside deployment folder if not present
        File uploadDir = new File(storePath + File.separator + uploadFolder);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        //handle the file upload
        try {
            //upload file to selected path
            image.write(storePath + File.separator + filePath);
            System.out.println("File uploaded");
            return filePath;

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("File not uploaded");
            return null;
        }

    }

}
