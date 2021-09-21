<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<!--  <script type="text/javascript" src="resources/script.js" crossorigin="anonymous"></script> -->
<title>legalcheckWeb</title>
</head>
<body>
	  
	<iframe id="legalcheckUrl" src="" style="position:fixed;
			top: 0px; left: 0px;
			bottom: 0px; right: 0px;
			width: 100%; height: 100%;
			border:none; margin: 0;
			padding: 0; overflow:hidden;
			z-index: 999999; "> 
	  </iframe>
	
  
	<script type="text/javascript">
	var html = document.querySelector('#legalcheckUrl');
	
	populateIframe(html, '${url.nombreValor}', [['Authorization', 'Bearer abcd1234']]);
	function populateIframe(iframe, url, headers) {
		console.log(iframe);
		console.log(url);
		console.log(headers);
	  var xhr = new XMLHttpRequest();

	  xhr.open('GET', url);
	  xhr.onreadystatechange = handler;
	  xhr.responseType = 'blob';
	  headers.forEach(function(header) {
	    xhr.setRequestHeader(header[0], header[1]);
	  });
	  xhr.send();

	  function handler() {
	    if (this.readyState === this.DONE) {
	      if (this.status === 200) {
	        // this.response is a Blob, because we set responseType above
	        console.log(this.response);
	        iframe.src = URL.createObjectURL(this.response);
	        console.log("En handler ...");
		    console.log(iframe.src);
	      } else {
	        console.error('XHR failed', this);
	      }
	    }
	  }


	}

	function setPDF(blob) {
	  document.querySelector('#blob').src = URL.createObjectURL(blob);
	}
	</script>

</body>
</html>