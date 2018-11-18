var app = angular.module('searchSOF', []);

app.controller('sofCtrl', function($scope, $http) {

    var url="/searchingSOF?title=";

    $scope.hasMoreResults = false;
    $scope.sof_title = "";
    $scope.page = 1;

    $scope.search = function() {
        $http.get(url + $scope.sof_title)
             .then(function(response) {
                    var data = response.data;
                    $scope.items = data.items;

                    $scope.hasMoreResults = data.has_more;
             });
    }

    $scope.moreResults = function() {
        $scope.page++;
        var more = "&page=" + $scope.page;
        $http.get(url + $scope.sof_title + more)
                     .then(function(response) {
                            var data = response.data;
                            data.items.forEach(function(item) {
                                $scope.items.push(item);
                            });
                            console.log($scope.items);
                            $scope.hasMoreResults = data.has_more;
                     });
    }
});