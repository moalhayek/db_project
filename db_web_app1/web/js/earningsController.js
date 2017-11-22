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

    this.instantiate = function(barID){

        this.data_daily = []
        this.data_monthly = []
        this.labels_monthly = []
        this.series = []

        var options = {
            url: 'transactions/getDailyAverages',
            params: {
                barId: barID,
                startDate: this.startDate,
                endDate: this.dateMap[this.endDate],
            },
            method: 'GET',
            destination: 'dailyAverages'
        }
        
        var options2 = {
            url: 'transactions/getEarnings',
            params: {
                barId: barID,
                startDate: this.startDate,
                endDate: this.dateMap[this.endDate],
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
            this.labels_monthly = this.setLabels(this.getEarnings())
            this.addData('total')

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

    this.dateMap = {
        '2016-01': '2016-02'
        ,'2016-02': '2016-03',
        '2016-03': '2016-04',
        '2016-04': '2016-05',
        '2016-05': '2016-06',
        '2016-06': '2016-07',
        '2016-07': '2016-08',
        '2016-08': '2016-09',
        '2016-09': '2016-10',
        '2016-10': '2016-11',
        '2016-11': '2016-12',
        '2016-12': '2017-01',
    }

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
        this.series.push(this.seriesMap.whichOne)

        var dailyAveArr = this.getDailyAverages()
        var monthlyArr = this.getEarnings()

        var new_data_daily = this.setData(dailyAveArr,whichOne);
        var new_data_monthly = this.setData(monthlyArr,whichOne);

        this.data_daily.push(new_data_daily)
        this.data_monthly.push(new_data_monthly)
    }

    this.removeData = function(whichOne){
        var str = this.seriesMap.whichOne

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

        var averageArr = inputArr;
        console.log(averageArr)

        var prop = inputArr[0]['avg_early_earnings']? 'avg_'+shiftType + '_earnings': shiftType + '_earnings'

        averageArr.forEach(function(elem){
            tempData.push(elem[prop]);
        })
        console.log(tempData)

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

    this.labels_daily = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat']
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