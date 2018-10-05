var month = new Array();
month[0] = "Январь";
month[1] = "Февраль";
month[2] = "Маарт";
month[3] = "Апрель";
month[4] = "Май";
month[5] = "Июнь";
month[6] = "Июль";
month[7] = "Август";
month[8] = "Сентябрь";
month[9] = "Октябрь";
month[10] = "Ноябрь";
month[11] = "Декабрь";

var arr = new Array();
var arr2 = new Array();
var d = new Date();
for (var i = 0; i < 12; i++) {
    arr[i] = d.getMonth();
    arr2[i] = d.getYear() + 1900;
    d.setMonth(d.getMonth() + 1);
}

$(function () {
    $("#slider-range").slider({
        range: true,
        min: 0,
        max: 11,
        values: [0, 11],
        slide: function (event, ui) {
            $("#amount").val("С " + month[arr[ui.values[0]]] + " " + arr2[ui.values[0]] + "г по " + month[arr[ui.values[1]]] + " " + arr2[ui.values[1]] + "г");
            var yearDiff = 0;
            if (arr2[ui.values[0]] != arr2[ui.values[1]]){
                yearDiff = 12
            }
            var period = month.indexOf(month[arr[ui.values[1]]]) - month.indexOf(month[arr[ui.values[0]]]) + yearDiff;
           var quan = $('#quantity').text();
            $('#totalPrice').text($('#pricePerMounth').text()*period* quan);

        }
    });
    $("#amount").val("C " + month[arr[$("#slider-range").slider("values", 0)]] + " " + arr2[$("#slider-range").slider("values", 0)] + "г " +
        " по " + month[arr[$("#slider-range").slider("values", 1)]] + " " + arr2[$("#slider-range").slider("values", 1)] + "г");


});



$('#send-order').on('click', function (e){

    $('#min_mounth').prop('value',$("#slider-range").slider("values", 0));
    $('#max_mounth').prop('value',$("#slider-range").slider("values", 1));
    $('#min_year').prop('value',$("#slider-range").slider("values", 0));
    $('#max_year').prop('value',$("#slider-range").slider("values", 1));
    $('#totalPrice').text($('#pricePerMounth').text()*period* quan);
    console.log($('#form').serialize());
    $('#form').submit();

})


