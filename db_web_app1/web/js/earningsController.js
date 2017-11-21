var app = angular.module('db-project');

//this will be controller for personal earnings
app.controller("earnings", function (sharedProperties) {

    this.startDate = '2016-01'
    this.endDate = '2016-12'

    this.refresh = function(shiftType,holdOld){
        console.log('refreshing chart')
        console.log(this.getDailyAverages())
        var dailyAveArr = this.getDailyAverages()
        var monthlyArr = this.getEarnings()
        console.log('setting data_daily')
        var new_data_daily = this.setData(dailyAveArr,shiftType);

        console.log('setting daily labels')
        this.labels_daily = this.setLabels(dailyAveArr);
        console.log('setting monthly data')
        var new_data_monthly = this.setData(monthlyArr,shiftType);
        console.log('setting monthly labels')
        this.labels_monthly = this.setLabels(monthlyArr)

        if(holdOld){
            if(shiftType=='early'){
                var old_data_daily = this.data_daily[0]
                var old_data_monthly = this.data_monthly[0]

                this.data_daily[0] = new_data_daily
                this.data_daily.push(old_data_daily)

                this.data_monthly[0] = new_data_monthly
                this.data_monthly.push(old_data_monthly)

            }else if(shiftType=='late'){
                this.data_daily.push(new_data_daily)
                this.data_monthly.push(new_data_monthly)
            }
            this.series = ['Early Sales','Late Sales']
        }else{
            this.data_daily = []
            this.data_daily.push(new_data_daily)

            this.data_monthly = []
            this.data_monthly.push(new_data_monthly)

            this.series = []
            this.series.push(shiftType)
        }
    }

    this.instantiate = function(barID,shiftType){
        this.setDailyAverages(barID);
        this.setEarnings('monthly',barID)
        this.refresh(shiftType,false)
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

    this.dateOptions = ['2016-01','2016-02','2016-03','2016-04','2016-05','2016-06','2016-07','2016-08','2016-09','2016-10','2016-11','2016-12',]
    this.early = false
    this.late = false
    this.total = true

    this.switchData = function(whichOne,checked){
        console.log(whichOne + ' just got clicked')
        if(!checked&&whichOne=='total'){
            this.early = false
            this.late = false
            this.refresh(whichOne,false)
        }else if(!checked&&(whichOne =='early')){
            this.total = false
            if(this.late){
                this.refresh(whichOne,true)
            }else{
                this.refresh(whichOne,false)
            }
            
        }else if(!checked&&(whichOne=='late')){
            this.total = false
            this.refresh(whichOne)
            if(this.early){
                this.refresh(whichOne,true)
            }else{
                this.refresh(whichOne,false)
            }
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
                barId: barID,
                startDate: this.startDate,
                endDate: this.endDate,
                timeOfDay: this.getTimeType()
            },
            method: 'GET',
            destination: 'dailyAverages'
        }

        sharedProperties.httpReq(options)
        //var results = {"dailyAverages":[{"avg_early_earnings":504,"avg_late_earnings":1045,"dayOfWeek":1,"avg_total_earnings":1549},{"avg_early_earnings":513,"avg_late_earnings":1072,"dayOfWeek":2,"avg_total_earnings":1585},{"avg_early_earnings":495,"avg_late_earnings":1051,"dayOfWeek":3,"avg_total_earnings":1546},{"avg_early_earnings":500,"avg_late_earnings":1039,"dayOfWeek":4,"avg_total_earnings":1539},{"avg_early_earnings":874,"avg_late_earnings":1740,"dayOfWeek":5,"avg_total_earnings":2614},{"avg_early_earnings":877,"avg_late_earnings":1739,"dayOfWeek":6,"avg_total_earnings":2616},{"avg_early_earnings":863,"avg_late_earnings":1725,"dayOfWeek":7,"avg_total_earnings":2588}]};
        //console.log('updating sharedProperties with ' + results);
        //sharedProperties.setProperty('dailyAverages', results);
    }

    this.setEarnings = function(timeType, barID){        

        var options = {
            url: 'transactions/getEarnings',
            params: {
                type: timeType,
                barID: barId,
                startDate: this.startDate,
                endDate: this.endDate,
                timeOfDay: this.getTimeType()
            },
            method: 'GET',
            destination: 'monthlyEarnings'
        }
        
        sharedProperties.httpReq(options)
        //var results = {"monthly_earnings":[{"early_earnings":4738,"late_earnings":9632,"month":"1","total_earnings":14370,"year":"2016"},{"early_earnings":4427,"late_earnings":9018,"month":"2","total_earnings":13445,"year":"2016"},{"early_earnings":4549,"late_earnings":9339,"month":"3","total_earnings":13888,"year":"2016"},{"early_earnings":4687,"late_earnings":9528,"month":"4","total_earnings":14215,"year":"2016"},{"early_earnings":4716,"late_earnings":9585,"month":"5","total_earnings":14301,"year":"2016"},{"early_earnings":4573,"late_earnings":9328,"month":"6","total_earnings":13901,"year":"2016"},{"early_earnings":4733,"late_earnings":9638,"month":"7","total_earnings":14371,"year":"2016"},{"early_earnings":4711,"late_earnings":9610,"month":"8","total_earnings":14321,"year":"2016"},{"early_earnings":4760,"late_earnings":9592,"month":"9","total_earnings":14352,"year":"2016"},{"early_earnings":4550,"late_earnings":9202,"month":"10","total_earnings":13752,"year":"2016"},{"early_earnings":4401,"late_earnings":8862,"month":"11","total_earnings":13263,"year":"2016"},{"early_earnings":4701,"late_earnings":9621,"month":"12","total_earnings":14322,"year":"2016"}]}
        //sharedProperties.setProperty('monthlyEarnings',results)
    };

    this.getEarnings = function(){
        return sharedProperties.getProperty('monthlyEarnings').monthly_earnings;
    }

    this.getDailyAverages = function(){
        return sharedProperties.getProperty('dailyAverages').dailyAverages;
    };

    this.setData = function(inputArr,shiftType){
        var tempData = [];
      
        //var averageArr = this.getDailyAverages();
        var averageArr = inputArr;
        console.log(averageArr)

        var prop = inputArr[0]['avg_early_earnings']? 'avg_'+shiftType + '_earnings': shiftType + '_earnings'
        //console.log(prop)
        averageArr.forEach(function(elem){

            tempData.push(elem[prop]);
        })
        console.log(tempData)

        //this.data = tempData;
        return tempData;
    };

    this.setLabels = function(inputArr){
        var tempLabels = [];

        var averageArr = inputArr;
        console.log(averageArr)
        
        var monthMap = this.monthMap
        var dayMap = this.dayMap
        

        averageArr.forEach(function(elem){
            var dayOrMonth = elem.dayOfWeek? dayMap[elem.dayOfWeek] :monthMap[elem.month]
            //console.log(dayOrMonth)
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

    this.series = []

    this.data = []
    
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