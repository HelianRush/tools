$(function () {

    $("#toDown").click(function () {
        toDownload();
    })

});

function toDownload() {
    var url = "/uploadCtl/download";

    var filePath = $("#returnPath").val();
    var fileName = $("#returnName").val();

    var form = $("<form id='down'>");
    form.attr("style", "display:none");
    form.attr("target", "");
    form.attr("method", "get");
    form.attr("action", url);

    var input1 = $("<input id='filePath' name='filePath' >");
    input1.attr("typt", "hidden");
    input1.attr("value", filePath);
    form.append(input1);

    var input2 = $("<input id='fileName' name='fileName' >");
    input2.attr("typt", "hidden");
    input2.attr("value", fileName);
    form.append(input2);

    $("body").append(form);
    form.submit();
}