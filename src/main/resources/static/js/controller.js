adresbestand.controller('mainController', ['$scope','_', function ($scope, _) {
    $scope.persons = [];
    $scope.checkedPersons = [];

    $scope.runSearch = function (searchObject) {
        if(_.isEmptyObject(searchObject)){
            $scope.searchAddressForm.$invalid = true;
            return false;
        }
        else{

        }

    }

}]);

