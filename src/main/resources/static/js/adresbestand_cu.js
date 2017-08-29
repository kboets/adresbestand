$(document).ready(function() {
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

    $("createUpdate").validate();

});

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

