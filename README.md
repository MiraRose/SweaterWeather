#To Run
1. Make sure you have gradle installed
2. In root directory, `gradle run --args="filepath city state"` (Example: `gradle run --args="src/main/resources/sample_config.json Columbus OH"`)

#Thoughts
##Assumptions
- The user is in the US
- The user will want something waterproof when it's raining or snowing

##Stumbling Blocks
###Java
I chose Java because it's the language I've spent the most time in, but Java was not the ideal language for this project in retrospect. It added an extra level of unnecessary complexity to almost everything, from API calls to working with JSON to reading files. Plus I had to do some prodding to get things like tests and such using the right version.

##Future Improvements
- More Tests
- Tests that have mocks instead of calling the API directly (and then having another way of keeping tabs on the third party such as a monitoring system)
- An interactive CLI
- Using my json parsing library more effectively, maybe even an object to encapsulate the json that comes back from the weather api (although I'm not sure it'd be good to be locked-in in case they change something)
- Input error parsing
- There's quite a few runtime exceptions that should be handled in a more user friendly way
- Even better handling for the api key