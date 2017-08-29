$(document).ready(function() {
    console.log("search js set and ready");

    $("searchAddressForm").validate({
        rules : {
            lastName : {
                required : true,
                minlength : 2
            }
        }
    });

});