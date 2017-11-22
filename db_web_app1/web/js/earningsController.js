var app = angular.module('db-project');

//this will be controller for personal earnings
app.controller("earnings", function (sharedProperties,$timeout,$q) {

    this.startDate = '2016-01'
    this.endDate = '2016-12'

    this.seriesMap = {
        early: 'Early Sales',
        late: 'Late Sales',
        total: 'Total Sales',
    }

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

        var options = {
            url: 'transactions/getDailyAverages',
            params: {
                barId: barID,
                startDate: this.startDate,
                endDate: this.endDate,
            },
            method: 'GET',
            destination: 'dailyAverages'
        }
        
        var options2 = {
            url: 'transactions/getEarnings',
            params: {
                barId: barID,
                startDate: this.startDate,
                endDate: this.endDate,
            },
            method: 'GET',
            destination: 'monthlyEarnings'
        }

        var promises = [];
        
        console.log('about to add http reqs to promise arr')
        promises.push(sharedProperties.httpReq(options))
        promises.push(sharedProperties.httpReq(options2))

        $q.all(promises).then(function(res) {
            sharedProperties.setProperty('dailyAverages',res[0])
            sharedProperties.setProperty('monthlyEarnings',res[1])
            console.log('trying to refresh now')
            this.refresh(shiftType, false)
        }.bind(this));
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
        if(!checked){
            this.addData(whichOne);
        }else{
            this.removeData(whichOne);
        }
    }

    this.addData = function(whichOne){
        this.series.push(seriesMap.whichOne)

        var dailyAveArr = this.getDailyAverages()
        var monthlyArr = this.getEarnings()

        var new_data_daily = this.setData(dailyAveArr,whichOne);
        var new_data_monthly = this.setData(monthlyArr,whichOne);

        this.data_daily.push(new_data_daily)
        this.data_monthly.push(new_data_monthly)
    }

    this.removeData = function(whichOne){
        var str = seriesMap.whichOne

        var index = this.series.indexOf(str)
        this.series.splice(index,1)
        this.data_daily.splice(index,1)
        this.data_monthly.splice(index,1)
    }

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