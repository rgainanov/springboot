angular.module('app', []).controller('indexController', function ($scope, $http) {
    const appUrl = 'http://localhost:8189/app';
    const apiContextPath = 'http://localhost:8189/app/api/v1';
    $scope.currentPage = 1;
    $scope.pageSize = 7;

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: apiContextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                product_category: $scope.filter ? $scope.filter.product_category : null,
                page: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let startPage = pageIndex - 2;
            let endPage = pageIndex + 2;

            if (endPage > $scope.ProductsPage.totalPages) {
                startPage -= (endPage - $scope.ProductsPage.totalPages);
                endPage = $scope.ProductsPage.totalPages;
            }

            if (startPage <= 0) {
                endPage += ((startPage - 1) * (-1));
                startPage = 1;
            }

            endPage = endPage > $scope.ProductsPage.totalPages ? $scope.ProductsPage.totalPages : endPage;

            $scope.PaginationArray = $scope.generatePagesIndexes(startPage, endPage);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i <= endPage; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.getPage = function (page) {
        $scope.currentPage = page;
        $scope.fillTable();
    }

    $scope.addProductPage = function () {
        location.href = (appUrl + '/add-product.html');
    }

    $scope.editProductPage = function () {
        location.href = (appUrl + '/edit-product.html');
    }

    $scope.submitCreateNewProduct = function () {
        $http.post(apiContextPath + '/products', $scope.newProduct)
            .then(function (response) {
                location.href = '/app/index.html';
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http.delete(apiContextPath + '/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.updateProductById = function () {
        $http.put(apiContextPath + '/products', $scope.updateProduct)
            .then(function (response) {
                location.href = '/app/index.html';
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});