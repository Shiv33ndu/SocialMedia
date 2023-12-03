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