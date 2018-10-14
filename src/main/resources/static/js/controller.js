adresbestand.controller('mainController', ['$scope','$http','_', function ($scope, $http, _) {
    $scope.persons = [];
    $scope.checkedPersons = [];
    $scope.searched = false;

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
                    $scope.persons = response;
                    $scope.searched= true;
                    console.log($scope.persons);
                })
                .error(function (data, status, headers, config) {
                    $scope.searched= true;
                    console.log("could not retrieve persons: " + status);
                });

        }

    }

}]);

