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
 *
 * @author ACER
 */
public class ImageUtil {
    /**
     * to get the original file name
     *
     * @param file part object received for the file
     * @return original name of the file uploaded
     */
    public static String getFileName(Part file) {
        return file.getSubmittedFileName();
    }

    public static String nameWithTimeStamp(String originalName) {
        int lastDotIndex = originalName.lastIndexOf(".");
        String fname = originalName.substring(0, lastDotIndex);
        String extension = originalName.substring(lastDotIndex);
        String withTime = fname + LocalDateTime.now();
        return withTime.replace(':', '-') + extension;
    }

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
