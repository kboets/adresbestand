describe('Container file for all filter tests of adresbestand application', function () {

    describe('PhoneNumberFilter', function () {

        var filter;
        beforeEach(function () {
            module("adresbestand");

            inject(function ($filter) {
                filter = $filter;
            });
        });

        it('Should return correct format for phoneNumber with zipcode of 3 numbers', function () {
            var phoneNumber = "013675750";
            expect(filter).toBeDefined();
            expect(filter).not.toBeNull();
            expect(filter('phoneNumber')(phoneNumber)).toBe("013 67 57 50");
        });

        it('Should return correct format for phoneNumber with zipcode of 2 numbers', function () {
            var phoneNumber = "025211333";
            expect(filter).toBeDefined();
            expect(filter).not.toBeNull();
            expect(filter('phoneNumber')(phoneNumber)).toBe("02 521 13 33");
        });



    });

});