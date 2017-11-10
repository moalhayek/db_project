import random
from faker import Faker

fake = Faker()

outputfile_name = 'bartenders_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = ['bar_id',
            'drinker_id',
            'start_date',
            'mon_early_avg_sales',
            'mon_late_avg_sales',
            'tues_early_avg_sales',
            'tues_late_avg_sales',
            'wed_early_avg_sales',
            'wed_late_avg_sales',
            'thurs_early_avg_sales',
            'thurs_late_avg_sales',
            'fri_early_avg_sales',
            'fri_late_avg_sales',
            'sat_early_avg_sales',
            'sat_late_avg_sales',
            'sun_early_avg_sales',
            'sun_late_avg_sales']

busy_day_list = ['thurs','fri','sat']


csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)

drinker_list = range(1,10000)
random.shuffle(drinker_list)

#each iteration refers to a person

bar_iter = range(1,201)

shift_list = attr_list[3:17]
for bar_id in bar_iter:
    bartender_amt = random.randint(5,15)
     # number of bartenders this bar employs
    bartender_list = drinker_list[-bartender_amt:] #select freq_amt bars from bar id list
    del drinker_list[-bartender_amt:]
    
    for bartender in bartender_list:
        shift_amt = random.randint(2,6)
        
        random_shifts = random.sample(shift_list,shift_amt)
        
        bartender_avg_dict = {}
        date_obj = fake.date_between(start_date="-5y",end_date="today")
        start_date = '{0}-{1}-{2}'.format(str(date_obj.year),str(date_obj.month),str(date_obj.day))

        for shift in random_shifts:
            shift_split_list = shift.split('_')
            day_of_week = shift_split_list[0]
            
            time_of_day = shift_split_list[1]
            
            if day_of_week in busy_day_list:
                mean = 300
            else:
                mean = 200
            if time_of_day == 'late':
                mean = mean*1.3
            sigma = mean/10

            bartender_avg_dict[shift] = str(int(random.gauss(mean,sigma)))

        for shift in shift_list: 
            if shift not in bartender_avg_dict:
                bartender_avg_dict[shift] = '\N' 

                      
        csv_str = '{0},{1},{2},'.format(str(bar_id),str(bartender),start_date)
        for (shift,sales) in bartender_avg_dict.iteritems():
            csv_str += sales+','
        csv_str += '\n'
        outputfile.write(csv_str)

outputfile.close()
