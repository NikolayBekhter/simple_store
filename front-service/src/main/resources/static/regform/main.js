angular.module('store').controller('authController', function ($scope, $http, $localStorage, $location, $rootScope) {
    // использовать для локального подключения
    // const contextPath = 'http://localhost:5555/auth/api/v1/';
    // использовать для удаленного подключения
    const contextPath = 'http://95.165.90.118:443/auth/api/v1/';

    $scope.tryToAuth = function () {
        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.simpleUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $http.get(contextPath + 'users/get_roles/' + $localStorage.simpleUser.username)
                        .then(function (response) {
                            let roles = response.data;
                            $localStorage.roleIndex = roles.findIndex(item => item.name === 'ROLE_ADMIN');
                        })

                    $location.path('/');

                }
            });
    };

    $scope.tryToReg = function () {
        $http.post(contextPath + 'registration', $scope.new_user)
            .then(function (response) {
                alert('Аккаунт успешно создан!')
                $location.path('/auth');
            })
    };

});
