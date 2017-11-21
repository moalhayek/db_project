app.controller('bartenders',function(sharedProperties){

    this.labels = ['Bartender','Mon','Tue','Wed','Thu','Fri','Sat','Sun']
    this.series = []
    this.data = [
        [65, 59, 80, 81, 56]
    ];

    this.setBartenders = function(barID){
        //REST endpoint with whichBar as param
        var options = {
            url: 'bartenders/getBartenders',
            params: {
                barId: barID,
            },
            method: 'GET',
            destination: 'dailyAverages'
        }

        var promise = sharedProperties.httpReq(options);

        promise.then(function(res){
            sharedProperties.setProperty('bartenders',res.bartenders)
            this.setData('total');
        }.bind(this));
    };

    this.data =[];

    this.setData = function(timeType){
        var allData = sharedProperties.getProperty('bartenders');
        console.log(allData)
        var tempData = []
        var sales_name= timeType+'_avgs'
        console.log(sales_name)
        console.log(allData[0])
        console.log(allData[0][sales_name])
        allData.forEach(function(elem){
            var name = elem.name;
            var sales = elem['total_avgs'];
            var dataSet = [name,sales[0],sales[1],sales[2],sales[3],sales[4],sales[5],sales[6]]
            tempData.push(dataSet);
        });
        this.data = tempData
    }

});