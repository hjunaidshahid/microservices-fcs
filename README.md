Create two Java microservices for a fictive coffee shop using Java framework and technology of your choice: 
1. Orders service
2. and Barista service. 
# ORDERS SERVICE
It shall maintain the customer orders for a coffee. 
The following requirements must be met:
1. An order must contain information about 
a. type of coffee drink  long black, latte, cappuccino, espresso
b. size of the ordered coffee  small, medium, large
c. milk type (if applicable)  cow milk / soy milk / almond milk
d. on-site / takeaway
e. price
f. order number
2. The orders have to be persisted using JPA.
3. The service must implement an external REST interface for
a. adding an order
b. canceling an order
c. listing of pending orders with their current status
4. An order, which is already in preparation, cannot be cancelled. 
5. The completed orders shall be forwarded to Barista service for processing.
6. The orders, finalized by Barista service, shall be removed from the order list.
# BARISTA SERVICE
This service is responsible for order processing. The order in the Barista service goes through the following states: 

waiting -> in preparation -> finished -> picked up

The implementation of the above mentioned preparation cycle could be done in a separate thread/task/timer. 

The order preparation state shall be persisted. A notification about finalized orders being picked up by the customer shall be sent back to the order service. 

The finalized orders shall be removed from the persistence storage.

# GENERAL REQUIREMENTS
1. The persistence shall be implemented with Hibernate and in-memory / embedded relational database, e.g. H2, Apache Derby
2. The internal communication between the microservices shall be implemented in form of REST web services
3. Implementation language: Java 8 or highe
