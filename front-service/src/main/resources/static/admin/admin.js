angular.module('store').controller('adminController', function ($scope, $http, $localStorage, $rootScope) {
    // использовать для локального подключения
    const contextPathCore = 'http://localhost:5555/core/api/v1/';
    const contextPathAuth = 'http://localhost:5555/auth/api/v1/';
    // использовать для удаленного подключения
    // const contextPathCore = 'http://95.165.90.118:443/core/api/v1/';
    // const contextPathAuth = 'http://95.165.90.118:443/auth/api/v1/';

    $scope.getUsers = function () {
        $http.get(contextPathAuth + 'users/all')
            .then(function (response) {
                $scope.usersList = response.data;
            });
    };

    $scope.userBun = function (id) {
        $http.get(contextPathAuth + 'users/bun/' + id)
            .then(function (response) {
                $scope.getUsers();
            });
    };

    $scope.setRole = function () {
        console.log($scope.user)
        $http.post(contextPathAuth + 'users/set_role', $scope.user)
            .then(function (response) {
                alert('Роль успешно добавлена!');
                $scope.user.email = null;
                $scope.user.role = null;
            });
    };

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPathCore + 'products',
            method: 'GET',
            params: {
                p: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                min_price: $scope.filter ? $scope.filter.min_price : null
            }
        }).then(function (response) {
            $scope.filter.title_part = null;
            $scope.filter.max_price = null;
            $scope.filter.min_price = null;
            $scope.ProductPage = response.data;
            console.log(response.data);
            $scope.indexNumber = $scope.ProductPage.totalPages;
            $scope.generatePagesList($scope.ProductPage.totalPages);
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

    $scope.loadAllHistory = function () {
        $http.get(contextPathCore + 'history/all')
            .then(function (response) {
                $scope.allHistoryList = response.data;
            });
    };

    $scope.notConfirmed = function () {
        $http.get(contextPathCore + 'org/not_confirmed')
            .then(function (response) {
                $scope.notConfirmedOrg = response.data;
                console.log(response.data);
            });
    };

    $scope.upUsersBalance = function () {
        $http.post(contextPathAuth + 'users/up_balance', $scope.user_up)
            .then(function successCallback(response) {
                alert('Баланс увеличен!');
                $scope.user_up.email = null;
                $scope.user_up.balance = null;
            });
    };

    $scope.sendMessage = function () {
        $http.post(contextPathAuth + 'users/message', $scope.users_msg)
            .then(function (response) {
                alert('Сообщение отправлено!');
                $scope.users_msg.recipient = null;
                $scope.users_msg.message = null;
            });
    };

    $scope.confirmedOrg = function (title) {
        $http.get(contextPathCore + 'org/confirm/' + title)
            .then(function (response) {
                alert('Организация успешно одобрена.');
                $scope.notConfirmed();
            });
    };

    $scope.notConfirmedProduct = function () {
        $http.get(contextPathCore + 'products/not_confirmed')
            .then(function (response) {
                $scope.notConfirmedProd = response.data;
                console.log(response.data);
            });
    };

    $scope.confirmedProduct = function (title) {
        $http.get(contextPathCore + 'products/confirm/' + title)
            .then(function (response) {
                alert('Продукт успешно одобрен.');
                $scope.notConfirmed();
            });
    };

    $scope.getUsers();
    $scope.notConfirmedProduct();
    $scope.notConfirmed();
    $scope.loadAllHistory();
    $rootScope.loadProducts();

});