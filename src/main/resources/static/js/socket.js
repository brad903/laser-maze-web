var sock;

$(function () {
    sock = new SockJS("/ws/laser-maze");
    sock.onopen = function () {
        sock.send(JSON.stringify({messageType: 'JOIN'}));
        sock.onmessage = function(e) {
            printUsers(e);
        }
    }

   $('#readyBtn').on('click', function(e) {
       sock.send(JSON.stringify({messageType: 'READY'}));
       sock.onmessage = function(e) {
            var countOfReady = printUsers(e);
            if(countOfReady == 2) {
                $('#readyBtn').attr('disabled', true);
                alert("게임 시작!");
            }
       }
   });

    sendBtn.click(function () {
        var message = messageInput.val();
        sock.send(JSON.stringify({type: 'PLAY', }));
        messageInput.val('');
    });
});

function printUsers(e) {
    var content = JSON.parse(e.data);
    var code = "";
    var cnt = 0;
    content.forEach(function(data, index) {
        var readyStatus = '대기';
        if(data.ready) {
            cnt++;
            readyStatus = '준비 완료';
        }
        code += ('<h4> ' +  data.user.name  + '  -  ' + readyStatus + '</h4>')
    });
    $('#playerList').html(code);
    return cnt;
}
