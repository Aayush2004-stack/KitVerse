/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package kitverse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kitverse.utilities.ImageUtil;

/**
 *
 * @author ACER
 */

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part image = request.getPart("image");
        if(image == null || image.getSize()==0 || image.getSubmittedFileName().isEmpty()){
            request.setAttribute("upload", "failure");
            return;
        }
        
        String filePath = ImageUtil.uploadImage(request, image, "photos");
        if(filePath!=null){
            request.setAttribute("upload", "success");
            request.setAttribute("filePath", filePath);
        }else{
            request.setAttribute("upload", "failure");
        }
        
    }

}
