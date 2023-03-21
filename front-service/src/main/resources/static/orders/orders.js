angular.module('store').controller('orderController', function ($scope, $http, $localStorage) {
    // использовать для локального подключения
    // const contextPath = 'http://localhost:5555/core/api/v1/';
    // использовать для удаленного подключения
    const contextPath = 'http://95.165.90.118:443/core/api/v1/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'orders')
            .then(function (response) {
                $scope.order = response.data;
            });
    };

    $scope.showUser = function () {
        $localStorage.simpleUser.username;
    };

    $scope.deleteOrder = function (orderId) {
        $http.delete(contextPath + 'orders/' + orderId)
            .then(function (response) {
                $scope.loadOrders();
            });
    };

    $scope.loadOrders();

});