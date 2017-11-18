var app = angular.module('db-project');

app.directive('sticky',Sticky);
Sticky.$inject = ['$mdSticky'];

function Sticky($mdSticky){
    return {
        restrict: 'A',
        link: function(scope,element){
            $mdSticky(scope,element);
        }
    }
}

app.config(function($mdThemingProvider){
    $mdThemingProvider.theme('cyan-theme')
    .primaryPalette('cyan')
    .accentPalette('green')
    .warnPalette('orange');
});