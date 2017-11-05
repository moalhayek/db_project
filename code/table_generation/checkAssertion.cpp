#include <iostream> 
#include <string> 

using namespace std; 

//checkAssertion class
class CheckAssertion{
	public:
		//function that checks the assertion of whether or not the drinker spends more than half their daily salary
		void CheckAssertion(int sal, int spen){
			//check to see if the assertion was violated
			int spendingLimit = sal/(365*2);
			string result = (spen >= spendingLimit) ? "REJECTED" : "ACCEPTED";

			//set and print out the result of the check
			setResult(result);
			cout << getResult() << endl;
			return;
		}
		//function that sets the result of the assertion check
		void setResult(string checkResult){
			result = checkResult;
		}
		//function that gets the result of the assertion check
		string getResult(){
			return result;
		}
	private:
		//the current validity of the assertion
		string result; 
};	
