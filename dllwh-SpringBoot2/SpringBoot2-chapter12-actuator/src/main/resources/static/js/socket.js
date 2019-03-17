var gc = function() {
	var socket = new SockJS('/websocket');
	var stompClient = Stomp.over(socket);
	
	var pid = GetQueryString("pid");
	stompClient.connect({}, function(frame) {
		stompClient.subscribe('/topic/gc', function(d) {
			var data = JSON.parse(d.body)
			console.log("--------------------");
		});
		console.log(pid);
	stompClient.send("/app/gc", {}, pid);
	});
}

gc();