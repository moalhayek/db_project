var app = angular.module('db-project');

//this will be controler for general dash, might not even need this
app.controller('generalDashController',function(){

});

//this will be controller for general music modules
app.controller('musicTrendController',function(sharedProperties){
    this.setTrends = function(minAge,maxAge){
        //REST endpoint
        var results = {'Gospel': 25, 'Rock': 35, 'Techno': 13, 'Hip-hop': 40};
        sharedProperties.setProperty('musicTrends',results);
    };

    this.getTrends = function(){
        return sharedProperties.getProperty('musicTrends');
    }

    this.data = [];
    this.labels =['Gospel','Rock','Techno','Hip-hop'];
    this.options = {legend: {display: true}};

    this.refreshChart = function(min,max){
        this.setTrends(min,max);
        this.setData();
        //this.setLabels();
    }
    this.setData = function(){
        var tempData = [];

        console.log(this.getTrends());
        angular.forEach(this.getTrends(),function(value,key){ 

            console.log(value);
            tempData.push(value);
        });

        this.data = tempData;
    };

    this.setLabels = function(){
        var tempLabels = [];

        console.log(this.getTrends());
        angular.forEach(this.getTrends(),function(value,key){ 
            console.log(key)
            tempLabels.push(key);
        });

        this.labels = tempLabels;
    }

    this.slider = {
        minValue: 21,
        maxValue: 30,
        options: {
            floor: 21,
            ceil: 60,
            step: 1,
            minRange: 1
        }
    };
});