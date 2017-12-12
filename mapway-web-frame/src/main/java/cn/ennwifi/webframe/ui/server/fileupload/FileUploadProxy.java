
package cn.ennwifi.webframe.ui.server.fileupload;

import cn.ennwifi.webframe.ui.shared.module.ClientConfigure;
import cn.ennwifi.webframe.ui.shared.module.DefaultClientConfigure;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * 文件上传代理,解决跨域调用问题
 *
 * @author zhangjianshe
 *
 */
public class FileUploadProxy extends HttpServlet {

    /**
     * 获取客户端配置信息.需要自定义。
     * @return
     */
  public ClientConfigure getClientConfigure()
  {
     return  new DefaultClientConfigure();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {

    ClientConfigure properties = getClientConfigure();
    String query = req.getQueryString();

    String url = properties.getImageUploadProxyUrl();
    if (query != null) {
      url += "?" + query;
    }
    Header header = Header.create();
    header.asFormContentType("multipart/form-data");
    HashMap<String, Object> paras = new HashMap<String, Object>();

    FileItemFactory fileItemFactory = new DiskFileItemFactory();
    ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

    String temppath = req.getServletContext().getRealPath("/") + "temp/" + R.UU16();
    File f = new File(temppath);
    if (!f.exists()) {
      f.mkdirs();
    }

    FileItemIterator it;
    try {
      it = servletFileUpload.getItemIterator(req);
      while (it.hasNext()) {
        FileItemStream next = it.next();
        if (!next.isFormField()) {
          String fielName = temppath + "/" + origionName(next.getName());
          Streams.writeAndClose(new FileOutputStream(fielName), next.openStream());
          paras.put("upfile", new File(fielName));
        }

      }
      Response r = Http.upload(url, paras, header, 30000);
      String content = r.getContent();
      resp.setContentType("text/html");
      System.out.println(content);
      resp.setCharacterEncoding("UTF-8");
      Streams.write(resp.getOutputStream(), Lang.ins(content));
    } catch (FileUploadException e) {
      e.printStackTrace();
    }



  }

  /**
   * Origion name.
   *
   * @param fileName the file name
   * @return the string
   */
  public String origionName(String fileName) {
    if (Strings.isBlank(fileName)) {
      return R.UU16();
    }
    String targetName = "";
    String tfn = fileName.replaceAll("\\\\", "/");
    int index = tfn.lastIndexOf('/');
    if (index >= 0) {
      targetName = tfn.substring(tfn.lastIndexOf('/') + 1);
    } else {
      targetName = tfn;
    }
    return targetName;
  }

}
