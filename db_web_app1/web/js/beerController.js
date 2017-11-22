var app = angular.module('db-project');

//controller for personal beer info
app.controller("beerInfoController", function (sharedProperties) {
    this.data = [];
    this.labels = ['Total Sold','Sale Price','Total Profit'];
    this.series = [];

    this.beerDict = {};

    this.addBeer = function(newBeer){
        var name = newBeer.name
        var manf = newBeer.manuf
        var name_str = manf +': ' +name
        this.beerDict[name_str] = newBeer;
        this.series.push(name_str);
        this.setData(newBeer);
    }

    this.removeBeer = function(beerName){

        delete this.beerDict[beerName]

        var index = this.series.indexOf(beerName)

        this.series.splice(index,1)
        this.data.splice(index,1)
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

    this.setData = function(newBeer){
        console.log(newBeer);
        var dataSet = [newBeer['totalSold'], newBeer['salePrice'], newBeer['totalProfit']];
        this.data.push(dataSet);
    };

    this.getAllBeers = function(){
        //console.log('heidy ho')
        //console.log(sharedProperties.getProperty('myBeers'))
        return sharedProperties.getProperty('myBeers')
    }
});