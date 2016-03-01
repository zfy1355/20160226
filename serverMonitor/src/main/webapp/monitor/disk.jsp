<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内存状况</title>
</head>
<body>
 <c:forEach var="diskInfo"  items ="${diskInfos}">
    <li >
       Disk  Name : ${disnInfo.diskName}<tab/>
       Total Space : ${diskInfo. totalSpace/1024}GB<tab/>
       Free  Space : ${diskInfo.freeSpace/1024 }GB<tab/>
       Used Space: ${diskInfo.usedSpace/1024 }GB
    </li >
</c:forEach >
<li>
	Memory Total Space :${cpu.totalSpace }MB
	Memory Free Space : ${cpu.freeSpace }MB
</li>
</body>
</html>