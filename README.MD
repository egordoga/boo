##              Description of "Booking" project

####        This project is a REST application for interaction with the hotel site

Capabilities:

 * View list of available rooms for specified dates -------- /room/free
 * View rooms filtered by category  -------- /room/category
 * Create user -------- /user/add
 * User can book the room for specified days -------- /user/booking
 * User can view his booking -------- /user/user_booking
 * User can get the total price of the booking -------- /booking/cost
 * View all bookings for the hotel -------- /booking/all_bookings
 
 All dates of application have the format d-MM-yy
 
 The file resources/static/script.curl has a 'CopyPast' samples for testing the work of application