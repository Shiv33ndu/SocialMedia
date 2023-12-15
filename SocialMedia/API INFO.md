# API INFORMATION

## USer Related API Services

```java

public RegisterUser(User user);

public findUserById(Integer userId);

public findUserByEmail(String email);

public followUser(Integer userId, Integer followUserId);

public searchUser(String query);

public updateUserDetail(User updatedUser, User existingUser);

public findUserProfileByJwt(String jwt);

public void updatePassword(User user, String newPassword);

public void sendPasswordResetEmail(User user);

```

```query
search user SELECT DISTINCT u FROM User u WHERE u.username LIKE %:query% OR u.email ; 
```


## Post Related APIs Services

```java

public Post createPost(Post post, Integer userId);

public String deletePost(Integer postId, Integer userId);

public List<Post> findPostByUserId(Integer userId);

public Post findPostById(Integer postId);

public List<Post> findAllPost(Integer userId);

public Post savedPost(Integer postId, Integer userId);

public Post likePost(Integer postId, Integer userId);

```