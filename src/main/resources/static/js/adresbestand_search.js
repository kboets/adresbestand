$(document).ready(function() {

    $("searchAddressForm").validate({
        rules : {
            lastName : {
                required : true,
                minlength : 2
            }
        }
    });

});