angular.module('store').controller('lkController', function ($scope, $http, $localStorage, $rootScope) {
    // использовать для локального подключения
    // const contextPath = 'http://localhost:5555/core/api/v1';
    // использовать для удаленного подключения
    const contextPath = 'http://95.165.90.118:443/core/api/v1';

    $scope.loadHistory = function () {
        $http.get(contextPath + '/history')
            .then(function (response) {
                $scope.historyList = response.data;
            });
    };

    $scope.saveProduct = function () {
        $http.post(contextPath + '/products', $scope.save_product)
            .then(function (response) {
                alert("Успех!");
                $scope.save_product.title = null;
                $scope.save_product.description = null;
                $scope.save_product.organizationTitle = null;
                $scope.save_product.price = null;
                $scope.save_product.quantity = null;
            });
    };

    $scope.addNewOrg = function () {
        // var fd = new FormData();
        // fd.append('data', JSON.stringify($scope.add_new_org));
        // fd.append('file', $scope.file);
        // var config = {
        //     headers: {'Content-Type': undefined},
        //     transformRequest: angular.identity
        // }
        $http.post(contextPath + '/org', $scope.add_new_org /*fd, config*/)
            .then(function (response) {
                alert("Успех!");
            });
    };

    $scope.loadHistory();
    $rootScope.loadProducts();
});