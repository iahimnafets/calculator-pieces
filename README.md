The TASK:

This is a new mircoservice, this would not normally be a mircoservce, it is a test of
people's coding rather than the business requirements.
Issue
The business wants a mircoservice that can assist them in figuring out the optimal
change to return to a customer
Input
Must be in pence, so 50 pounds is 5000 pence (Example: 50122), with an ID for the call
(Example: "UUID_1"). The URLs used will be appropriate to the call/actions


Output:

JSON responses that shows the following:
{
"penceSubmitted": 50122,
"externalID" : "UUID_1",
"pounds": {
"50": 10,
"20": 0,
"10": 0,
"5": 0,
"2": 0,
"1": 1
},
"pence": {
"50": 0,
"20": 1,
"10": 0,
"5": 0,
"2": 1,
"1": 0
},
"dateTime": "2022-04-30T10:56:59.1381145"
}


Solution: Please use Springboot:
Record the calls into a data store (what was asked for, and the response
provided) 
You may choice the appropriate 
HTTP method to call the server, and the URLs
that it is called on. 
Must have testing 
Other parts of the processing/storing 
of data is up to you, but see the
requirements below
