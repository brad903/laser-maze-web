var sock;

$(function () {
    var chatBox = $('.chat_box');
    var messageInput = $('input[name="message"]');
    var sendBtn = $('.send');
    var roomId = $('.content').data('room-id');
    var member = $('.content').data('member');
    sock = new SockJS("/ws/laser-maze");
    sock.onopen = function () {
        console.log(roomId);
        sock.send(JSON.stringify({roomId: roomId, messageType: 'JOIN', user : member}));
        sock.onmessage = function(e) {
            printUsers(e);
        }

    }

   $('#readyBtn').on('click', function(e) {
       console.log('ready');
       sock.send(JSON.stringify({roomId: roomId, messageType: 'READY', user : member}));
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
        sock.send(JSON.stringify({chatRoomId: roomId, type: 'CHAT', message: message, writer: member}));
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
