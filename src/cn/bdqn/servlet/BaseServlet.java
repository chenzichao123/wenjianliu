package cn.bdqn.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import sun.nio.ch.IOUtil;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload servletContext=new ServletFileUpload();
		servletContext.setHeaderEncoding("UTF-8");
		List<FileItem> fileItems=null;
		try {
			fileItems=servletContext.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(FileItem fileItem:fileItems){
			String name=fileItem.getName();
		    long time=System.currentTimeMillis();
		    String fileName=time+name.substring(name.lastIndexOf("."));
		    System.out.println("开始上传，文件名"+fileName);
		    InputStream in=fileItem.getInputStream();
		    OutputStream out=new FileOutputStream("D://"+fileName);
		    IOUtils.copy(in, out);
		}
	}

}
