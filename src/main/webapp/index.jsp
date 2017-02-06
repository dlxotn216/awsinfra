<%--
  Created by IntelliJ IDEA.
  User: crscube
  Date: 2017-01-25
  Time: 오후 5:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <title>Home</title>

    <link rel="stylesheet" href="resources/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="resources/css/fancytree/ui.fancytree.min.css" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css">
    <%--<link rel="stylesheet" href="resources/css/annotator.min.css">--%>
    <link type="text/css" rel="stylesheet" href="resources/css/annotorious/annotorious.css" />

    <script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

    <!-- IE에서 PDFJS관련 발생하는 오류 해결위한 라이브러리 -->
    <script type="text/javascript" src="resources/js/compatibility.js"></script>
    <script type="text/javascript" src="resources/js/pdf.js"></script>
    <script type="text/javascript" src="resources/js/pdf.worker.js"></script>
    <script type="text/javascript" src="resources/js/pdfobject.js"></script>

    <script type="text/javascript" src="resources/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="resources/js/jquery.fancytree-all.js"></script>

    <script type="text/javascript" src="js/home.js"></script>
    <style>
        .fancytree-childcounter{
            height: 15px ! important;
            min-width: 15px ! important;
        }
        .width-100{
            width: 100%;
        }
        .padding-30{
            padding: 30px;
        }
        .app-body{

        }
        .app-body {
            border: 1px solid black;
        }

        .btn-file {
            position: relative;
            overflow: hidden;
        }
        .btn-file input[type=file] {
            position: absolute;
            top: 0;
            right: 0;
            min-width: 100%;
            min-height: 100%;
            font-size: 100px;
            text-align: right;
            filter: alpha(opacity=0);
            opacity: 0;
            outline: none;
            background: white;
            cursor: inherit;
            display: block;
        }
    </style>
</head>
<body>
<div class="app-body padding-30">
    <div class="row" style="height:1000px;">
        <%--<div class="col-md-2">--%>
        <%--<ul id="treeDemo" class="ztree width-100"></ul>--%>
        <%--</div>--%>

        <form class="col-md-2" action="/demos/s3/upload" method="POST" enctype="multipart/form-data">
            <span class="btn btn-default btn-file"> browse
                <input class="form-control" type="file" name="file" hidden>
            </span>
            <input class="btn btn-primary btn-block" type="submit" value="transfer" />
        </form>

        <div class="col-md-2">
            <input class="form-control" name="search" placeholder="Filter..." autocomplete="off">
            <span id="matches"></span>
            <div id="fancyTree">

            </div>
        </div>

        <div id="pdf-viewer" class="col-md-4" style="height:500px">

        </div>


    </div>
</div>


<!--

** 전제 **
pdf를 제공하는 response에서 attachment가 아니라 inline이어야 함

PDF Module 정리
1. <embed src="...">
- 크롬에선 적용이 되나 IE에서 미적용 (IE에선 자체적인 PDF Viewer를 제공하지 않음)

2. <object data="...">
- 크롬에선 적용이 되나 IE에선 미적용 같은 이유

3. <iframe src="local">
- 크롬에선 적용이 되나 IE에서 미적용 같은 이유

4. <iframe src="docs.google.com?url=...>
- 크롬, IE에서 PDF Viewer가 나타나긴 하지만 "미리보기를 지원하지 않음"이라는 문구 출력
google docs의 자체적인 버그로 .doc는 잘 되나 .pdf가 지원되지 않음

5. PDFJS와 PDFObject를 사용
PDFObject는 크롬에선 지원 IE 미지원
PDFJS는 크롬 IE 모두 지원

만약 PDFObject를 지원하지 않는 브라우저인 경우
PDFJS로 Force 하도록 설정하여 해결

	 <div id="page">
	    <canvas id="canvas"></canvas>
	</div>

	<a href="/SpringTest/pdf-down" id="embedURL">Download file</a>

	<embed src="/SpringTest/pdf-down"
			width="500" height="800"
			type="application/pdf">

	<object data="/SpringTest/pdf-down" type="application/pdf">
	    <p>It appears you don't have Adobe Reader or PDF support in this web browser. <a href="lorem.pdf">Click here to download the PDF</a>. Or <a href="http://get.adobe.com/reader/" target="_blank">click here to install Adobe Reader</a>.</p>
	   <embed src="/SpringTest/pdf-downf" type="application/pdf" />
	</object>

	<iframe src="/SpringTest/pdf-down" style="width: 300px; height: 300px;">
	      <p>It appears your web browser doesn't support iframes.</p>
	</iframe>

	<iframe src="http://docs.google.com/gview?key=AIzaSyDr6LPane2tqL1pdmf7KX9Ajfa7j5VE8rw&url=http://localhost:8080/SpringTest/pdf-down&embedded=true">
	</iframe>

	<embed src="http://docs.google.com/gview?key=AIzaSyDr6LPane2tqL1pdmf7KX9Ajfa7j5VE8rw&url=http://localhost:8080/SpringTest/pdf-down&embedded=true">
	</embed>

	<iframe src="http://docs.google.com/viewer?key=AIzaSyDr6LPane2tqL1pdmf7KX9Ajfa7j5VE8rw&url=http://localhost:8080/SpringTest/pdf-down&embedded=true">
	</iframe>

	<embed src="http://docs.google.com/viewer?key=AIzaSyDr6LPane2tqL1pdmf7KX9Ajfa7j5VE8rw&url=http://localhost:8080/SpringTest/pdf-down&embedded=true">
	</embed>
  -->
</body>
</html>
