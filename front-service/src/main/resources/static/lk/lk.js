angular.module('store').controller('lkController', function ($scope, $http, $localStorage, $rootScope) {
    // использовать для локального подключения
    const contextPath = 'http://localhost:5555/core/api/v1';
    // использовать для удаленного подключения
    // const contextPath = 'http://95.165.90.118:443/core/api/v1';

    $scope.loadHistory = function () {
        $http.get(contextPath + '/history')
            .then(function (response) {
                $scope.historyList = response.data;
            });
    };

    $scope.saveProduct = function () {
        $http.post(contextPath + '/products', $scope.save_product)
            .then(function (response) {
                alert("Успех!");
                $scope.save_product.title = null;
                $scope.save_product.description = null;
                $scope.save_product.organizationTitle = null;
                $scope.save_product.price = null;
                $scope.save_product.quantity = null;
            });
    };

    // $scope.addNewOrg = function () {
    //     const fd = new FormData();
    //     // fd.append('data', JSON.stringify($scope.add_new_org));
    //     fd.append('file', $scope.file);
    //     const config = {
    //         headers: {'Content-Type': undefined},
    //         transformRequest: angular.identity
    //     }
    //     $http.post(contextPath + '/org', fd)
    //         .then(function (response) {
    //             alert("Успех!");
    //         });
    // };

    $scope.createCompany = function() {
        // Получаем значения полей из формы
        const companyOwner = $localStorage.simpleUser.username;
        const companyName = document.getElementById("companyName").value;
        const companyDescription = document.getElementById("companyDescription").value;
        const companyImage = document.getElementById("companyImage").files[0];

        // Создаем объект FormData и добавляем значения полей
        const formData = new FormData();
        formData.append("owner", companyOwner)
        formData.append("name", companyName);
        formData.append("description", companyDescription);
        formData.append("companyImage", companyImage);

        // Отправляем POST запрос на сервер, передавая объект FormData
        fetch(contextPath + '/org', {
            method: "POST",
            body: formData
        })
            .then(function (response){
                alert("Успех!");
            })
            // .then(data => {
            //     // Обновляем объект Company на странице
            //     const company = data;
            //     const companyImageElement = document.getElementById("companyImageElement");
            //     companyImageElement.src = "data:image/png;base64," + company.image;
            // })
            .catch(error => console.error(error));
    }

    // $scope.addNewOrg = function () {
    //     const fd = new FormData();
    //     // console.log(file)
    //     fd.append('file', $scope.file);
    //
    //     const xhr = new XMLHttpRequest();
    //     xhr.open('POST', contextPath + '/org', true);
    //     xhr.send($scope.file);
    //     alert("Успех!");

        // $http({
        //     url: contextPath + '/org',
        //     method: 'POST',
        //     params: {
        //         file: $scope.file
        //     }
        // }).then(function (response) {
        //     alert("Успех!");
        // });
    // };

    $scope.loadHistory();
    $rootScope.loadProducts();
});