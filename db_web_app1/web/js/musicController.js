var app = angular.module('db-project');

//this will be controller for general music modules
app.controller('musicTrendController',function(sharedProperties,$q){
    this.setTrends = function(barId){
        //REST endpoint
        var options = {
            url: 'music/getMusicTrends',
            params: {
                barId: barId,
            },
            method: 'GET',
            destination: 'musicTrends'//this is the global property you want to update
        }
        var promise = sharedProperties.httpReq(options); // uncomment when server is up

        promise.then(function(res){
            sharedProperties.setProperty('musicTrends',res);
            console.log(sharedProperties.getProperty('musicTrends'))
            this.setData();
            this.setLabels();
        }.bind(this))

    };

    this.getTrends = function(){
        return sharedProperties.getProperty('musicTrends').musicData;
    }

    this.data = [];
    this.labels = [];


    this.setData = function(){
        var tempData = [];

        var averageArr = this.getTrends();
        console.log(averageArr)

        averageArr.forEach(function(elem){
            tempData.push(elem.listeners);
        })

        this.data = tempData;
    };

    this.setLabels = function(){
        var tempLabels = [];

        var averageArr = this.getTrends();
        console.log(averageArr)

        averageArr.forEach(function(elem){
            tempLabels.push(elem.genre);
        })

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