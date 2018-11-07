<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>上传文件</title>
    </head>
    <body>
        <form id="fileForm" action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>名称</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>请选择文件</td>
                    <td>
                        <input type="file" name="upload">
                        <input type="file" name="upload">
                        <input type="file" name="upload2">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="上传">
                    </td>
                </tr>
            </table>
        </form>
    
    </body>
</html>
