/* Declare App's AngularJS Modules */
var app = angular.module('tipCalcApp', ['ngRoute', 'tipCalcControllers']);

/* AngularJS Route Provider */
app.config(['$routeProvider',
function ($routeProvider) {
    $routeProvider.
    when('/home', {
        templateUrl: 'views/home.html',
        controller: 'tipCalcController'
    }).
    when('/java', {
        templateUrl: 'views/java.html'
    }).
    otherwise({
        redirectTo: '/home'
    });
}]);
