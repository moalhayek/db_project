(function(){
    var app = angular.module('db-project',['ngMaterial','chart.js']);

    app.directive('sticky',Sticky);
    Sticky.$inject = ['$mdSticky'];


    function Sticky($mdSticky){
        return {
            restrict: 'A',
            link: function(scope,element){
                $mdSticky(scope,element);
            }
        }
    }
    app.config(function($mdThemingProvider){
        $mdThemingProvider.theme('cyan-theme')
        .primaryPalette('cyan')
        .accentPalette('green')
        .warnPalette('orange');
    });

    app.controller('pageController',function(){
        this.pages = ['home','personal_bar_data','general_bar_data']
        this.currPage = 'personal_bar_data'
        this.changePage = function(newPage){

            if (this.pages.includes(newPage)){
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

    

    app.controller('personalDashController',function(sharedProperties){
        this.noCache = true;

        this.barSelected = false;

        this.recalcData = function(){
            this.barSelected = false;
            this.barSelected = true;
        };

        this.getBars = function(){
            var results = [
            {'name': 'Sean','id': '1'},
            {'name': 'Mo','id': '2'},
            {'name': 'Brian','id': '3'},
            {'name': 'Ridwan','id': '4'},
            {'name': 'Imielinski','id': '5'}
            ];

            sharedProperties.setProperty('barNames',results);
        };
        this.allBars = function(){
            return sharedProperties.getProperty('barNames');
            //return ['1','2','3'];
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

    app.controller("earnings", function () {

        this.timeType = 'day-of-week';  
      this.labels_months = ['Foo',"January", "February", "March", "April", "May", "June", "July"];
      this.labels_days_of_week = ['Mon','Tue','Wed','Thurs','Fri','Sat','Sun'];
      this.series = ['Happy Hour Sales', 'Late Night Sales'];
      this.data = [
        [65, 59, 80, 81, 56, 55, 40.34],
        [28, 48, 40, 19, 86, 27, 90,25]
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
    });

    app.controller("advertisementsController", function (sharedProperties) {

        this.timeType = 'monthly';  
      this.labels = ['Foo',"January", "February", "March", "April", "May", "June", "July"];
      this.labels_days_of_week = ['Mon','Tue','Wed','Thurs','Fri','Sat','Sun'];
      this.series = ['Happy Hour Sales', 'Late Night Sales'];
      this.data = [
        [65, 59, 80, 81, 56, 55, 40.34],
        [28, 48, 40, 19, 86, 27, 90,25]
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

        this.setAds = function(barID){
            //REST call
            console.log('ads data generated with barID ' +barID);
            /*var results = [
                {'id': 1, 'name': 'Google','startDate': '02-2017','endDate': '06-2017'
                'Facebook','Youtube'
            ];*/
            var results = ['Google','Facebook','Youtube'];
            sharedProperties.setProperty('advertisements',results);
        };

        this.getAds = function(){
            return sharedProperties.getProperty('advertisements');
        };
    });

    app.controller("beerInfoController", function () {

        this.timeType = 'day-of-week';  
      this.labels = ['Foo',"January", "February", "March", "April", "May", "June", "July"];
      this.labels_days_of_week = ['Mon','Tue','Wed','Thurs','Fri','Sat','Sun'];
      this.series = ['Happy Hour Sales', 'Late Night Sales'];
      this.data = [
        [65, 59, 80, 81, 56, 55, 40.34],
        [28, 48, 40, 19, 86, 27, 90,25]
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
    });

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
})();