angular.module('store').controller('catalogController', function ($scope, $http, $localStorage, $rootScope) {
    // использовать для локального подключения
    // const contextCartPath = 'http://localhost:5555/cart/api/v1/';
    // использовать для удаленного подключения
    const contextCartPath = 'http://95.165.90.118:443/cart/api/v1/';

    $scope.sendToCart = function (id) {
        $http.get(contextCartPath + 'cart/add/'+ id)
            .then(function (response) {
            });
    };

    $rootScope.loadProducts();
    $rootScope.showUserBalance();

});