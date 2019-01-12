adresbestand.controller('searchController', ['$scope','$http','$location','$window', function ($scope, $http, $location, $window) {
    $scope.persons = [];
    $scope.searched = false;

    $scope.searchObject = {
        firstName:'',
        lastName:''
    };

    $scope.searchAll = function () {
        console.log("arrived in searchAll");
        $http({
            method: "GET",
            url: "findAll"
        })
            .success(function (response) {
                $scope.persons = response;
            })
            .error(function (data,status,headers,config) {
                console.log("could not retrieve persons: " + status);
            });
        $scope.searched= true;
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
    };

    $scope.checkAll = function() {
        console.log("arrived in checkAll");
        $scope.selected.persons = angular.copy($scope.persons);
    };
    $scope.print = function() {
        var url = $location.url() + "print";
        console.log("print url " + url);
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

adresbestand.controller('paginationController', ['$scope','$http','$location','$window','_', function ($scope, $http, $location, $window, _) {


    $scope.searchAll = function () {

        $http({
            method:"GET",
            url : 'findAll'
        })
            .success(function (response) {
                $scope.persons = response;
                $scope.searched= true;
            })
            .error(function (data,status,headers,config) {
                $scope.searched= true;
                console.log("could not retrieve persons: " + status);
            });
    };

}]);

