adresbestand.controller('mainController', ['$scope','$http','_', function ($scope, $http, _) {
    $scope.persons = [];
    $scope.checkedPersons = [];

    $scope.searchObject = {
        firstName:'',
        lastName:''
    };
    $scope.searchPersons = function (searchObject) {
        if(searchObject.firstName === '' && searchObject.lastName === ''){
            $scope.searchAddressForm.$invalid = true;
            return false;
        }
        else{
            $http({
                method:"POST",
                url : 'searchAddress',
                data: $scope.searchObject
            })
                .success(function (response) {
                    $scope.persons = response.data
                })
                .error(function (data, status, headers, config) {
                    console.log("could not retrieve persons: " + status);
                });

        }

    }

}]);

