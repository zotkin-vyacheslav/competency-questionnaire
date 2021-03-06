var currentArrIndex = 0;
var countOfLoadedPages = 0;
var categories_id = [[${categoriesId}]];

$(document).ready(function () {

    loadPage(categories_id[currentArrIndex]);
    checkIndexToHideButtons();

    $("#next_button_id").on("click", function () {

        $("#" + categories_id[currentArrIndex].toString()).hide();

        if (countOfLoadedPages > currentArrIndex) {
            $("#" + categories_id[++currentArrIndex].toString()).show();
        } else {
            countOfLoadedPages++;
            loadPage(categories_id[++currentArrIndex]);
        }
        checkIndexToHideButtons();
    });
    $("#previous_button_id").on("click", function () {

        $("#" + categories_id[currentArrIndex].toString()).hide();

        $("#" + categories_id[--currentArrIndex].toString()).show();

        checkIndexToHideButtons();
    });
    $("#finish_button_id").on("click", function () {
        $("#survey-page").hide();
        $("#main-page").append("<p>Данные сохранены. Спасибо!</p><a href='/' class='btn btn-link'>Ответить заново</a>");

        createJSON();
    });
});

function checkIndexToHideButtons() {
    if (currentArrIndex > 0 && !$("#previous_button_id").is(':visible')) {
        $("#previous_button_id").show();
    } else if (currentArrIndex === 0) {
        $("#previous_button_id").hide();
    }
    if (currentArrIndex === Object.keys(categories_id).length - 1) {
        $("#next_button_id").hide();
        $("#finish_button_id").show();
    } else if (currentArrIndex < Object.keys(categories_id).length - 1) {
        $("#next_button_id").show();
        $("#finish_button_id").hide();
    }
}

function loadPage(k) {

    $.getJSON("rest/cat/" + k.toString(), function (result) {

        var category_title = result["categoryName"];

        var subCategories = result["subCategories"];

        var cat_id = "table" + result["categoryId"] + k.toString();

        $("#table").after("<div id='" + result["categoryId"] + "'><div class='category'>" +
            "<div id='" + cat_id + "'></div>" +
            "</div></div>");

        $("#" + cat_id).html(category_title);

        $(".category").after("<div>" +
            "<img class='survey-image' src='" + result["image"] + "' title alt style=' margin-top: 4%;\n" +
            "    margin-left: auto;\n" +
            "    margin-right: auto;\n" +
            "    display: block;\n" +
            "    width: 412px;'/>" +
            "</div>");

        var i;

        for (i = 0; i < subCategories.length; i++) {

            var subcategory_title = result["subCategories"][i]["name"];
            var subcategory_add = result["subCategories"][i]["additional"];

            var options = result["subCategories"][i]["options"];

            var j;

            var table_id = "table" + result["categoryId"] + i.toString();
            var sub_id = "sub" + result["categoryId"] + i.toString();
            var sub_add = "sub_add" + result["categoryId"] + i.toString();

            $("#" + result["categoryId"].toString()).append("<table class='table table-stripped'>" +
                "                            <thead>" +
                "                            <div class='sub'>" +
                "                            <h3 id='" + sub_id + "''></h3>" +
                "                            <div id='" + sub_add + "''></div>" +
                "                            </div>" +
                "                            <tr>" +
                "                                <th class='col-md-4'></th>" +
                "                                <th class='col-md-2'><b>Нет</b></th>" +
                "                                <th class='col-md-2'><b>Начальные знания</b></th>" +
                "                                <th class='col-md-2'><b>Базовые знания</b></th>" +
                "                                <th class='col-md-2'><b>Продвинутые знания</b></th>" +
                "                            </tr>" +
                "                            </div>" +
                "                            <tbody id='" + table_id.toString() + "'>" +
                "                            </tbody>" +
                "                        </table> " +
                "                        <div class='bottom-table' id='footer'><p>" +
                "                            Если в списке выше что-то отсутствует из того, что Вы знаете и использовали, то укажите\n" +
                "                            ниже через запятую:" +
                "                        </p>" +
                "                        <div class=\"form-group\" id=\"form_id\">" +
                "<input class='form-control' id='" + result["subCategories"][i]["subCategoryId"] + "' placeholder='Мой ответ' name='additional'/>" +
                "                        </div></div>");

            $("#" + sub_id).text(subcategory_title);
            $("#" + sub_add).text(subcategory_add);

            for (j = 0; j < options.length; j++) {
                str1 = options[j]["optionName"];

                str2 = "<tr><td class='col-md-4'>" + str1 + "</td>";

                str2 = str2 + "<td class='col-md-2'><div><label class='radio'> <input id='" + options[j]["optionId"] + "' type='radio' value='1' name='radio" + options[j]["optionId"] + "'></div></label></td>";
                str2 = str2 + "<td class='col-md-2'><div><label class='radio'> <input id='" + options[j]["optionId"] + "' type='radio' value='2' name='radio" + options[j]["optionId"] + "'></div></label></td>";
                str2 = str2 + "<td class='col-md-2'><div><label class='radio'> <input id='" + options[j]["optionId"] + "' type='radio' value='3' name='radio" + options[j]["optionId"] + "'></div></label></td>";
                str2 = str2 + "<td class='col-md-2'><div><label class='radio'> <input id='" + options[j]["optionId"] + "' type='radio' value='4' name='radio" + options[j]["optionId"] + "'></div></label></td>";

                str2 = str2 + "</tr>";
                $("#" + table_id.toString()).append(str2);
            }
        }
    });
}

function createJSON() {
    jsonObj = [];
    stringAnswers = [];

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

    $('input[class="form-control"]').each(function () {
        if ($(this).val() !== "") {
            var id = $(this).attr("id");
            var value = $(this).val();

            items = {};
            items ["id"] = id;
            items ["value"] = value;

            stringAnswers.push(items);
        }
    });

    $.ajax({
        url: "rest/getStringAnswers",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'application/json',
        data: JSON.stringify(stringAnswers),
        async: false,
        cache: false,
        processData: false,
    });

    $.ajax({
        url: "rest/getAnswer",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'application/json',
        data: JSON.stringify(jsonObj),
        async: false,
        cache: false,
        processData: false,
    });
}