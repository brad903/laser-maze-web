$('.square').on('click', expandMenu);
function expandMenu(e) {
    if ($('.play').css('visibility') == 'hidden'){
        $('.play').css('visibility', 'visible');
    } else{
        $('.play').css('visibility', 'hidden');
    }
}

var time = 120;
function timerOn() {
    timer = leadingZero(parseInt(Number(time) / 60)) + ' : ' + leadingZero(Number(time) % 60);
    $('#timer').html('<h1>' + timer + '</h1>');
    time -= 1;
}

function leadingZero(num) {
    return num < 10 ? ('0' + num) : num;
}

var col;
var row_;
$(document).ready(function() {
    setInterval(timerOn, 1000);

   $("#chessSquares td").click(function() {
       col = parseInt( $(this).index());
       row = parseInt( $(this).parent().index() );
   });

   $(".play td img").click(function() {
       direction = $(this).data('value');
       sendCommand(row, col, direction);
   });

});