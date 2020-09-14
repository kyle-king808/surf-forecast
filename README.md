# My Personal Project

## Surf Report Application


My application will be a surf report app. It will have a list of beaches and will return the surf conditions for each beach.
It will take in weather data such as swell direction, period, and height. Then depending on the direction the beach is facing it will report
what the local wave height and conditions will probably be. Eventually I'm planning for the app to report tides, weather, and
safety warnings such as jellyfish or reef warnings.    

Data to report for each beach:
- Wave height range
- Wave period
- Wind direction

This app will be used by any ocean-going person.

Prospective Users
- Surfers
- Sailors
- Swimmers
- Recreational beach goers

This project interests me because I *love* the ocean. Accurate data specific to the each beach is very difficult to come by
because of how the specific beach reacts to the swell and direction. I would also like to add a lot of useful information
that isn't on many websites, such as a jellyfish warning or notes on information specific to that beach. Finally this interests
me because hopefully I'll be able to have live data constantly come in and refresh the app, making it useful even after the project
ends.  



##User Stories

Stories
- As a user, I want to be able to create a new beach and add it to a list of beaches.
- As a user, I want to be able to see the wave height, period, and direction of a beach.
- As a user, I want to be able to be able to see the original statistics for the swell.
- As a user, I want to be able to be able to input the current surf weather conditions.
- As a user, I want to be able to save my list of beaches and their details.
- As a user, I want to be able to load my list of beaches and their details.

##Instructions for Grader

Instructions:
- You *must* "Update Current Swell Info" first whenever program is run, else error.
You can do this by inputting non-negative integer values into the 4 text boxes bellow "Update Current Swell Info"
 and then clicking "Update Current Swell Info".*(direction must = [0,360)*
- You will trigger the audio component, a song, by doing the previous step. The song only plays
the first time you "Update Current Swell Info".
- You can can generate the first required event by adding a Beach to a your list of Beaches.
 Under "Add New Beach" input the beach's name and direction [0,360). Then click "Add New Beach".
  The Beach will show up on your list to the right.
- You can generate the second required event by selecting with your mouse a beach on the list, and then 
clicking "Delete Beach" bellow the display panel in the middle.
- You can save the state of my application by clicking "Save Beaches" in the right panel. This
saves the list of beaches (not the swell info). 
- You can reload the state of my application by closing the application, re-opening it,
*Updating Current Swell Info*  first, and then clicking "Load Beaches" from the right panel.
Important to note that you must *Update Current Swell Info* before reloading beaches.  

Tips
- Beaches will only display having waves if their direction is within 90 degrees of the swell direction.


##Phase 4 Task 2

- The class that has a robust design, throwing a checked exception, is SurfForecast.
- Method *updateSwell()* will throw a *HeightWrongOrderException()* if the parameter oneThirdSwellHeight
is larger than oneTenthSwellHeight before anything else is executed in the method.
- The *updateSwell()* method is tested in SurfForecastTest class.
- The SurfReportAppGui method *updateSwellPaneHelper()* deals with the exception,
switching the order of heights, and re-running *updateSwellForecast()*. 

##Phase 4 Task 3
- (1) My SurfForecast class violated the single responsibility principle, because it organized
the list of beaches, updated them, updated the swell, and also had the fields to the swell data. The role
of the SurfForecast is to organize the beaches, the swell, and update them accordingly. It should not
have individual fields for the Swell weather info.
- (1) To fix this I created another class, Swell, to store the weather data, and 
SurfForecast has just one field now, swell, to hold all weather data and can update the weather still.
