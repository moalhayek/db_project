from random import *

outputfile_name = 'listens_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = ['drinker_id','music_id']
csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)

drinker_amt = 10000
#each iteration refers to a person

genre_list = range(0,25)

for person in range(1,drinker_amt):
    listen_amt = randint(1,4) # number of genres this person listens to (4 being the most)
    listen_list = sample(genre_list,listen_amt) #select listen_amt music from genre list
    
    person_str = str(person)
    for genre in listen_list:
        csv_str = '{0},{1}\n'.format(person_str,genre)
        outputfile.write(csv_str)

outputfile.close()



