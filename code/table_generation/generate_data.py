from faker import Faker
from random import *
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
  return fake.city()

def generate_salary(age):
  if (age < 25){
    mean = 55000
  }
  else if (age < 35){
    mean = 75000
  }
  else{
    mean = 95000
  }
  return str(abs(int(gauss(mean,35000))))

def generate_spending_per_night(salary):
  if (salary < 60000){
    mean = 25
  }
  else if (salary < 130000){
    mean = 50
  }
  else{
    mean = 100
  }
  return str(abs(int(gauss(mean,500))))

def generate_crowding_pref(age):
  crowd_types = ['Not crowded','Moderately crowded','Very crowded']
  p = []
  if (age < 30){
    p = [.33, .33, .34]
  }
  else if (age<45){
    p = [.5, .3, .2]
  }
  else{
    p = [.7, .2, .1]
  }
  return choice(crowd_types, p)

def generate_relationship_status():
  relationship_types = ['Single','In a relationship']
  return choice(relationship_types)
 
#first we establish what our attributes are
drinker_attributes = ["name","age","gender","street_address","city","salary","spending_per_night","crowding_pref","relationship_status"] 

#how many?
amount = 15000

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
    sal = generate_salary(age)
    tuple_list.append(generate_name(random_profile))
    tuple_list.append(age)
    tuple_list.append(generate_gender(random_profile))
    tuple_list.append(generate_street_address())
    tuple_list.append(generate_city())
    tuple_list.append(sal)
    tuple_list.append(generate_spending_per_night(sal))
    tuple_list.append(generate_crowding_pref(age))
    tuple_list.append(generate_relationship_status())

  #format as comma_delimited string 
    csv_string = ','.join(tuple_list)
    csv_string += '\n'
    outputfile.write(csv_string)        