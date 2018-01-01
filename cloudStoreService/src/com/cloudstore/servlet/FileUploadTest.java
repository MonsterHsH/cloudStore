package com.cloudstore.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileUploadTest
 */
@WebServlet(name = "fileUploadTest", urlPatterns = { "/fileUploadTest" })
public class FileUploadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到要下载的文件名
		String fileName = request.getParameter("filename");
		System.out.println(fileName);
//		fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
		//上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
		String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
//		String fileSaveRootPath="D://fileUploadTest";
//		System.out.println(fileSaveRootPath+"//"+fileName);
		//处理文件名
		String realname = fileName.substring(fileName.indexOf("_")+1);
		//通过文件名找出文件的所在目录
		String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
		//得到要下载的文件	
		System.out.println("D:\\fileUploadTest"+File.separator+fileName);
		
//		File file = new File(path+File.separator+fileName);
	
		File file = new File("D:\\fileUploadTest"+File.separator+fileName);
		//如果文件不存在
		if(!file.exists()){
			request.setAttribute("message", "您要下载的资源已被删除！！");
//		    request.getRequestDispatcher("/message.jsp").forward(request, response);
		   response.getWriter().print("资源被删除了！");
			return;
		}
		         
		//设置响应头，控制浏览器下载该文件
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("D:\\fileUploadTest"+File.separator+fileName, "UTF-8"));
		//读取要下载的文件，保存到文件输入流
//		FileInputStream fis = new FileInputStream(path + File.separator + fileName);
		FileInputStream fis = new FileInputStream("D:\\fileUploadTest"+File.separator+fileName);
		
		//创建输出流
		OutputStream fos = response.getOutputStream();
//		//设置缓存区
//		ByteBuffer buffer = ByteBuffer.allocate(1024);
//		//输入通道
//		FileChannel readChannel = fis.getChannel();
//		//输出通道
//		FileChannel writeChannel = ((FileOutputStream)fos).getChannel();
//		while(true){
//			buffer.clear();
//			int len = readChannel.read(buffer);//读入数据
//			if(len < 0){
//				break;//传输结束
//			}
//			buffer.flip();
//			writeChannel.write(buffer);//写入数据
//		}
		
		byte[] buffer=new byte[1024];
		while(fis.read(buffer)>-1){
			fos.write(buffer);
		}
		//关闭输入流
		fis.close();
		//关闭输出流
		fos.close();
	}
	//通过文件名和存储上传文件根目录找出要下载的文件的所在路径
	public String findFileSavePathByFileName(String fileName,String fileSaveRootPath){
		int hashcode = fileName.hashCode();
		int dir1 = hashcode&0xf;
		int dir2 = (hashcode&0xf0)>>4;
			String dir = fileSaveRootPath + "\\" + dir1 + "\\" + dir2;
			File file = new File(dir);
			if(!file.exists()){
				file.mkdirs();
			}	
			return dir;
	}
}
