# github_hilt
Android application using Gihub API


### Functionality

 - List all kotlin repositories
 - List all pr`s by repository
 - Show webview with a public relations page
    
### Project Architecture

The project follows the concept of Android architecture components and Clean architecture:

! [Image of Android architecture components] (https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

! [Clean architecture image] (https://res.cloudinary.com/practicaldev/image/fetch/s--T7GIdw6s--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_8uto/https://miro.medium.com / max / 1488/1% 2AD1EvAeK74Gry46JMZM4oOQ.png)

### Technologies
 - Corrotinas (FLOW)
 - Retrofit
 - Dagger2
 - mockito
 - ROOM (not implemented)
 
### API
https://api.github.com/search/repositories
https://api.github.com/repos/<criador>/<repositório>/pulls

### Tests
 - All ViewModels
 - All Repositories
 
### Improvements
 - ~tests~
 - Create interface tests
 - Improve UI
 - ~Add asynchronous call to get user name~
 - Create local database 
 - Add HILT
