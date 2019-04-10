# Spring Boot RESTFul

<br>

1. 도메인 생성

```java

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    protected User(){}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

```

<br>

2. 컨트롤러 생성

```java

@RestController
@RequestMapping(value = "/users")
public class UserController {
    
}

```

<br>

3. 레파지토리 생성

```java

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
    User findByNameAndPassword(String name, String password);
}

``` 

<br>

4. 서비스 생성

```java
public interface UserService {
    User join(String name, String password);
    User authentication(String name, String password);
}
```

<br>

5. 서비스 구현 객체 생성

```java
@Service
public class UserServiceImpl  implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
       public User join(String username, String password) {
           User user = userRepository.findByName(username);
           if (username != null && password != null) {
               if (user != null) throw new AlreadyExistsException("존재 하는 이름");
               return userRepository.save(new User(username, password));
           } else {
               return null;
           }
       }

    @Override
    public User authentication(String name, String password) {
        User user = userRepository.findByName(name);
        if(user != null && user.getPassword().equals(password)){
             return user;
        }
        return null;
    }
}
```


<br>

6. 컨트롤러 생성

```java
package com.spring.restful.board.springrestboard.controller;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/join")
    public User create(@RequestBody User user){
        System.out.println(user);
        return userService.join(user.getName(),user.getPassword());
    }


    @GetMapping(value = "/login")
    public User login(@RequestParam("name") String name, @RequestParam("password") String password){ // 쿼리받기
        System.out.println("get : username"+name+" / password"+password);
        return userService.authentication(name,password);
    }
}

```