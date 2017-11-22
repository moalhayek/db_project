(function(){
    var app = angular.module('db-project',['ngMaterial','chart.js']);   

    app.controller('pageController',function(){
        this.pages = ['home','personal_bar_data', 'modify_bar_data']
        this.currPage = 'personal_bar_data';
        
        this.changePage = function(newPage){
            
            if (this.pages.includes(newPage)){
                console.log(newPage);
                this.currPage = newPage;
            }else{
                console.log('woah nelly, you tried switching to a non-existent page!')
            }
        };
    });
})();