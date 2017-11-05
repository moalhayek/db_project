from faker import Faker
from random import *
import numpy as np
import math

fake = Faker()

def generate_name(prof):
  return prof['name']

def generate_age():
  return str(randint(21,60))

def generate_gender(prof):
  return prof['sex']

def generate_street_address():
  return fake.street_address()

def generate_city():
  return fake.state_abbr()

def generate_salary(age):

  if (age < 25):
    mean = 55000
    sigma = 10000
  elif (age < 35):
    mean = 75000
    sigma = 15000
  else:
    mean = 95000
    sigma = 40000
  
  return str(abs(int(gauss(mean,sigma))))

def generate_spending_per_night(salary):

  while (True):
    if (salary < 60000):
      mean = 20
      sigma = 8
    elif (salary < 130000):
      mean = 50
      sigma = 20
    else:
      mean = 150
      sigma = 50

    spn = abs(int(gauss(mean,sigma)))
    if (spn < (salary/365)/2):
      break

  return str(spn)

def generate_crowding_pref(age):
  crowd_types = ['Not crowded','Moderately crowded','Very crowded']
  prob = []

  if (age<30):
    prob = [.33, .33, .34]
  elif (age<45):
    prob = [.5, .3, .2]
  else:
    prob = [.7, .2, .1]

  return np.random.choice(crowd_types, p=prob)

def generate_relationship_status():
  relationship_types = ['Single','In a relationship']
  return choice(relationship_types)
 
#first we establish what our attributes are
drinker_attributes = ["name","age","gender","street_address","city","salary","spending_per_night","crowding_pref","relationship_status"] 

#how many?
amount = 10000

outputfile_name = 'drinker_table.csv'
outputfile = open(outputfile_name,'w')

outputfile.write(','.join(drinker_attributes)+'\n')
#iterate for as many tuples as you need
for index in range(1,amount):
    tuple_list = []
    # generate random profile
    #iterate through all desired attributes
    random_profile = fake.profile()
    age = generate_age()
    sal = generate_salary(int(age))
    tuple_list.append(index)
    tuple_list.append(generate_name(random_profile))
    tuple_list.append(age)
    tuple_list.append(generate_gender(random_profile))
    tuple_list.append(generate_street_address())
    tuple_list.append(generate_city())
    tuple_list.append(sal)
    tuple_list.append(generate_spending_per_night(int(sal)))
    tuple_list.append(generate_crowding_pref(int(age)))
    tuple_list.append(generate_relationship_status())

  #format as comma_delimited string 
    csv_string = ','.join(tuple_list)
    csv_string += '\n'
    outputfile.write(csv_string) 

       
