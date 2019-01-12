describe('Container file for all controller tests of adresbestand application', function () {

    describe('searchController', function () {

        var ctrl, $scope, q, httpBackend;

        beforeEach(function () {
            module("adresbestand");

            inject(function ($controller, $rootScope, $injector, $httpBackend) {
                $scope = $rootScope.$new();
                q = $injector.get('$q');
                httpBackend = $httpBackend;
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

        it('Should return all persons when selecting searchAll', function () {
            httpBackend.expectGET("/findAll").respond({
                allPersons : [{
                    "firstName": "Kurt",
                    "lastName": "Boets"
                },{
                    "firstName": "Jorre",
                    "lastName": "Boets"
                }],
                searched :true
            })
            expect($scope.persons).toEqual[0];
            expect($scope.searched).toBe(false);
            $scope.searchAll();
            expect($scope.persons).toEqual[2];
            expect($scope.searched).toBe(true);
        });

        it('Should return correct person(s) with select criteria when calling searchAddress', function () {
            $scope.searchObject = {
                firstName:'Kurt',
                lastName:'Boets'
            };
            httpBackend.expectPOST("/searchAddress").respond({
                person : [{
                    "firstName": "Kurt",
                    "lastName": "Boets"
                }]
            });

            expect($scope.persons).toEqual[0];
            $scope.searchPersons($scope.searchObject);
            expect($scope.persons).toEqual[1];

        });
    });


});


