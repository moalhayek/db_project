import datetime
import random
from faker import Faker
import numpy as np
from math import floor

fake = Faker()

def daterange(start_date,end_date):
    if start_date <= end_date:
        for n in range((end_date-start_date).days + 1):
            yield start_date + datetime.timedelta(n)
    else:
        for n in range((start_date-end_date).days + 1):
            yield start_date - datetime.timedelta(n)

inputfile_name = 'sells_table.csv'
inputfile = open(inputfile_name,'r')

beer_dict = {}
lines = inputfile.readlines()

curr_bar = lines[1].strip().split(',')[0]
beer_dict[curr_bar] = {}
for line in lines[1:]:
    beer_info_list = line.strip().split(',')

    newbar = beer_info_list[0]
    curr_beer = beer_info_list[1]
    price = beer_info_list[3]

    if newbar != curr_bar:
        curr_bar = newbar
        beer_dict[curr_bar] = {}

    beer_dict[curr_bar][curr_beer] = price

inputfile.close()

outputfile_name = 'transactions_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = [
    'bar_id',
    'employee_id',
    'date_of_sale',
    'day_of_week',
    'shift_type',
    'beer_id',
    'sale_price',
]

non_busy_day_list = ['sun','mon','tues','wed']
busy_day_list = ['thu','fri','sat']

day_dict = {
    0: 'mon',
    1: 'tue',
    2: 'wed',
    3: 'thu',
    4: 'fri',
    5: 'sat',
    6: 'sun',   
}

csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)

drinker_list = range(1,10000)
random.shuffle(drinker_list)

#each iteration refers to a person

bar_iter = range(1,201)

shift_list = attr_list[3:17]

start = datetime.date( year=2016, month =1,day=1)
end = datetime.date(year = 2017,month =11, day = 18)

p_early_sale = 0.35
p_late_sale = 1-p_early_sale

for bar_id in bar_iter:
    bar_str = str(bar_id)
    print 'generating sales for bar_id=' + bar_str

    bartender_amt = random.randint(5,15)
    bartender_list = drinker_list
    # number of bartenders this bar employs
    bartender_list = drinker_list[-bartender_amt:] #select freq_amt bars from bar id list
    bartender_p = np.random.dirichlet(np.ones(bartender_amt),size=1)[0]

    del drinker_list[-bartender_amt:]

    beer_options = beer_dict[bar_str]
    #print beer_options.keys()

    for date in daterange(start,end):
        day_of_week = day_dict[date.weekday()]
        date_str = date.isoformat()        

        #I generated these beer sales numbers based off what i found online
        #this code is 200bars*~1300sales*~700 days = 182mil iterations...might take a while lol
        if day_of_week in busy_day_list:
            #mean = 650
            mean = 100
        else:
            #mean = 400
            mean = 60

        sigma = mean/10
        #need to figure out how many sales in a day
        sales_amt = int(random.gauss(mean,sigma))

        early_sale_amt = floor(sales_amt*0.35)
        for sale in range(1,sales_amt+1):
            #print bartender_list
            #print bartender_p
            bartender = np.random.choice(bartender_list,p=bartender_p)

            if sale<early_sale_amt:
                shift = 'early'
            else:
                shift = 'late'

            #shift = np.random.choice(['early','late'], p=[p_early_sale,p_late_sale])

            beer = random.sample(beer_options.keys(),1)[0]

            sales_price = beer_options[beer]
                      
            csv_l = [bar_str,str(bartender),date_str,day_of_week,shift,beer,sales_price]
            csv_str = '{0},{1},{2},{3},{4},{5},{6}\n'.format(csv_l[0],csv_l[1],csv_l[2],csv_l[3],csv_l[4],csv_l[5],csv_l[6])
            outputfile.write(csv_str)

outputfile.close()
