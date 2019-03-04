var sock;

$(function () {
    sock = new SockJS("/ws/laser-maze");
    sock.onopen = function () {
        sock.send(JSON.stringify({messageType: 'JOIN'}));
    }

   $('#readyBtn').on('click', function(e) {
       sock.send(JSON.stringify({messageType: 'READY'}));
   });

   sock.onmessage = function(e) {
       // 레디
       var content = JSON.parse(e.data);
       if(content.messageType == 'READY') {
          var countOfReady = printUsers(content.messageObject);
          if(countOfReady == 2) {
              $('#readyBtn').attr('disabled', true);
              alert("게임 시작!");
          }
       }

       if(content.messageType == 'RESULT') {
            console.log(content);
       }
   }
});

$('#chessSquares tr').find('img').each(function() {
     var direction = $(this).data('direction');
     var operator = Math.floor(((direction - 1) + 1) / 2);
     var angle = 0;
     angle = 90 * operator;
      $(this).rotate(angle);
});

function printUsers(content) {
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

function sendCommand(row, col, commandNumber) {
    console.log(row + " " + col + " " + direction);
    sock.send(JSON.stringify({messageType: 'PLAY', row : row, col : col, commandNumber : commandNumber}));
}
