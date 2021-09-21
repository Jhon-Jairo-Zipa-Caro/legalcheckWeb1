var html = document.querySelector('#html');
var pdf = document.querySelector('#pdf');

populateIframe(html, 'https://calidad.tulegalcheck.com/', [ [ 'Authorization',
		'Bearer abcd1234' ] ]);
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
