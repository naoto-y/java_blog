<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<title>フォームデータの取得サンプル</title>
</head>
<body>
<p>ファイル出力テスト</p>
<form action="./servlet/outputFile" method="get">
	<p><textarea name="item1"></textarea></p>
	<p><input type="submit" value="作成"></p>
</form>
<p><% out.println(System.getProperty("os.name")); %></p>
</body>
</html>
