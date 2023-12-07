# This File contains the list of Issue faced in this Project

## SavePost API, and LikePost API

While hitting the SavePost API, API was suppose to return the saved Post info to confirm that we have saved that specific Post, but in this case the error was occurred due to Many-to-Many relation, between Post & User Entity, 
because All users can save All post(Many-to-Many), thus it was creating a situation of Recursion while fetching the data. 

### Temporary FIX : 

I used @JsonIgnore annotation as of now for the declaration of List <Post> savedPost in UserInfo.java modal Entity, which is ignoring the List data of Saved Post, and just returning the User detail excluding the SavedPost[] in the JSON.

	#### Check : 
	
	- Use PostMan with URL : http://localhost:8811/usercontrol/user/id/{userId}
	- It will return JSON having all the Key : value (except SavedPost Key)

