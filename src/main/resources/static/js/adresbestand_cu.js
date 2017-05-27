$(document).ready(function() {
    $('#city').autocomplete({
        minLength: 1,
        source: function (request, response) {
            $.getJSON('getCitiesWithName', request, function(result) {
                response($.map(result, function(item) {
                    return {
                        // following property gets displayed in drop down
                        label: item.city,
                        // following property gets entered in the textbox
                        value: item.city
                    }
                }));
            });
        }
    });

    $('#btn_save').click(function(){
        console.log('inside button');
    });


});

