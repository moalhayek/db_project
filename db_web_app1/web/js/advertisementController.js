var app = angular.module('db-project')

//controller for the general ad platform info
app.controller('advertisementController',function(sharedProperties){



    this.labels = ['Total Clicks','Total Cost','Cost Per Click']
    this.series = []
    this.data = [];

    this.getAds = function(){
        return sharedProperties.getProperty('adPlatforms')
    };

    this.setAds = function(barID){
        //REST endpoint with whichBar as param
        var options = {
            url: 'ads/getAdPurchases',
            params: {
                barId: barID,
            },
            method: 'GET',
            destination: 'adPurchase'
        };

        var promise = sharedProperties.httpReq(options);

        promise.then(function(res){
            sharedProperties.setProperty('adPlatforms',res.adPurchases)
            //console.log(sharedProperties.getProperty('adPurchases'))
        }.bind(this));
    };

    this.data =[];

    this.setData = function(platform){
        var tempData = [];
        //['Start Date','End Date','Total Clicks','Total Cost','Cost Per Click']
        var dataSet = [platform['totalClicks'], platform['totalCost'], platform['costPerClick']];
        tempData.push(dataSet);
        this.data = tempData
    };
    this.getAdEarnings = function(targetMonth){
        var arr = sharedProperties.getProperty('monthlyEarnings').monthly_earnings;
        var obj = {}
        arr.forEach(function(elem){
            if(elem.month == targetMonth){
                obj = elem;
            }
        });
        return obj.total_earnings;
    };
});