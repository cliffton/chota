$('#chota-form').on('keyup keypress', function(e) {
    var keyCode = e.keyCode || e.which;
    if (keyCode === 13) {
        if($("#longurl").val() != ""){
            $("#chota-submit").click();
            e.preventDefault();
            return false;
        }
        else{
            e.preventDefault();
            return false;
        }

    }
});

var successfunction = function (data) {
    $("#chota-url-ref").text(data.shorturl);
    $("#chota-url-ref").attr("href", data.shorturl);
    $("#chota-url").show();
    $("#chota-copy").show();
}

function copyToClipboard(element) {
    var $temp = $("<input>");
    $("body").append($temp);
    $temp.val($(element).text()).select();
    document.execCommand("copy");
    $temp.remove();
}

$("#chota-copy").click(function (e) {
    copyToClipboard(document.getElementById("chota-url-ref"));
    $("#chota-copy").text("Copied");
})


$("#chota-submit").click(function () {
    // alert("Chota!");
    $("#chota-copy").text("Copy");
    var longurl = $("#longurl").val();

    $.ajax({
        type: "POST",
        url: "/api/link/",
        data: JSON.stringify({ longurl: longurl }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: successfunction,
        failure: function(errMsg) {
            alert(errMsg);
        }
    });
});