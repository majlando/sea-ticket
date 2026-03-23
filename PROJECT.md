# Project - Event Tickets EASV Bar
Many companies and organizations are organizing events. This is often a complicated task as it involves a lot of management of resources, such as time, places and people.
This assignment is an example of a simple event management system, for the EASV bar, where you will automate only some of the major tasks.
## Functional Requirements
### Users
There are 3 types of users in the system. Admin and Event Coordinator log in using a username and password.
- **Admin**: Can create and manage all other users. Can delete events and assign coordinators. Cannot create and manage events.
- **Event Coordinator**: Can create, delete, and manage events and add tickets to events pertaining to that coordinator (set by the admin). Can assign other coordinators to have access.
- **Customer**: End customer. Gets the printed tickets on paper. Does not have access to the system.
## Events
Events can be anything, like a dog show, EASV party, Wine tasting, etc. However, all events have some minimum information and optional information:
### Required Information
- **Time**: Start date and time.
- **Location**: Where the event is located, could be a real address, URL, coordinates, etc.
- **Notes**: Extra information about the event.
### Optional Information (must be supported but may not be used for all events)
- End date and time
- Location guidance: How to get to the event, e.g., by car.
### Printed Tickets and Admission
- The event coordinators must be able to print tickets for the customer. Each ticket must be connected to the name and email of the customer who bought the ticket. One customer can buy multiple tickets.
- The event coordinator either prints the ticket for the customer or sends the ticket to the customer by email. The ticket can be, e.g., a PDF or an image.
- The customer buys the ticket directly from the event coordinator in real life (IRL). Payment is not part of the system. An event coordinator hands out tickets after being paid.
### There are multiple types of tickets
For participants, customizable. e.g. VIP tickets, Food included tickets, 1st row, free beer, etc. These types/categories of tickets are not fixed but can be customized for each event.
Tickets must have the following information:
- QR code (2D barcode) and corresponding 1D barcode, this code must resolve to a unique system-generated ID that cannot be guessed. Avoid using a sequence of numbers or anything else that can be guessed. You might use UUID or similar technology.
- Event information (see above), both required and optional, except the participant data.
Special free or discounted food/drinks tickets
You must be able to print separate free tickets for an event, such as "one free beer", "50% off one drink", "1 set of free earplugs", etc. This ticket must contain its own valid QR/barcode for one-time use. These tickets are not connected to any specific customers, but can optionally be for a specific event, otherwise it is valid for all events.

