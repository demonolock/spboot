var app = angular.module("spboot", []);

app.controller('AppCtrl', function ($scope, $http) {
    $http({
        method: 'GET',
        url: 'http://localhost:8090/api/stackoverflow'
    }).then(function (response) {
        $scope.websites = response.data;
    }, function (error) {
        console.log("error = " + error.status);
    });
});