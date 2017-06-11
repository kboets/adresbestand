$(document).ready(function() {
    $('#city').autocomplete({
        minLength: 1,
        select: function( event, ui ) {
          $("#zipCode").val(ui.item.zipCode);
        },
        source : function(request, response) {
            $.ajax({
                url:'getCitiesWithName',
                method:'get',
                data: {term: request.term},
                dataType: 'json',
                success: function(data) {
                    var zipCode = null
                    response($.map(data, function(item) {
                       return {
                           label:item.city,
                           value:item.city,
                           zipCode:item.zipCode
                       }
                    }));
                }
            });
        }
    });


    // $('#btn_save').click(function(){
    //     console.log('inside button');
    // });


});

