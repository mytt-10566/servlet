package com.momo.servlet.xml.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

/**
 * @author: MQG
 * @date: 2018/11/7
 */
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String uri = request.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        if (methodName.equals("uploadUI")) {// 上传页面
            uploadUI(request, response);
        } else if (methodName.equals("upload")) {// 上传
            upload(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void uploadUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/file/upload.jsp").forward(request, response);
    }

    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 普通参数
        String name = request.getParameter("name");
        System.out.println("name：" + name);

        // 文件
        Part part = request.getPart("upload");
        this.uploadFile(part);

        // 所有部件
//		Collection<Part> parts = request.getParts();
//		if(parts != null && parts.size() > 0){
//			for(Part part : parts){
//				this.uploadFile(part);
//			}
//		}

    }

    private void uploadFile(Part part) {
        if (part == null) {
            return;
        }
        // 原文件名称，Servlet3.1提供
        String submittedFileName = part.getSubmittedFileName();
        if (submittedFileName == null || part.getSize() == 0) {
            return;
        }

        try {
            // 获取上传文件信息
            System.out.println("contentType=" + part.getContentType());// MIME类型
            System.out.println("size=" + part.getSize());// 文件大小 字节
            System.out.println("submittedFileName：" + part.getSubmittedFileName());
            System.out.println("name=" + part.getName());

            // 获取文件上传域信息
            Collection<String> headerNames = part.getHeaderNames();
            for (String headName : headerNames) {
                System.out.println(headName + "=" + part.getHeader(headName));
            }

            // 保存至服务器
            String basePath = this.getServletContext().getRealPath("/");
            String path = basePath + "/uploads/file/" + part.getSubmittedFileName();
            part.write(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
