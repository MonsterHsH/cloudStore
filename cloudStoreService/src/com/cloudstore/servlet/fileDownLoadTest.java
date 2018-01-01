package com.cloudstore.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cloudstore.tools.javaBean.DownLoadFile;


/**
 * Servlet implementation class fileDownLoadTest
 */
@WebServlet("/fileDownLoadTest")
public class fileDownLoadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fileDownLoadTest() {
        super();
    }
		@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
            String savePath = this.getServletContext().getRealPath("/img/upload");
            //上传时生成的临时文件保存目录
            String tempPath = this.getServletContext().getRealPath("/img/temp");
            String message="";
            List<FileItem> list=null;
            try {
				list = DownLoadFile.salveFile(savePath, tempPath, request);
                for (FileItem item : list) {
                    //如果fileitem中封装的是普通输入项的数据
                    if(item.isFormField()){
                        String name = item.getFieldName();
                        name=new String(name.getBytes(),"UTF-8");
                        //解决普通输入项的数据的中文乱码问题
                        String value = item.getString("UTF-8");
                        System.out.println(name+"  "+name);
                        System.out.println(value+"  "+value);
                    }else{
                        //如果fileitem中封装的是上传文件，得到上传的文件名称，
                        String fileName = item.getName();
                        System.out.println(fileName);
                        if(fileName==null||fileName.trim().equals("")){
                            continue;
                        }
                        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                        fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                        //得到上传文件的扩展名
                        String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
                        if("zip".equals(fileExtName)||"rar".equals(fileExtName)||"tar".equals(fileExtName)||"jar".equals(fileExtName)){
                            request.setAttribute("message", "上传文件的类型不符合！！！");
                            request.getRequestDispatcher("/message.jsp").forward(request, response);
                            return;
                        }
                        //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                        System.out.println("上传文件的扩展名为:"+fileExtName);
                        //获取item中的上传文件的输入流
                        InputStream is = item.getInputStream();
                        //得到文件保存的名称
                        fileName =DownLoadFile.mkFileName(fileName);
                        //得到文件保存的路径
                        String savePathStr =DownLoadFile.mkFilePath(savePath, fileName);
                        System.out.println("保存路径为:"+savePathStr);
                        //创建一个文件输出流
                        FileOutputStream fos = new FileOutputStream(savePathStr+File.separator+fileName);
                        //创建一个缓冲区
                        byte buffer[] = new byte[1024];
                        //判断输入流中的数据是否已经读完的标识
                        int length = 0;
                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while((length = is.read(buffer))>0){
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                            fos.write(buffer, 0, length);
                        }
                        //关闭输入流
                        is.close();
                        //关闭输出流
                        fos.close();
                        //删除处理文件上传时生成的临时文件
                        item.delete();
                        message = "文件上传成功";
                    }
                }	
			}catch (FileUploadException e) {
				
				
				
				
				
			}
                
                

//            } catch (FileUploadBase.FileSizeLimitExceededException e) {
//                e.printStackTrace();
//                request.setAttribute("message", "单个文件超出最大值！！！");
//                return;
//            }catch (FileUploadBase.SizeLimitExceededException e) {
//                e.printStackTrace();
//                request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
//                request.getRequestDispatcher("/message.jsp").forward(request, response);
//                return;
//            }catch (FileUploadException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                message = "文件上传失败";
//            }
            request.setAttribute("message",message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }
     
        

}
