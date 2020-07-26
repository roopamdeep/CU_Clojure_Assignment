# CU_Clojure_Assignment
Sales Order Apllication using Functional Programming


*** Sales Menu ***
------------------
1. Display Customer Table
2. Display Product Table
3. Display Sales Table
4. Total Sales for Customer
5. Total Count for Product
6. Exit
Enter an option?
string. All text is case-sensitive so “John” and “john” are different people. Finally, there are no
duplicate records/customers.
prod.txt: This is the data for the product table. The schema is
<prodID, itemDescription, unitCost>
An example of the prod.txt disk file might be:
1|shoes|14.96
2|milk|1.98
3|jam|2.99
4|gum|1.25
5|eggs|2.98
6|jacket|42.99
Again, the data is valid – no duplicates and text is case sensitive.
sales.txt: This is the data for the main sales table. The schema is
<salesID, custID, prodID, itemCount>
An example of the sales.txt disk file might be:
1|1|1|3
2|2|2|3
3|2|1|1
4|3|3|4
The first record (salesID 1), for example, indicates that John Smith (customer 1) bought 3 pairs of
shoes (product 1). Again, you can assume that all of the values in the file (e.g., custID, prodID) are
valid.
So now you have to do something with your data. You will provide the following menu to allow the
user to perform actions on the data:
*** Sales Menu ***
------------------
1. Display Customer Table
2. Display Product Table
3. Display Sales Table
4. Total Sales for Customer
5. Total Count for Product
6. Exit
Enter an option?
The options will work as follows
1. You will display the contents of the Customer table. The output should be similar to the
following:
1: ["John Smith" "123 Here Street" "456-4567"]
2: ["Sue Jones" "43 Rose Court Street" "345-7867"]
3: ["Fan Yuhong" "165 Happy Lane" "345-4533"]
Note that exact formatting does not matter. You can use commas as separators or round
brackets instead of square brackets. The important thing is that each record lists the ID,
followed by the data associated with the ID. Records should be sorted by ID.
Note that the records are NOT guaranteed to be sorted in the data file (as they are in the
illustration above). In addition, ID numbers are not guaranteed to be consecutive numbers
(e.g., the IDs could be 7, 3, 2, 9, 14)
2. Same thing for the prod table – it will be sorted by Product ID (again, the data file may not
be sorted)
3. The sales table is a little different. ID values aren’t very useful for viewing purposes, so the
custID should be replaced by the customer name and the prodID by the product description,
as follows:
1: ["John Smith" "shoes" "3"]
2: ["Sue Jones" "milk" "3"]
3: ["Sue Jones" "shoes" "1"]
4: ["Fan Yuhong" "jam" "4"]
Again, the list should be sorted by Sales ID (the data file may not be sorted)
4. For option 4, you will prompt the user for a customer name. You will then determine the
total value of the purchases for this customer. So for Sue Jones you would display a result
like:
Sue Jones: $20.90
This represents 1 pair of shoes and 3 cartons of milk (in our simple example).
5. Here, we do the same thing, except we are calculating the sales count for a given product. So,
for shoes, we might have:
Shoes: 4
This represents three pairs for John Smith and one for Sue Jones.
Note that if a given customer or product does not exist when using menu option 4 or 5, an
appropriate response is given (either a "cust/prod not found" message or total sales/count
= 0 )
6. Finally, if the Exit option is entered the program will terminate with a “Good Bye” message.
Otherwise, the menu will be displayed again.
