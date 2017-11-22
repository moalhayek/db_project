var app = angular.module('db-project');

app.controller('personalDashController',function(sharedProperties){

    this.clearValue = function(){
        this.selectedBar = undefined;
    }

    this.getBars = function(){
        
        var options = {
            url: 'bars/getBarNames',
            params: {},
            method: 'GET',
            destination: 'barNames'//this is the global property you want to update
        }
        var promise =  sharedProperties.httpReq(options);
        promise.then(function(res){
            var temp = res.bars
            console.log(temp)
            temp.splice(0,1)
            console.log(temp)
            sharedProperties.setProperty('barNames',temp)
            console.log(sharedProperties.getProperty('barNames'))
        }.bind(this));
        
    };
    this.allBars = function(){
        return sharedProperties.getProperty('barNames');
    };

});

//this will be all info about the personal bartender module


