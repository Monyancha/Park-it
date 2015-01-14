# Park-it
Driving in the heart of Toronto is a great experience. Food is great and the attractions are great! What's not great is when you need to find parking. What if you could just have a list of the closest parking lots with the price rate, the size of the lot and the address? Park-it does it all for you, all on the go.
Park-it is an Android application that uses Toronto Open Data and Google Maps to point you to the closest parking lot wherever you are (preferably in Toronto).

![Parking List](screenshots/ParkingListActivity.png)
![Parking Activity](screenshots/ShowParkingActivity.png)

# About
This application was my way of teaching myself various aspects of Android in a fun way. I went to a Startup Hackathon a few months ago, and the focus of it was to use Open Data. I didn't win, but it was a great experience and I took a lot away from it. Since then, I really wanted to make something that could make my life slightly easier while utilizing the power of Open Data and the popularity of the Android Framework.


# How does it work?
Park-it uses "Green P Parking" taken from the Toronto Open Data resources online. It takes your current location and calculates the distance between you and the closest parking lots around. Because it is Toronto Open Data, this app really only works in Toronto.

#Lets get into more detail
Here's a breakdown on how everything works

## ParkingListActivity.java
Here is the first place where the user goes in Park-it. This activity contains a subclass implementing an `AsyncTask` that will send an HTTP GET request. Once the results come back, it will be placed into a `JSONObject` object. 

## The JSON Object
The JSONObject returned contains the `Latitude` and `Longitude` coordinates, which make it easy to calculate the distance from your current location to the Parking lot itself. The distance is calculated using the `Location.distanceTo` function provided by Google.

## The Array Adapter
Next, the parsed JSON is added to `ParkingListArrayAdapter` (which implements an `ArrayAdapter` class) where it takes the desired values and displays each `TextView` in the `ListView`.



