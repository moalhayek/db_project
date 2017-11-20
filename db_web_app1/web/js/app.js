(function(){
    var app = angular.module('db-project',['ngMaterial','chart.js','rzModule']);   

    app.controller('pageController',function(){
        this.pages = ['home','personal_bar_data','general_bar_data']
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

    app.controller('dbRequest',function(){
        this.sampleQuery = function(){
            //url here is the rest endpoint for our amazon ec2
            var options = {
                url: 'amazon.com/hello',
                params: {},
                method: 'GET',
                destination: ''//this is the global property you want to update
            }
            sharedProperties.httpReq(options);
        }
    });
})();