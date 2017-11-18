var app = angular.module('db-project')

//controller for personal ad purchase info
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
        
        var results = [
          {'id': 1,'name': 'Google','startDate': '01-02-2017','endDate': '05-07-2017','costToDate': 2567,'costPerClick': 0.64,'clicksToDate': 3145},
          {'id': 2,'name': 'Facebook','startDate': '04-22-2016','endDate': '12-07-2016','costToDate': 2567,'costPerClick': 0.64,'clicksToDate': 3145},
          {'id': 3,'name': 'Youtube','startDate': '11-12-2016','endDate': '11-17-2017','costToDate': 2567,'costPerClick': 0.64,'clicksToDate': 3145}
        ];
        sharedProperties.setProperty('advertisements',results);
    };

    this.getAds = function(){
        return sharedProperties.getProperty('advertisements');
    };
});

//controller for the general ad platform info
app.controller('adPlatformController',function(){

});