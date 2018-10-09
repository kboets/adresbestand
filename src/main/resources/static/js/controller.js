adresbestand.controller('mainController', ['$scope','$http','_', function ($scope, $http, _) {
    $scope.persons = [];
    $scope.checkedPersons = [];

    $scope.searchObject = {
        firstName:'',
        lastName:''
    };
    $scope.runSearch = function (searchObject) {
        if(searchObject.firstName === '' && searchObject.lastName === ''){
            $scope.searchAddressForm.$invalid = true;
            return false;
        }
        else{
            $http.post().then()
        }

    }

}]);

