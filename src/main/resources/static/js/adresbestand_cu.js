$(document).ready(function() {

    $("#btn_print").click(function () {

    })


    $("#createUpdateForm").validate();
    jQuery.validator.addMethod("lettersonly", function(value, element) {
        return this.optional(element) || /^[a-z]+$/i.test(value);
    }, "Enkel letters toegelaten");

    $("#lastName").rules("add", {
        required:true,
        minlength:2,
        messages: {
            required: "Gelieve een naam in te geven",
            minlength: "De naam moet uit tenminste 2 karakters bestaan"
        }
    });
    $("#street").rules("add", {
        required:true,
        minlength:3,
        lettersonly: true,
        messages: {
            required: "Gelieve een straat in te geven",
            minlength: "De straat moet uit tenminste 2 karakters bestaan",
            pattern:"Gelieve enkel letters in te geven."
        }
    });
    $("#houseNumber").rules("add", {
        required:true,
        digits:true,
        messages: {
            required: "Gelieve een huisnummer in te geven.",
            digits:"Het huisnummer moet uit nummers bestaan."
        }
    });
    $("#zipCode").rules("add", {
        required:true,
        digits:true,
        minlength:4,
        messages: {
            required: "Gelieve een huisnummer in te geven.",
            digits:"Het huisnummer moet uit nummers bestaan.",
            minlength: "De postcode moet uit 4 cijfers bestaan"
        }
    });
    $("#city").rules("add", {
        required:true,
        minlength:3,
        digits:false,
        messages: {
            required: "Gelieve een gemeente in te geven.",
            minlength: "De gemeente moet uit tenminste 3 karakters bestaan",
            digits:"Gelieve enkel letters in te geven."
        }
    });


    $('#city').autocomplete({
        minLength: 2,
        select: function( event, ui ) {
          $("#zipCode").val(ui.item.zipCode);
          $("#municipalityId").val(ui.item.id);
        },
        source : function(request, response) {
            $.ajax({
                url: getContextPath()+'/getCitiesWithName',
                method:'get',
                data: {term: request.term},
                dataType: 'json',
                success: function(data) {
                    var zipCode = null
                    var id = null
                    response($.map(data, function(item) {
                       return {
                           label:item.city,
                           value:item.city,
                           zipCode:item.zipCode,
                           id: item.id
                       }
                    }));
                }
            });
        }
    });


});

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

