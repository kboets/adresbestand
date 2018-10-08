adresbestand.controller('mainController', ['$scope','_', function ($scope, _) {
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

        }

    }

}]);

