angular.module('store').controller('orderController', function ($scope, $http, $localStorage, $rootScope) {
    // использовать для локального подключения
    const contextPath = 'http://localhost:5555/core/api/v1/';
    // использовать для удаленного подключения
    // const contextPath = 'http://95.165.90.118:443/core/api/v1/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'orders')
            .then(function (response) {
                $scope.order = response.data;
                $scope.status = response.data.status;
                $rootScope.showUserBalance();
            });
    };

    $scope.deleteOrder = function (orderId) {
        $http.delete(contextPath + 'orders/' + orderId)
            .then(function (response) {
                $scope.loadOrders();
            });
    };

    $scope.payment = function (orderId) {
        $http.get(contextPath + 'orders/payment/' + orderId)
            .then(function (response) {
                $scope.loadOrders();
                $rootScope.showUserBalance();
            });
    };

    $scope.loadOrders();
    $rootScope.showUserBalance();

});