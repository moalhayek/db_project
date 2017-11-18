var app = angular.module('db-project');

//controller for personal beer info
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

//controller for gen beer
app.controller('genBeerInfoController',function(){

});