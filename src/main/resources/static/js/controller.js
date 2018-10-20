adresbestand.controller('mainController', ['$scope','$http','$location','$window','_', function ($scope, $http, $location, $window, _) {
    $scope.persons = [];
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
                })
                .error(function (data, status, headers, config) {
                    $scope.searched= true;
                    console.log("could not retrieve persons: " + status);
                });
        }
    };

    $scope.selected = {
        persons:[]
    }

    $scope.checkAll = function() {
        $scope.selected.persons = angular.copy($scope.persons);
    };
    $scope.print = function() {
        var url = $location.absUrl() + "print";
        var config = {
            headers : {
                'Content-Type': 'application/json'
            },
            responseType:'arraybuffer'
        };
        var dataArr = $scope.selected.persons;

        $http.post(url, dataArr, config).
            then(function (response) {
                var file = new Blob([response.data], {type: "application/pdf"});
                var fileURL = URL.createObjectURL(file);
                $window.open(fileURL, '_blank');
            });

    };

}]);
