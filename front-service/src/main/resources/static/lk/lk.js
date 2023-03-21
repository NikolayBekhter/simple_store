angular.module('store').controller('lkController', function ($scope, $http, $localStorage, $rootScope) {
    // использовать для локального подключения
    const contextCartPath = 'http://localhost:5555/core/api/v1/org';
    // использовать для удаленного подключения
    // const contextCartPath = 'http://95.165.90.118:443/core/api/v1/org';

    $scope.addNewOrg = function () {
        // var fd = new FormData();
        // fd.append('data', JSON.stringify($scope.add_new_org));
        // fd.append('file', $scope.file);
        // var config = {
        //     headers: {'Content-Type': undefined},
        //     transformRequest: angular.identity
        // }
        $http.post(contextPath, $scope.add_new_org /*fd, config*/)
            .then(function (response) {
                alert("Успех!");
            });
    };

    $rootScope.loadProducts();
});