from random import *

outputfile_name = 'frequents_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = ['drinker_id','bar_id']
csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)

drinker_amt = 10000
#each iteration refers to a person

bar_list = range(1,201)

for person in range(1,drinker_amt):
    freq_amt = randint(0,4) # number of bars this person frequents
    freq_list = sample(bar_list,freq_amt) #select freq_amt bars from bar id list
    
    person_str = str(person)
    for bar in freq_list:
        csv_str = '{0},{1}\n'.format(person_str,bar)
        outputfile.write(csv_str)

outputfile.close()



