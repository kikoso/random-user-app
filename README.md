# Random User App for Android

Android repository for the App to display randomusers

## Getting Started

### Get Information

The project uses the API from [RandomUser](https://randomuser.me/?) to display a list of random users


### Get The Code

To clone the repository, run:
```
$ cd <your directory for source code>/
$ git clone git@github.com:kikoso/random-user-app.git
$ Open the root folder on Android Studio
```

This project aims to use the latest stable version of Android Studio (4.2.2 at 18.07.2021).

## Project Structure

The project is using MVVM with a Clean Code flavor. Some of the libraries used are:

- Clean with MVVM 
- Libraries
	- Retrofit, OkHttp for network
	- Hilt (DI)
	- Coroutines
	- AAC viewmodel
	- Navigation
	- ViewBinding
	- Picasso

## Further work

Due to time constraints, this project was finished in 4 hours and there was no time to work too much on details (for instance, the project does not paginate new items on the recycler view, and has just included a SwipeRefreshLayout. Some general comments in no particular order:

- The application stores the data in the DB and is able to fetch it for the User Details. For the user list, however, we are not supporting offline mode since all the users are randomly created, and this leads to more questions: should we display at the beginning the items in the DB, or coming from the API? What would happen when we refresh or scroll? Etc. However, when an item is being clicked on the list the result is fetched on an offline basis from the BD.
- There are unit test for the Users, but no UI test. With more time, Espress Tests could be created.
- The UX would have been done with Compose with more time
- There are currently two View Models, one for the User List and another for the User Details. They both share some functionality, so this could have been merged in a shared class (or extend it from a class with the common functionality).
- The app could use some modularisation, which right now feels like over engineering but for apps with wider scope should be considered.
- There is some error handling (in case the app has no connectivity or other problems happen), but this could be better tested and expanded.
- I would have liked to add Flow, but I focused on getting something done within the time limit.


## Making Changes

This project follows the [Gitflow Workflow](https://nvie.com/posts/a-successful-git-branching-model/).

The ```main``` branch stores the official release history and the ```develop``` branch is the integration branch for the next release.

When developing a new feature or fix that should go into the next release, you should create a new branch from ```develop``` using the ```feature/``` prefix. Give the branch a name that includes the Jira identifier and a short description:


```
$ git checkout develop
$ git checkout -b feature/RANDOMUSER-1-setup-initial-project-and-repo
``` 

When the work is completed, push the latest changes and create a [pull request](https://github.com/kikoso/random-user-app/pulls) to merge the changes into ```develop```. Once the PR has been approved, we prefer to squash merge the changes to keep the commit history clean. You can do this from the GitHub interface when merging the PR or manually:

```
$ git checkout develop
$ git merge --squash feature/RANDOMUSER-1-setup-initial-project-and-repo
```

The final commit message should start with a description of the Jira item followed by a description of what was done:

```
RANDOMUSER-1: Set up initial Android project and repo

- Update README with initial info on project. 
```
