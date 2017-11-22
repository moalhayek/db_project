var app = angular.module('db-project');

app.controller('modifyDataController',function(sharedProperties,$mdDialog,$http){

    this.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
        'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
        'WY').split(' ').map(function(state) {
        return {abbrev: state};
    });

    this.genderOptions = [
        {name: 'Male', abbrev: 'M'},
        {name: 'Female', abbrev: 'F'},
        {name: 'Other', abbrev: 'M'}
    ]

    this.statuses = ['Single', 'In a relationship']
    this.prefs = ['Not crowded','Moderately crowded','Very crowded']


    this.addBeer = function(beer){
        var promise = $http({
            method: 'POST',
            url: 'http://localhost:8080/rest/beers/addBeer',
            data: {
                bar_id: beer.bar_id,
                beer_id: beer.beer_id,
                is_on_tap: beer.is_on_tap,
                price: beer.price
            }
        }).then(function mySuccess(response){
            console.log(response)
            console.log('beer was added succesfully')

            var printStr = response.data==2? 'You are now selling a new beer at your bar!': 'You have now changed the selling price of this beer at your bar!'
            $mdDialog.show(
                $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#popupContainer')))
                    .clickOutsideToClose(true)
                    .title('Success!')
                    .textContent(printStr)
                    .ariaLabel('Alert Dialog Demo')
                    .ok('Got it!')
                    .targetEvent()
            )

        },function myError(response){
            console.log(response);
            console.log('adding/updating the beer failed');

            $mdDialog.show(
                $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#popupContainer')))
                    .clickOutsideToClose(true)
                    .title('Failure:')
                    .textContent('Sorry, but I cannot allow you to sell a beer for less than its manufacturing price!')
                    .ariaLabel('Alert Dialog Demo')
                    .ok('Got it!')
                    .targetEvent()
            )
        })

        promise.then(function(){
            this.beer = undefined;
        }.bind(this))
    }

    this.addDrinker = function(drinker){
        var promise = $http({
            method: 'POST',
            url: 'http://localhost:8080/rest/drinkers/addDrinker',
            data: {
                name: drinker.name,
                age: drinker.age,
                gender: drinker.gender,
                street_address: drinker.street_address,
                city: drinker.city,
                state: drinker.state,
                salary: drinker.salary,
                spending_per_night: drinker.spending_per_night,
                crowding_pref: drinker.crowding_pref,
                relationship_status: drinker.relationship_status
            }
        }).then(function mySuccess(response){
            console.log('the drinker was added successfully!');
            $mdDialog.show(
                $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#popupContainer')))
                    .clickOutsideToClose(true)
                    .title('Success!')
                    .textContent('You added a drinker to the table successfully')
                    .ariaLabel('Alert Dialog Demo')
                    .ok('Got it!')
                    .targetEvent()
            )

        },function myError(response){
            console.log(response.statusText);
            console.log('adding the drinker failed');
            $mdDialog.show(
                $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#popupContainer')))
                    .clickOutsideToClose(true)
                    .title('Failure:')
                    .textContent('Sorry, I can\'t add a drinker younger than 21, and you can\'t spend so much of your salary on drinking. Tsk, tsk!')
                    .ariaLabel('Alert Dialog Demo')
                    .ok('Got it!')
                    .targetEvent()
            )
        })

        promise.then(function(){
            this.drinker = undefined;
        }.bind(this))
    }

    this.setData = function(){
        this.setAllBeers();
        this.setAllBars();
    }

    this.setAllBars = function(){
        var options = {
            url: 'bars/getBarNames',
            params: {},
            method: 'GET',
        }
        var promise =  sharedProperties.httpReq(options);
        promise.then(function(res){
            var temp = res.bars
            temp.splice(0,1)
            sharedProperties.setProperty('allBars',temp)
            console.log(sharedProperties.getProperty('allBars'))
        }.bind(this));
    }

    this.setAllBeers = function(){
        var options = {
            url: 'beers/getAllBeers',
            params: {},
            method: 'GET',
        }
        var promise =  sharedProperties.httpReq(options);
        promise.then(function(res){
            var temp = res.beers2
            temp.splice(0,1)
            sharedProperties.setProperty('allBeers',temp)
            console.log(sharedProperties.getProperty('allBeers'))
        }.bind(this));

    }

    this.getBeers = function(){
        return sharedProperties.getProperty('allBeers')
    }

    this.getBars = function(){
        return sharedProperties.getProperty('allBars')
    }
});