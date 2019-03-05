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

       if(content.messageType === 'RESULT') {
            if(content.messageObject != null) {
                console.log(content);
                executePlay(content.messageObject.commandMessage);
                content.messageObject.laserMovements.forEach(function(data, index) {
                    shoot(data);
                });
            }
       }

       if(content.messageType === 'INFO') {
           alert(content.messageObject.errorMessage);
       }

       if(content.messageType === 'DISCONNECTED') {
           alert("상대편의 접속 불량으로 게임이 종료되었습니다!");
           window.location.replace("/");
       }
   }
});

function executePlay(data) {
    var target = findTarget(data.row, data.col);
    if(data.commandNumber > 8) {
        var angle = getRotationDegrees(target);
        angle = data.commandNumber == 9 ? angle + 90 : angle - 90;
        target.rotate(angle);
    }

    if(data.commandNumber <= 8) {
        var nextPiece = findNextTarget(data.row, data.col, data.commandNumber);
        var originImage = target.attr('src');
        var nextImage = nextPiece.attr('src');

        var temp = getRotationDegrees(target);
        console.log(temp);
        target.attr('src', nextImage);
        nextPiece.attr('src', originImage);
        nextPiece.rotate(temp);
    }
}

function shoot(content) {
    content.forEach(function(data, index) {
        console.log(index);
        setTimeout(function() {
            moveLaserPointer(data);
        }, 500);

        if(content.length - 1 === index) {
            if(!(isOutOfBound(data.row) || isOutOfBound(data.col))) {
                var target = findTarget(data.row, data.col);
                target.attr('src', '/img/pieces/dummy.png');
            }
        }
    });
}

function isOutOfBound(num) {
    return num >= 8 || num < 0;
}

function moveLaserPointer(data) {
    var target = findTarget(data.row, data.col);
    targetTop = Number(target.offset().top) - Number(target.attr('height'));
    targetLeft = Number(target.offset().left) + Number((target.attr('width') / 2));

    var laser = target.closest('.laserPointer');
    laser.css('visibility', 'visible');
    laser.css('top', targetTop);
    laser.css('left', targetLeft);

    setTimeout(function() {
        laser.css('visibility', 'collapse');
    }, 500);
}

function findTarget(row, col) {
    var localRow = $('#chessSquares tr:nth-child(' + (row + 1) + ')');
    var localCol = localRow.children().eq(col);
    return localCol.children();
}

function findNextTarget(row, col, commandNumber) {
    if(commandNumber == 1) return findTarget(row - 1, col);
    if(commandNumber == 2) return findTarget(row - 1, col + 1);
    if(commandNumber == 3) return findTarget(row, col + 1);
    if(commandNumber == 4) return findTarget(row + 1, col + 1);
    if(commandNumber == 5) return findTarget(row + 1, col);
    if(commandNumber == 6) return findTarget(row + 1, col - 1);
    if(commandNumber == 7) return findTarget(row, col - 1);
    if(commandNumber == 8) return findTarget(row - 1, col - 1);
}

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

function getRotationDegrees(obj) {
    var matrix = obj.css("transform");
    if(matrix !== 'none') {
        var values = matrix.split('(')[1].split(')')[0].split(',');
        var a = values[0];
        var b = values[1];
        var angle = Math.round(Math.atan2(b, a) * (180/Math.PI));
    } else { var angle = 0; }
    return (angle < 0) ? angle + 360 : angle;
}