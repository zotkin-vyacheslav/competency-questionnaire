$(document).ready(function () {
    $("#logout_btn_id_admin").click(function () {

        if (confirm('Подтвердите выход')) {
            window.location.href = "/survey/logout";
        }

        return false;
    });
});

function checkNextAndPreviosButtons() {
    $("#next_button_id").on("click", function () {

        $("#" + categories_id[currentArrIndex].toString()).hide();

        if (countOfLoadedPages > currentArrIndex) {
            $("#" + categories_id[++currentArrIndex].toString()).show();
        } else {
            countOfLoadedPages++;
            currentArrIndex++;
            loadPersonalPage();
        }
        checkIndexToHideButtons();
        $(".highcharts-credits").hide();
        //            $(".highcharts-button").hide();
        $(".highcharts-axis-title").hide();
        window.scrollTo(0, 0);
        return false;
    });

    $("#previous_button_id").on("click", function () {

        $("#" + categories_id[currentArrIndex].toString()).hide();

        $("#" + categories_id[--currentArrIndex].toString()).show();

        checkIndexToHideButtons();
        window.scrollTo(0, 0);
        return false;
    });
    $(".highcharts-credits").hide();
    //            $(".highcharts-button").hide();
    $(".highcharts-axis-title").hide();
}

function checkIndexToHideButtons() {
    if (currentArrIndex > 0 && !$("#previous_button_id").is(':visible')) {
        $("#previous_button_id").show();
    } else if (currentArrIndex === 0) {
        $("#previous_button_id").hide();
    }
    if (currentArrIndex === Object.keys(categories_id).length - 1) {
        $("#next_button_id").hide();
    } else if (currentArrIndex < Object.keys(categories_id).length - 1) {
        $("#next_button_id").show();
    }
}

function loadPage() {
    getDataFromServer();

    var category_header_id = "cat" + categories_id[currentArrIndex];

    $(".chart").append("<div id='" + categories_id[currentArrIndex] + "'><div class='category-stat'>" +
        "               <div id='" + category_header_id + "'></div>" +
        "                </div><br></div>");

    $("#" + category_header_id).html(data[0]);

    for (var i = 1; i < data.length; i++) {

        if (data[i][1].length === 0) {
            return;
        }

        var subcategoryId = 'subcategoryId' + categories_id[currentArrIndex] + i;
        $("#" + categories_id[currentArrIndex] + "").append("<div id='" + subcategoryId + "' style=\"min-width: 700px; height: 500px; max-width: 1200px;\"></div>");

        getChart(data[i], subcategoryId);
    }
}

function getDataFromServer() {
    $.ajax({
        url: "list",
        async: false,
        dataType: "json",
        data: {
            categoryId: categories_id[currentArrIndex]
        },
        success: function (d) {
            data = d;
        }
    });
}

function getChart(data, id) {

    var chart = Highcharts.chart('' + id + '', {
        chart: {
            type: 'column'
        },
        title: {
            text: data[0]
        },
        colors: ['#f45b5b', '#8085e9', '#8d4654', '#7798BF', 'rgb(53, 131, 221)'],
        xAxis: {
            categories: data[1],
            crosshair: true
        },
        yAxis: {
            min: 0
        },
        tooltip: {
            formatter: function () {
                var points = this.points;

                var res = '<span style="font-size:10px">' + points[0].key + '</span>' +
                    '<table>';

                var total = points[0].y + points[1].y +points[2].y +points[3].y;

                for (var iter = 0; iter < points.length; iter++) {

                    var i = Math.round(points[iter].y * 100 / total);

                    res = res + '<tr><td style="color:' + points[iter].series.color + ';padding:0">' + points[iter].series.name + ': </td>' +
                        '<td style="padding:0"><b>' + points[iter].y + ' (' + i + '%)' + '</b></td></tr>';
                }

                res = res + '</table>';

                return res;
            },
            shared:
                true,
            useHTML:
                true,
            valueDecimals:
                0,
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth:
                    0
            }
        }
        ,
        series: [{
            name: 'Нет',
            data: data[2]

        }, {
            name: 'Начальные знания',
            data: data[3]

        }, {
            name: 'Базове знания',
            data: data[4]

        }, {
            name: 'Продвинуте знания',
            data: data[5]
        }]
    });
}