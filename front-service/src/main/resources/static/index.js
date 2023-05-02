(function () {
    angular
        .module('store',  ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'orderController'
            })
            .when('/auth', {
                templateUrl: 'regform/auth.html',
                controller: 'authController'
            })
            .when('/registration', {
                templateUrl: 'regform/registration.html',
                controller: 'authController'
            })
            .when('/catalog', {
                templateUrl: 'catalog/catalog.html',
                controller: 'catalogController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .when('/lk', {
                templateUrl: 'lk/lk.html',
                controller: 'lkController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.simpleUser) {
            try {
                let jwt = $localStorage.simpleUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.simpleUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.simpleUser.token;
        }
    }
})();

angular.module('store').controller('indexController', function ($rootScope, $location, $scope, $http, $localStorage) {
    // использовать для локального подключения
    const contextPath = 'http://localhost:5555/auth/api/v1/';
    const contextPathCore = 'http://localhost:5555/core/api/v1/';
    // использовать для удаленного подключения
    // const contextPath = 'http://95.165.90.118:443/auth/api/v1/';
    // const contextPathCore = 'http://95.165.90.118:443/core/api/v1/';

    $rootScope.showControl = function () {
        if ($localStorage.simpleUser && $localStorage.roleIndex >= 0) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isRoleAdmin = function () {
        $http.get(contextPath + 'users/get_roles/' + $localStorage.simpleUser.username)
            .then(function (response) {
                let roles = response.data;
                $rootScope.roleIndex = roles.findIndex(item => item.name === 'ROLE_ADMIN');
            })
    };

    $rootScope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/auth');
    };

    $scope.clearUser = function () {
        delete $localStorage.simpleUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.simpleUser) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isActiveUser = function (username) {
        $http.get(contextPath + 'users/is_active/' + username)
            .then(function (response) {
                $rootScope.isActive = response.data;
            })
    };

    $rootScope.loadProducts = function (pageIndex = 1) {
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
            $scope.ProductPage = response.data;
            $scope.indexNumber = $scope.ProductPage.totalPages;
            $scope.generatePagesList($scope.ProductPage.totalPages);
        });
    };

    $rootScope.generatePagesList = function (totalPages) {
        $scope.pagesList = [];
        for (let i = 0; i < totalPages; i++) {
            $scope.pagesList.push(i + 1);
        }
    }

    $rootScope.isThereIndex = function () {
        return $scope.indexNumber > 1;
    }

    $rootScope.previousPage = function (page, delta) {
        if ((page + delta) >= 0) {
            $scope.loadProducts(page + delta);
        }
    }

    $rootScope.nextPage = function (page, delta) {
        if ((page + delta) <= $scope.indexNumber) {
            $scope.loadProducts(page + delta);
        }
    }

    $rootScope.showProductInfo = function (productId) {
        $http.get(contextPathCore + 'products/' + productId)
            .then(function (response) {
                alert(response.data.title + ' ' + response.data.organizationTitle);
            });
    };

    $rootScope.showUserBalance = function () {
        $http.get(contextPath + 'users')
            .then(function (response) {
                let user = response.data;
                $scope.userBalance = user.balance;
            })
    };

    $rootScope.showUser = function () {
        return $scope.userBalance;
    };

    $rootScope.isRefund = function (orderId) {
        $http.get(contextPathCore + 'orders/is_refund/' + orderId)
            .then(function (response) {
                $rootScope.isRefundOrder = response.data;
                console.log($rootScope.isRefundOrder);
            });
    };

});