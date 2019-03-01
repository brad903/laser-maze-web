    $(function () {
        var chatBox = $('.chat_box');
        var messageInput = $('input[name="message"]');
        var sendBtn = $('.send');
        var roomId = $('.content').data('room-id');
        var member = $('.content').data('member');

        var sock = new SockJS("/lasermaze");
        var client = Stomp.over(sock);

        client.connect({}, function () {
            client.send('/publish/games/join', {}, JSON.stringify({user: member, roomId : roomId}));
            client.subscribe('/subscribe/games/rooms/' + roomId, function (user) {
                var content = JSON.parse(user.body);
                console.log(content);
                $("#box").append("<h1>" + content.name + "</h1>");
            });
        });

        sendBtn.click(function () {
            var message = messageInput.val();
            client.send('/publish/games/message', {}, JSON.stringify({id: roomId, row: "1", col: "2", commandNumber: "3"}));
            messageInput.val('');
        });
    });
