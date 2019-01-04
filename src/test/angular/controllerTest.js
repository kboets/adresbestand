describe('Container file for all controller tests of adresbestand application', function () {

    describe('searchController', function () {

        var ctrl, $scope, q;

        beforeEach(function () {
            module("adresbestand");

            inject(function ($controller, $rootScope, $injector) {
                $scope = $rootScope.$new();
                q = $injector.get('$q');

                ctrl = mockito4js.spy($controller('searchController', {
                    $scope: $scope
                }));
            });
        });

        it('Should have all variables well initialized', function () {
            expect($scope).toBeDefined();
            expect($scope.persons).toEqual[0];
            expect($scope.searched).toBe(false);
        });

        it('Should copy all persons of the persons into  the selected.persons when calling  checkAll', function() {
            $scope.persons = [{
                "firstName": "Kurt",
                "lastName": "Boets"
            },{
                "firstName": "Jorre",
                "lastName": "Boets"
            }];

            expect($scope.selected.persons).toEqual[0];
            $scope.checkAll();
            expect($scope.selected.persons).toEqual[2];

        });

    });
});


