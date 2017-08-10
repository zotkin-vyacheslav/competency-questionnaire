var currentIndexId = 0;
var categories_id = [[${categoriesId}]];

$(document).ready(function () {

    getPage(categories_id[0]);

    $("#next_button_id").on("click", function () {
        getPage(getNextId());
    });
    $("#previous_button_id").on("click", function () {
        getPage(getPreviousId());
    });
    $("#finish_button_id").on("click", function () {
        $("#survey-page").hide();
        $("#main-page").append("<p>Данные сохранены. Спасибо!</p>");
        $("#main-page").append('<a role="button" href="/" class="btn btn-link">Ответить заново</a>');

        createJSON();
    });
});

function checkIndex() {
    if (currentIndexId > 0 && !$("#previous_button_id").is(':visible')) {
        $("#previous_button_id").show();
    } else if (currentIndexId === 0) {
        $("#previous_button_id").hide();
    }
    if (currentIndexId === Object.keys(categories_id).length - 1) {
        $("#next_button_id").hide();
        $("#finish_button_id").show();
    } else if (currentIndexId < Object.keys(categories_id).length - 1) {
        $("#next_button_id").show();
        $("#finish_button_id").hide();
    }
}

function getPreviousId() {

    return categories_id[--currentIndexId];
}

function getNextId() {

    return categories_id[++currentIndexId];
}

function createJSON() {
    jsonObj = [];

    $('input[type="radio"]').each(function () {
        if ($(this).prop('checked')) {
            var id = $(this).attr("id");
            var value = $(this).val();

            item = {};
            item ["id"] = id;
            item ["value"] = value;

            jsonObj.push(item);
        }
    });

    console.log(jsonObj);

    $.ajax({
        url: "rest/getAnswer",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'application/json',
        data: JSON.stringify(jsonObj),
        async: false,
        cache: false,
        processData: false,
        success: function (responseJsonObject) {
            // Success Message Handler
            console.log(responseJsonObject);
        }
    });
}

function getPage(i) {
    checkIndex();
    $(".table").hide();
    $(".form-group").hide();
    $.getJSON("rest/cat/" + i.toString(), function (result) {

        var category_title = result["categoryName"];

        $("#category_id").html(category_title);

        var subCategories = result["subCategories"];

        for (i = 0; i < subCategories.length; i++) {

            var subcategory_title = result["subCategories"][i]["name"];


            var options = result["subCategories"][i]["options"];

            var j;

            var table_id = 'table_id' + i.toString() + result["categoryId"];
//                    $(".table").hide();

            $("#table").append("  <table class='table table-stripped' id='" + table_id.toString() + "'>\n" +
                "                            <p id=\"subcategory_id\"></p>\n" +
                "                            <thead>\n" +
                "                            <tr>\n" +
                "                                <th class=\"col-md-4\"></th>\n" +
                "                                <th class=\"col-md-2\"><b>No</b></th>\n" +
                "                                <th class=\"col-md-2\"><b>Begin</b></th>\n" +
                "                                <th class=\"col-md-2\"><b>Basic</b></th>\n" +
                "                                <th class=\"col-md-2\"><b>Advanced</b></th>\n" +
                "                            </tr>\n" +
                "                            </thead>\n" +
                "                        </table>");

            $("#subcategory_id").append(subcategory_title);

            $("#form_id").html("<input class=\"form-control\" placeholder=\"Мой ответ\" name=\"additional\"/>");

            for (j = 0; j < options.length; j++) {
                str1 = options[j]["optionName"];

                str2 = "<tr><td class='col-md-4'>" + str1 + "</td>";

                str2 = str2 + "  <td class='col-md-2'><div></div><label class='radio'> <input id='" + options[j]["optionId"] + "' type='radio' value='no' name='radio" + options[j]["optionId"] + "'></div></label></td>";
                str2 = str2 + "  <td class='col-md-2'><div> <label class='radio'> <input id='" + options[j]["optionId"] + "' type='radio' value='begin' name='radio" + options[j]["optionId"] + "'></div></label></td>";
                str2 = str2 + "  <td class='col-md-2'><div><label class='radio'> <input id='" + options[j]["optionId"] + "' type='radio' value='basic' name='radio" + options[j]["optionId"] + "'></div></label></td>";
                str2 = str2 + "  <td class='col-md-2'> <label class='radio'> <input id='" + options[j]["optionId"] + "' type='radio' value='advanced' name='radio" + options[j]["optionId"] + "'></div></label></td>";

                str2 = str2 + "</tr>";
                $("#" + table_id.toString() + " tr:last").after(str2);
            }
        }
    });
}