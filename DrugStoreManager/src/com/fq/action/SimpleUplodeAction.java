package com.fq.action;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.dao.TestDAO;
import com.fq.po.DrugBean;
import com.fq.po.TestBean;
import com.fq.util.BaseAction;
import com.fq.util.StrUtils;
import com.opensymphony.xwork2.ModelDriven;
@Controller("simpleUplodeAction")
@Scope("prototype")
public class SimpleUplodeAction extends BaseAction implements ModelDriven<DrugBean>, RequestAware {
	//上传的文件
	private File photo;
	
	private String photoContentType;
	private String title;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private DrugBean drugBean = new DrugBean();
	//上传文件的名字
	private String photoFileName;
	private String mess;
	
	private String newFileName;
	
	public String execute(){
		//利用程序建立upload文件
		String path=ServletActionContext.getServletContext().getRealPath("/upload");
		System.out.println(path);
		System.out.println(photoContentType);
		File fupload = new File(path);
		if(!fupload.exists()){
			fupload.mkdirs();
		}
		//指定本地文件的名字
		//重命名
		if(photoFileName==null){
			mess="上传文件失败";
			return "success";
		}
		newFileName = StrUtils.getNewFileName()+photoFileName.substring(photoFileName.indexOf("."));
		
		File newFile = new File(fupload,newFileName);
		try {
			FileUtils.copyFile(photo, newFile);
			session.put("photoFileName", photoFileName);
			session.put("newFileName", newFileName);
			 
			//将数据保存到数据库
			/*TestDAO dao = new TestDAO();
			TestBean bean = new TestBean();
			bean.setTitle(title);
			bean.setOldName(photoFileName);
			bean.setNewName(newFileName);
			dao.add(bean);*/
			mess="上传成功";
		} catch (IOException e) {
			mess="上传失敗"; 
			e.printStackTrace();
			
		}
		return "success"; 
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}

	@Override
	public DrugBean getModel() {
		return drugBean;
	}

	
}
