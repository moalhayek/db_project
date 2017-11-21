var app = angular.module('db-project');

//this will be controller for personal earnings
app.controller("earnings", function (sharedProperties) {

    this.startDate = '2016-01'
    this.endDate = '2016-12'

    this.refresh = function(){
        console.log('refreshing chart')
        console.log(this.getDailyAverages())
        var dailyAveArr = this.getDailyAverages()
        var monthlyArr = this.getEarnings()
        console.log('setting data_daily')
        this.data_daily = this.setData(dailyAveArr);
        console.log('setting daily labels')
        this.labels_daily = this.setLabels(dailyAveArr);
        console.log('setting monthly data')
        this.data_monthly = this.setData(monthlyArr);
        console.log('setting monthly labels')
        this.labels_monthly = this.setLabels(monthlyArr)
    }

    this.instantiate = function(barID){
        this.setDailyAverages();
        this.setEarnings('monthly',barID)
        this.refresh()
    }

    this.monthMap = {
        '1': 'Jan',
        '2': 'Feb',
        '3': 'Mar',
        '4': 'Apr',
        '5': 'May',
        '6': 'Jun',
        '7': 'Jul',
        '8': 'Aug',
        '9': 'Sep',
        '10': 'Oct',
        '11': 'Nov',
        '12': 'Dec'   
    }

    this.dayMap = {
        '1' : 'Sun',
        '2': 'Mon',
        '3': 'Tue',
        '4': 'Wed',
        '5': 'Thu',
        '6': 'Fri',
        '7': 'Sat'
    }

    this.early = false
    this.late = false

    this.switchData = function(whichOne,checked){
        console.log(whichOne + ' just got clicked')
        if(!checked&&whichOne=='total'){
            this.early = false
            this.late = false
        }else if(!checked&&(whichOne =='early'||whichOne=='late')){
            this.total = false
        }
    }

    this.getTimeType = function(){
        if(this.early&&this.late){
            return 'both'
        }else if(this.early){
            return 'early'
        }else if(this.late){
            return 'late'
        }else{
            return 'total';
        }
    }

    this.setDailyAverages = function(barID){
        var options = {
            url: 'transactions/getDailyAverages',
            params: {
                barID: barID,
                startDate: this.startDate,
                endDate: this.endDate,
                timeOfDay: this.getTimeType()
            },
            method: 'GET',
            destination: 'dailyAverages'
        }


        //sharedProperties.httpReq(options)
        var results = {'dailyAverages': [{'earnings': 289,'day': '1'},{'earnings': 423,'day': '2'},{'earnings': 688,'day': '3'},{'earnings': 421,'day': '4'},{'earnings': 723,'day': '5'},{'earnings': 987,'day': '6'},{'earnings': 768,'day': '7'}]};
        console.log('updating sharedProperties with ' + results);
        sharedProperties.setProperty('dailyAverages', results);
    }

    this.setEarnings = function(timeType, barID){        

        var options = {
            url: 'transactions/getEarnings',
            params: {
                type: timeType,
                barID: barID,
                startDate: this.startDate,
                endDate: this.endDate,
                timeOfDay: this.getTimeType()
            },
            method: 'GET',
            destination: 'monthlyEarnings'
        }
        
        //sharedProperties.httpReq(options)
        var results = {"monthly_earnings":[{"earnings":14370,"month":"1","year":"2016"},{"earnings":13445,"month":"2","year":"2016"},{"earnings":13888,"month":"3","year":"2016"},{"earnings":14215,"month":"4","year":"2016"},{"earnings":14301,"month":"5","year":"2016"},{"earnings":13901,"month":"6","year":"2016"},{"earnings":14371,"month":"7","year":"2016"},{"earnings":14321,"month":"8","year":"2016"},{"earnings":14352,"month":"9","year":"2016"},{"earnings":13752,"month":"10","year":"2016"},{"earnings":13263,"month":"11","year":"2016"},{"earnings":14322,"month":"12","year":"2016"}]};
        sharedProperties.setProperty('monthlyEarnings',results)
    };

    this.getEarnings = function(){
        return sharedProperties.getProperty('monthlyEarnings').monthly_earnings;
    }

    this.getDailyAverages = function(){
        return sharedProperties.getProperty('dailyAverages').dailyAverages;
    };


    this.setData = function(inputArr){
        var tempData = [];
      
        //var averageArr = this.getDailyAverages();
        var averageArr = inputArr;
        console.log(averageArr)

        averageArr.forEach(function(elem){
            tempData.push(elem.earnings);
        })
        console.log(tempData)

        //this.data = tempData;
        return tempData;
    };

    this.setLabels = function(inputArr){
        var tempLabels = [];

        //var averageArr = this.getDailyAverages();
        var averageArr = inputArr;
        console.log(averageArr)
        
        var monthMap = this.monthMap
        var dayMap = this.dayMap
        
        averageArr.forEach(function(elem){
            var dayOrMonth = elem.day? dayMap[elem.day] :monthMap[elem.month]
            console.log(dayOrMonth)
            var label = dayOrMonth
            tempLabels.push(label);
        });

        console.log(tempLabels)
        //this.labels = tempLabels;
        return tempLabels;
    }

    this.timeType = 'monthly';  

    this.labels_daily = []
    this.data_daily = []

    this.labels_monthly = []
    this.data_monthly = []

    //this.series = ['Happy Hour Sales', 'Late Night Sales'];
    /*this.data = [
        [65, 59, 80, 81, 56, 55, 40.34],
        [28, 48, 40, 19, 86, 27, 90,25]
    ];*/

    
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
              display: false,
              position: 'right'
            }
          ]
        }
    };
});