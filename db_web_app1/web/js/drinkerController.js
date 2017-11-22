var app = angular.module('db-project');

//this will be the controller for the drinker module
app.controller('drinkerController',function(sharedProperties,$q){
    this.setDrinkers = function(barId){
        //REST endpoint
        var options = {
            url: 'drinkers/getDrinkers',
            params: {
                barId: barId,
            },
            method: 'GET',
            destination: 'drinkerInfo'//this is the global property you want to update
        }
        var promise = sharedProperties.httpReq(options); // uncomment when server is up

        promise.then(function(res){
            sharedProperties.setProperty('drinkerInfo',res.drinkers);
            console.log(sharedProperties.getProperty('drinkerInfo'))
            this.setData();
        }.bind(this))

    };

    this.labels = ['Drinker','Most Expensive Beer Liked','Spending Per Night']
    this.sortType = 'name'
    this.changeSort = function(toWhat){
        var oldSort = this.sortType
        this.sortType = this.sortMap[toWhat];
        if(oldSort ==this.sortType) {
            this.sortReverse = !this.sortReverse;
        }else{
            this.sortReverse = false;
        }
    }

    this.sortMap = {
        'Drinker': 'name',
        'Most Expensive Beer Liked': 'favBeer',
        'Spending Per Night': 'spendingPerNight'
    }

    this.sortReverse = false

    this.getDrinkers = function(){
        return sharedProperties.getProperty('drinkerInfo');
    }

    this.data = [];

    this.setData = function(){
        console.log('attempting to update data')
        var allData = sharedProperties.getProperty('drinkerInfo');
        console.log(allData)
        var tempData = []

        allData.forEach(function(elem){
            var name = elem.name;
            var favBeer = elem.expensiveFavBeer;
            var spendingPerNight = elem.spendingPerNight;
            var dataSet = {};
            dataSet.name = name;
            dataSet.favBeer = favBeer;
            dataSet.spendingPerNight = spendingPerNight;

            tempData.push(dataSet);
        });
        this.data = tempData
    }

    // this.setFavBeers = function(){
    //     var tempFavs = [];
    //
    //     var drinkersArr = this.getDrinkers();
    //     console.log(drinkersArr)
    //
    //     drinkersArr.forEach(function(elem){
    //         tempFavs.push(elem.expensiveFavBeer);
    //     })
    //
    //     this.favBeers = tempFavs;
    // };
    //
    // this.setSpendings = function(){
    //     var tempSpendings = [];
    //
    //     var drinkersArr = this.getDrinkers();
    //     console.log(drinkersArr)
    //
    //     drinkersArr.forEach(function(elem){
    //         tempSpendings.push(elem.spendingPerNight);
    //     })
    //
    //     this.spendingPerNight = tempSpendings;
    // }
    //
    // this.setNames = function(){
    //     var tempNames = [];
    //
    //     var drinkersArr = this.getDrinkers();
    //     console.log(drinkersArr)
    //
    //     drinkersArr.forEach(function(elem){
    //         tempNames.push(elem.name);
    //     })
    //
    //     this.names = tempNames;
    // }
});