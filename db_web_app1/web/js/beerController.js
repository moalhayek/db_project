var app = angular.module('db-project');

//controller for personal beer info
app.controller("beerInfoController", function () {

    this.data = [];
    this.labels = [];
    this.series = [];

    this.instantiate = function(barID){
        this.getAllBeers(barID)
    }
    this.getAllBeers = function(barID){
        var options = {
            url: 'beers/getBeers',
            params: {
                barId: barID,
                startDate: this.startDate,
                endDate: this.endDate,
                timeOfDay: this.getTimeType()
            },
            method: 'GET',
            destination: 'dailyAverages'
        }

        sharedProperties
    }
});

//controller for gen beer
app.controller('genBeerInfoController',function(){

});