angular.module('store').controller('catalogController', function ($scope, $http, $localStorage, $rootScope) {
    // использовать для локального подключения
    const contextCartPath = 'http://localhost:5555/cart/api/v1/';
    const contextPathCore = 'http://localhost:5555/core/api/v1/';
    // использовать для удаленного подключения
    // const contextCartPath = 'http://95.165.90.118:443/cart/api/v1/';
    // const contextPathCore = 'http://95.165.90.118:443/core/api/v1/';

    $scope.sendToCart = function (id) {
        $http.get(contextCartPath + 'cart/add/' + id)
            .then(function (response) {
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

    $scope.generatePagesList = function (totalPages) {
        $scope.pagesList = [];
        for (let i = 0; i < totalPages; i++) {
            $scope.pagesList.push(i + 1);
        }
    }

    $scope.isThereIndex = function () {
        return $scope.indexNumber > 1;
    }

    $scope.previousPage = function (page, delta) {
        if ((page + delta) >= 0) {
            $scope.loadProducts(page + delta);
        }
    }

    $scope.nextPage = function (page, delta) {
        if ((page + delta) <= $scope.indexNumber) {
            $scope.loadProducts(page + delta);
        }
    }

    $scope.getLogoCompany = function (title) {
        $http.get(contextPathCore + 'logo/' + title)
            .then(function (response) {
                return response.data;
                // var blob = new Blob( [ logo.bytes ], { type: "image/jpeg" } );
                // $scope.imageUrl = URL.createObjectURL( blob );
                // console.log($scope.imageUrl);
            })
            .then(dataNew => {
                // Обновляем объект Company на странице
                // const logo = dataNew;
                // console.log(logo.originalFileName);
                // const companyImageElement = document.getElementById("companyImageElement");
                // companyImageElement.src = "dataNew:image/png;base64," + logo.image;
            })

            .catch(error => console.error(error));
    };

    $rootScope.loadProducts();
    $rootScope.showUserBalance();

});