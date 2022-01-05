angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';
    $scope.currentPage = 1;
    $scope.pageSize = 7;

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                product_category: $scope.filter ? $scope.filter.product_category : null,
                page: $scope.currentPage,
                size: $scope.pageSize,
            }
        }).then(function (response) {
            console.log(response)
            $scope.ProductsList = response.data.products;
            $scope.PageNumbers = response.data.pageNumbers;
            $scope.PageNumber = response.data.currentPage;
        });
    };

    $scope.getPage = function (page) {
        $scope.currentPage = page;
        $scope.fillTable();
    }

    $scope.addProductPage = function () {
        location.href = (contextPath + '/add-product.html');
    }

    $scope.editProductPage = function () {
        location.href = (contextPath + '/edit-product.html');
    }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                location.href = '/app/index.html';
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.updateProductById = function () {
        $http.put(contextPath + '/products', $scope.updateProduct)
            .then(function (response) {
                location.href = '/app/index.html';
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});