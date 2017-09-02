$(document).ready(function() {
    console.log("adresbestand_cu.js");

    $("#createUpdateForm").validate({
        rules: {
            lastName: {
                required:true,
                minlength:2
            },
            street: {
                required:true,
                minlength:3
            },
            houseNumber: {
                required:true,
                digits:true
            },
            city:{
                required:true,
                minlength:2
            },
            zipCode: {
                required:true,
                digits:true
            }
        },
        messages: {
            lastName: {
                required: "Gelieve een naam in te geven",
                minlength: "De naam moet uit tenminste 2 karakters bestaan"
            },
            street: {
                required: "Gelieve een straat in te geven",
                minlength: "De straat moet uit tenminste 2 karakters bestaan"
            },
            houseNumber: {
                required: "Gelieve een huisnummer in te geven",
                digits: "Het huisnummer moet uit nummers bestaan."
            },
            city: {
                required: "Gelieve een gemeente in te geven",
                minlength: "De gemeente moet uit tenminste 2 karakters bestaan"
            },
            zipCode: {
                required: "Gelieve een postnummer in te geven",
                digits: "Het postnummer moet uit nummers bestaan."
            }
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

