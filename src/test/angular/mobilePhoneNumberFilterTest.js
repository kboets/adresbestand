describe('mobilePhoneNumberFilter', function () {

    var filter;
    beforeEach(function () {
        module("adresbestand");

        inject(function ($filter) {
            filter = $filter;
        });
    });

    it('Should return correct format for mobile number without country code', function () {
        var phoneNumber = "0497935159";
        expect(filter).toBeDefined();
        expect(filter).not.toBeNull();
        expect(filter('mobile')(phoneNumber)).toBe("0497 93 51 59");
    });

    it('Should return correct format for mobile number with country code', function () {
        var phoneNumber = "0032497935159";
        expect(filter).toBeDefined();
        expect(filter).not.toBeNull();
        expect(filter('mobile')(phoneNumber)).toBe("0032 497 93 51 59");
    });
});

