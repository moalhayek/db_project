from random import *

outputfile_name = 'likes_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = ['drinker_id','beer_id']
csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)

drinker_amt = 10000
#each iteration refers to a person

beer_list = range(0,100)

for person in range(1,drinker_amt):
    like_amt = randint(0,4) # number of beers this person likes (4 being the most)
    like_list = sample(beer_list,like_amt) #select like_amt beers from beer list
    
    person_str = str(person)
    for beer in like_list:
        csv_str = '{0},{1}\n'.format(person_str,beer)
        outputfile.write(csv_str)

outputfile.close()



