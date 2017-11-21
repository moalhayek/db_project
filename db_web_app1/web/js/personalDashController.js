var app = angular.module('db-project');

app.controller('personalDashController',function(sharedProperties){
    this.noCache = true;

    this.barSelected = false;

    this.recalcData = function(){
        this.barSelected = false;
        this.barSelected = true;
    };

    this.getBars = function(){
        
        var options = {
            url: 'bars/getBarNames',
            params: {},
            method: 'GET',
            destination: 'barNames'//this is the global property you want to update
        }
        var promise =  sharedProperties.httpReq(options);
        promise.then(function(res){
            console.log(res)
            console.log(res[0])
            console.log(res.data)
            sharedProperties.setProperty('barNames',res)
            console.log(sharedProperties.getProperty('barNames'))
        }.bind(this));
        
    };
    this.allBars = function(){
        return sharedProperties.getProperty('barNames').bars;
    };
    
    this.searchBarNames = function(searchText){
        var results = searchText ? this.allBars.filter(createFilterFor(query)): self.allBars;

        return results;
    }

    this.createFilterFor = function(query){
        var lowercaseQuery = angular.lowercase(query);

        return function filterFn(name){
            return (name.value.indexOf(lowercaseQuery)===0);
        };
    };
});

//this will be all info about the personal bartender module
app.controller('bartenders',function(sharedProperties){

    this.series = ['Series A', 'Series B'];
    this.data = [
        [65, 59, 80, 81, 56]
    ];
      
    this.onClick = function (points, evt) {
        //console.log(points, evt);
    };

    this.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
    this.options = {
        scales: {
        yAxes: [
            {
              id: 'y-axis-1',
              type: 'linear',
              display: true,
              position: 'left'
            },
            {
              id: 'y-axis-2',
              type: 'linear',
              display: true,
              position: 'right'
            }
          ]
        }
    };

    this.setBartenders = function(barID){
        //REST endpoint with whichBar as param
        console.log('bartenders info generated with barID ' + barID);
        var results = ['Mo','Brian','Ryan','James','Sasha'];
        sharedProperties.setProperty('bartenders', results);
    };
    //this.labels = ['Mo','Brian','Ryan','James','Sasha'];
    this.labels = function(){
        return sharedProperties.getProperty('bartenders');
    };
});

