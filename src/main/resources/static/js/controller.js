adresbestand.controller('searchController', ['$scope','$http','$location','$window', function ($scope, $http, $location, $window) {
    $scope.persons = [];
    $scope.searched = false;

    $scope.searchObject = {
        firstName:'',
        lastName:''
    };

    $scope.searchAll = function () {
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

adresbestand.controller('personController', ['$scope','$http','$location','$window', function ($scope, $http, $location, $window) {
    $scope.person = {};
    $scope.created = false;
    $scope.municipalities = [];

    var foundPerson = {};

    $scope.isPersonEmpty = function () {
        return isEmpty(foundPerson);
    }

    function isEmpty(obj) {
        for(var key in obj) {
            if(obj.hasOwnProperty(key))
                return false;
        }
        return true;
    }

    $scope.isReadOnly = function() {
        return false;
    }

    $scope.isEdit = function() {
        return !this.isPersonEmpty() && !this.isReadOnly();
    }

    //populate municpalities from db
    $scope.getAllMunicipalities = function() {
        $http({
            method: "GET",
            url: "getAllCities"
        })
            .success(function (response) {
                $scope.municipalities = response;
            })
            .error(function (data,status,headers,config) {
                console.log("could not retrieve cities: " + status);
            });
    };

    // https://www.youtube.com/watch?v=3p0v2_IiePk

    //$scope.

    $scope.createUpdate = function() {
        console.log("arrived in createUpdate");
        $http({
            method:"POST",
            url : 'createUpdate',
            data: $scope.person
        })
            .success(function (response) {
                console.log("Arrived in succes of createUpdate");
                $scope.created= response;
            })
            .error(function (data, status, headers, config) {
                console.log("could not save or update persons: " + status);
                $scope.created= false;
            });
    }

}]);

