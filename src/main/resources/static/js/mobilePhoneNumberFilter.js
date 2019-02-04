adresbestand.filter('mobile', function () {
    return function (tel) {
        if (!tel) { return ''; }

        var value = tel.toString().trim().replace(/^\+/, '');

        if (value.match(/[^0-9]/)) {
            return tel;
        }

        var country, dealer, number;

        switch (value.length) {

            case 10:
                country = 1;
                dealer = value.slice(0, 4);
                number = value.slice(4);
                break;
            case 13:
                country = value.slice(0, 4);
                dealer = value.slice(4, 7);
                number = value.slice(7);
                break;

            default:
                return tel;
        }

        if (country == 1) {
            country = "";
        }

        number = number.slice(0, 2) + ' ' + number.slice(2,4) + ' '+ number.slice(4);
        return (country + " " + dealer + " " + number).trim();
    };
});

