angular.module('store').controller('cartController', function ($scope, $http, $location, $localStorage, $rootScope) {
    // использовать для локального подключения
    // const contextPath = 'http://localhost:5555/cart/api/v1/';
    // const contextCorePath = 'http://localhost:5555/core/api/v1/';
    // использовать для удаленного подключения
    const contextPath = 'http://95.165.90.118:443/cart/api/v1/';
    const contextCorePath = 'http://95.165.90.118:443/core/api/v1/';

    if ($localStorage.simpleUser) {
        $rootScope.isActiveUser($localStorage.simpleUser.username);
    }

    $scope.loadCart = function () {
        $http.get(contextPath + 'cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.deleteFromCart = function (productId) {
        $http.get(contextPath + 'cart/remove/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + 'cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + 'cart/change_quantity',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.createOrder = function () {
        if ($scope.cart.totalCost === 0) {
            alert('Добавьте хотя бы один товар!');
            $location.path('/catalog');
            return;
        }

        $http.post(contextCorePath + 'orders')
            .then(function (response) {
                $scope.clearCart();
                alert('Ваш заказ создан!');
                $location.path('/orders');
            });
    };

    $scope.loadCart();

});