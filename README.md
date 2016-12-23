#Backend notes
##Restful services
	This web app is entirely restful, which required JSON data for all http requests.
	It also returns JSON data for all type of requests. 
	Complete API documentation is not given here but could be checked using the paths stated in the next section.

##Backend application packages

###Controllers
	The package consists of all servlet controllers handling all http requests coming from the user. Here are the list of api's available in each controllers
####CommentController
##### GET - /comments/{comment-Id}..
	Getting a comment with a specific id ..
##### POST - /comments..
	Adding new comment
##### DELETE - /comments/{comment-id}..
	Delete a comment with that specific comment Id
##### PUT - /comments/{comment-Id}
	Update a comment with the given Id..
	Create new comment if the Id is not found
	
#### Like controller
##### DELETE - /likes/{comment-id}
	Unlike a comment which was previously liked with that given Id
##### POST - /likes
	Creates a new like for a post
##### GET - /likes/{like-id}
	Get a like with a like id - which is not that meaningful to get a like though
#### User controller
##### GET - /users/{user-id}
	Get a user with specific user id
##### POST - /users
	Creates a new user with specific Id
##### PUT - /users/{user-id}
	Updates a new user, create a new one if the Id is not found
##### DELETE - /users/{user-id}
	Delete a user with a specific Id
#### PostPageController
##### GET - /postperpage/{post-type}
	Get the total number of posts per type - [DRIVE or RIDE]
#### PostLikeController
##### GET - /postlikes/{post-id}
	Get the number of likes for a post with id od post-id ..
	Response format..
	```json
	{"totallikes": 1}
	```
#### PostCountController
##### GET - /postcount
	Returns the total number of posts till date
#### PostCommentController
##### GET - /postcomments/{post-id}
####LoginController
##### POST - /login
	Handles login, authentication, session handling

### Customtags Package
#### CustomDate class
	Constructs a new tag for displaying the current date in th web page

### datasource package
#### DBConnection class
	Provides a single instance of database connection/ singliton design pattern
### model package
#### Comments
	A pojo class defining attributs and behaviour for handling post comments
#### Likes
	A pojo class defining attributes and behavior for handling post likes
#### Posts
	A pojo clas defining attributes and behaviour for handling posts
#### Users
	A pojo class definind attributes and behaviour for handling user profile

### model.mapping package
#### CommentMapper class
	Used to map incoming userid, postid and comment data from an incoming json data
	from frontend and map it to an object using gson (Google JSON to Object mapper)
#### LikePostMapper class
	Used to map incoming userid and postid from an incoming json data for adding a new like for the post
#### PostMapper class
	Used to map userid, comment text and post type from an incoming post id when a new post is added from frontend.
#### UserMapper class
	Used map to fullname, gender, state, city, street, zip, email and password when 
	a new user is being added. Note, this mapper is used as the createddate and updated date is taken from the server.
### Service package
#### ICommentService
	An interface containing the following methods..
	1. Comments addNewComment(Comments comment) - for adding new comment
	2. void deleteComment(int commentId) - for deleting a comment given a comment id
	3. Comments updateComment(Comments comment) - for updating a comment
	4. Comments findComment(int commentId) - for finding a comment given its id
	5. List<Comments> findCommentsByPostId(int postid) - for getting all comments of a post
	6. int getMaxId() - for getting the maximum Id given for a post
#### ILikeService
	An interface containing the following methods..
	1. Likes addNewLike(Likes like)
	2. void deleteLike(int likeId)
	3. List<Likes> findLikeByPostId(int postId)
	4. Likes findLikeById(int likeId)
	5. int getMaxId() - for getting the maximum id given for a like (ensure uniqueness)
	6. int findLikeByUserIdAndPostId(int userId, int postId)
#### ILoginService
	An interface containing the following methods..
	IsUserRegistered(String username, String password)
#### IPostService
	An interface containing the following methods..
	1. Posts addNewPost(Posts post)
	2. void deletePost(int postId)
	3. Posts updatePost(Posts post)
	4. Posts findPost(int postId)
	5. List<Posts>getRidePostPerPage(int pageSize)
	6. List<Posts>getDrivePostPerPage(int pageSize)
	7. int getMaxId() - getting maximum id given for a post(ensure uniqueness)
	8. int getNumberOfPosts()
#### IUserService
	An interface containing the following methods..
	1. Users addNewUser(Users user)
	2. deleteUser(int id)
	3. Users updateUser(Users user)
	4. Users findUser(int id)
	5. int getMaxId()
	6. Users findUserByUsername(String username)
### Service helper classes
#### CommentServiceHelper
	Comments getCommentFrommapper(CommentMapper mapper)	
		A static method for returning a comment object from the mapper 
#### LikeServiceHelper
	Likes getLikeFrommapper(LikePostMapper mapper)
		A static method for returning a like object from the mapper 
#### PostServiceHelper
	Posts getPostFrommapper(PostMapper mapper)
		A static method for returning a post object form the mapper
#### UserServiceHelper
	Users getUserFrommapper(UserMapper mapper) 
		A static method for returning a user object from UserMapper
### Service implementation classes
	Contains implementation of the interfaces stated above
### Utility classes
	Contains three classes
	CarPoolingMarshaller - Used to return a JSON data from a Java object
	CarPoolingUnMarshaller - Used to return Java object from JSON(not used in this project though - used gson of google instead)	
	DateToLocaDateUtil - Used to convert Java 8 dates to Java 7 date in preparedStatement of sql
		
## Application security
### Session and cookie management
	When new user is registed for the first time, they will be automatically logged in. 	The system will create a new session for this user. 
	The session data will expire in 15 minutes if user remain inactive. 
	When users logged in the next time, a session will be set for 15 days. 
	Up on checking the remember me option, they can avoid typing login information everytime they access the system.

	
      
