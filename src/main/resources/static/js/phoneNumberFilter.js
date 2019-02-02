adresbestand.filter('phoneNumber', function () {
    return function (tel) {
        if (!tel) { return ''; }

        var value = tel.toString().trim().replace(/^\+/, '');

        if (value.match(/[^0-9]/)) {
            return tel;
        }

        var country, zone, number, isBigCity;

        switch (value.length) {

            case 9:
                country = 1;
                var tempZone = value.substr(0,2);
                if(tempZone === "02" || tempZone === "03") {
                    zone = value.slice(0, 2);
                    number = value.slice(2);
                    isBigCity=true;
                } else {
                    zone = value.slice(0, 3);
                    number = value.slice(3);
                    isBigCity=false;
                }
                break;
            case 10: // +1PPP####### -> C (PPP) ###-####
                country = 1;
                zone = value.slice(0, 3);
                number = value.slice(3);
                break;

            case 11: // +CPPP####### -> CCC (PP) ###-####
                country = value[0];
                zone = value.slice(1, 4);
                number = value.slice(4);
                break;

            case 12: // +CCCPP####### -> CCC (PP) ###-####
                country = value.slice(0, 3);
                zone = value.slice(3, 5);
                number = value.slice(5);
                break;

            default:
                return tel;
        }

        if (country == 1) {
            country = "";
        }

        if(isBigCity === true) {
            number = number.slice(0, 3) + ' ' + number.slice(3,5) + ' ' + number.slice(5);
        } else {
            number = number.slice(0, 2) + ' ' + number.slice(2,4) + ' '+ number.slice(4);
        }


        return (country + " " + zone + " " + number).trim();
    };
});

