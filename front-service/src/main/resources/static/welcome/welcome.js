angular.module('store').controller('welcomeController', function ($scope, $http, $localStorage, $rootScope) {

    if ($localStorage.simpleUser) {
        $rootScope.isActiveUser($localStorage.simpleUser.username);
    }

});