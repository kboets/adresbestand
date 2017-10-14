$(document).ready(function () {

    $(".announce").click(function () {
        $("#personId").val($(this).data('id'));
    });

    $("#confirm_modal").click(function () {
        var id= $("#personId").val();
        console.log("confirmed modal : " +id);
        window.location.href = getContextPath()+'/remove/'+id;
    });


});

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}