var app = angular.module('db-project');

//convert from YYYY-MM to MM/YYYY
app.filter('dateFilter',function(){
    return function(date){
        dateArr = date.split('-');
        return dateArr[1]+'/'+dateArr[0];
    }
});