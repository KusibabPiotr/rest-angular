var app = angular.module('app');

app.controller("ClientController",function ($http){
    var cc = this;

    function refreshData(){
        $http({
            method: 'GET',
            url: '/clients'
        }).then(function (response){
            cc.clients = response.data
        },function (response){
            console.log("Something goes wrong")
        })
    }
    cc.addClient = function (client,details){
        client.clientDetails = details;
        $http({
            method: 'POST',
            url: '/clients',
            data: client
        }).then(function (){
            refreshData();
            cc.client = {};
            cc.details = {};
            console.log("All gut")
        },function (){
            console.log("Something Goes Wrong")
        })
    }
    refreshData();
})
app.controller("ProductController",function ($http){
    var pc = this;

    function refreshData(){
        $http({
            method: 'GET',
            url: '/products'
        }).then(function (response){
            pc.products = response.data
        },function (response){
            console.log("nicht javol")
        })
    }
    pc.addProduct = function addProduct(product){
        $http({
            method: 'POST',
            url: '/products',
            data: product
        }).then(function (){
            refreshData();
            pc.product = {};
        },function (){
            console.log("Błąd")
        })
    }
    this.title1 = 'Add Products';
    this.title2 = 'All Products';
    refreshData();

})