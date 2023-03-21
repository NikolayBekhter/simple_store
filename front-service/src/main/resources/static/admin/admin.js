angular.module('store').controller('adminController', function ($scope, $http, $localStorage, $rootScope) {
    // использовать для локального подключения
    // const contextPathCore = 'http://localhost:5555/core/api/v1/';
    // const contextPathAuth = 'http://localhost:5555/auth/api/v1/';
    // использовать для удаленного подключения
    const contextPathCore = 'http://95.165.90.118:443/core/api/v1/';
    const contextPathAuth = 'http://95.165.90.118:443/auth/api/v1/';

    $scope.setRole = function () {
        console.log($scope.user)
        $http.post(contextPathAuth + 'users/set_role', $scope.user)
            .then(function successCallback(response) {
                console.log(response.data);
                alert('Роль успешно добавлена!')
            });
    };

    $scope.saveOrUpdateProduct = function () {
            $http.post(contextPathCore + 'products', $scope.save_or_update_product)
                .then(function (response) {
                    alert("Успех!");
                    $scope.save_or_update_product.id = null;
                    $scope.save_or_update_product.title = null;
                    $scope.save_or_update_product.description = null;
                    $scope.save_or_update_product.organizationTitle = null;
                    $scope.save_or_update_product.price = null;
                    $scope.save_or_update_product.quantity = null;
                });
        };

    $rootScope.loadProducts();

});