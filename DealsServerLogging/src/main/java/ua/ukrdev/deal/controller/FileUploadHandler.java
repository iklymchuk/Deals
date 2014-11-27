package ua.ukrdev.deal.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ua.ukrdev.deal.util.Serv;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
public class FileUploadHandler extends HttpServlet {
    private final String UPLOAD_DIRECTORY = System.getProperty("java.io.tmpdir");
    Serv serv = new Serv();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        String newFileName = formatNewNameBasedOnTime(name);
                        String newFileFullName = UPLOAD_DIRECTORY + File.separator + newFileName;
                        item.write(new File(newFileFullName));
                        serv.setUploadedPhotoName(newFileFullName);
                    }
                }

                //File uploaded successfully
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        }else{
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }

     //   request.getRequestDispatcher("/result.jsp").forward(request, response);

    }

    private String formatNewNameBasedOnTime(String currentName) {
        Date dt = new Date();
        out.println(dt.getTime());
        if (currentName.split(Pattern.quote(".")).length>0)
        return (dt.getTime()+"."+currentName.split(Pattern.quote("."))[1]);
        else return (dt.getTime()+"_");
    }

}


