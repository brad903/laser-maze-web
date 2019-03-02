   $(function () {
        var chatBox = $('.chat_box');
        var messageInput = $('input[name="message"]');
        var sendBtn = $('.send');
        var roomId = $('.content').data('room-id');
        var member = $('.content').data('member');
        var sock = new SockJS("/ws/laser-maze");
        sock.onopen = function () {
            console.log(roomId);
            sock.send(JSON.stringify({roomId: roomId, messageType: 'JOIN', user : member}));
            sock.onmessage = function (e) {
                var content = JSON.parse(e.data);
                console.log(content);
                var code = "";
                content.forEach(function(data, index) {
                    console.log(data.id);
                    console.log(data);
                    code += ('<h1> ' +  data.name  + '</h1>')
                });
                $('#playerList').html(code);
            }
        }

        sendBtn.click(function () {
            var message = messageInput.val();
            sock.send(JSON.stringify({chatRoomId: roomId, type: 'CHAT', message: message, writer: member}));
            messageInput.val('');
        });
    });
