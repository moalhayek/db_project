var app = angular.module('db-project');

//controller for personal beer info
app.controller("beerInfoController", function (sharedProperties) {

    this.data = [];
    this.labels = [];
    this.series = [];

    this.beerDict = {};

    this.addBeer = function(newBeer){
        var name = newBeer.name
        var manf = newBeer.manuf
        var name_str = name+' ' +manf
        this.beerDict[name_str] = newBeer;
    }

    this.removeBeer = function(beerName){

        delete this.beerDict[beerName]
    }

    this.setAllBeers = function(barID){
        var options = {
            url: 'beers/getBeers',
            params: {
                barId: barID,
            },
            method: 'GET',
            destination: 'myBeers'
        }

        var promise = sharedProperties.httpReq(options)

        promise.then(function(res){
            console.log(res)
            sharedProperties.setProperty('myBeers',res.beers)
            console.log(sharedProperties.getProperty('myBeers'))
        }.bind(this))
    }

    this.getAllBeers = function(){
        //console.log('heidy ho')
        //console.log(sharedProperties.getProperty('myBeers'))
        return sharedProperties.getProperty('myBeers')
    }
});

//controller for gen beer
app.controller('genBeerInfoController',function(){

});