# ConnectHub

***

## Overview
ConnectHub is a forum for meaningful discussions, offering easy post management and personalized feeds based on your interests.

This is a project for the course CSC207 at the University of Toronto. It aims to helps connect students together to discuss various topics, such as Java.

## Table of Contents
1. [Description](#description)
2. [Features](#features)
3. [Using the web version](#getting-started)
4. [Using the app locally](#dependencies)
    * [Installing](#installing)
    * [Running the Swing app](#running-the-swing-app)
    * [Running the web app](#running-the-web-app)
5. [Usage](#usage)
6. [Feedback](#feedback)
7. [Contributors](#contributors)
8. [Contributing](#contributing)
9. [License](#license)


## Description
ConnectHub is a discussion forum where users can engage in meaningful conversations. You can sign up and create your account to become part of the community. Once you're in, you can easily create, edit, and delete your own posts. To make your experience even better, ConnectHub lets you customize your feed with category filters, so you can focus on posts that match your interests.​

This project is a hybrid of a Swing app and a React app with SpringBoot as the backend. You can choose to run either versions of the app.


## Features
- [x] Create posts online
- [x] Manage your posts
- [x] View posts created by others
- [x] Share your or others' posts

Roadmap for future:
* Interactions with posts such as comments or favourite them.

Note that post filtering and deletion features is not available on the web version.

## Getting Started
***
If you want to use the web version, you don't need to install anything - you can navigate here to get started: https://connecthub-i5rz.onrender.com/

Note that it may take a while for the website to load - the server shuts down after periods of inactivity and needs to restart.

Because this is a hybrid of a web and a swing app, and since the swing app was designed to be used locally by one person, the server will only keep track of the newest person that has logged into the program.

***Warning:*** the current webite is a beta version and does not have a secure authentication. Do not use any personal information.


### Dependencies
If you are downloading the app locally, you will need the following requirements:
* One of the operating systems below:
    * Windows
    * MacOS
* [JDK 8 or above](https://www.oracle.com/ca-en/java/technologies/downloads/) (JDK 17 recommended)
* If running the web version
    * [Apache Maven](https://maven.apache.org/download.cgi) v3.9.9
    * [Node.js](https://nodejs.org/en/download/package-manager) v20.17.0+ (v20.17.0 recommended)
    <!-- * [Docker](https://docker.com/products/docker-desktop/) (optional - see more [details](#running-the-web-app-with-docker)) -->


### Installing
Clone this repository in a directory you like and step into it:
```
$ git clone https://github.com/csc207group76/connecthub.git
$ cd connecthub/
```

Create a file named `.env` in the project's root directory and paste in the following:
```
# you dont have to paste the comments but common man i spent so much time
# dealing with enviornment variables and now its exposed like this
# whatever man 😒

MONGO_DB_CONNECTION_STRING=mongodb+srv://connecthub:csc207@connecthub.rdlr3.mongodb.net/?retryWrites=true&w=majority&appName=ConnectHub
REACT_APP_DEV_API_URL=http://localhost:8080/api/v1
WEB_FRONT_END_URL=https://connecthub-i5rz.onrender.com/api/v1 # this line is not strictly neccessary since we're on localhost
```


### Executing the program

#### Running the Swing App
- In your favourite IDE, load the `pom.xml` file by right clicking on it
- Navigate to `src/main/java/app/ConnectHub.java` in the source code and execute the script
- A sign up or login window will pop up, you can start using the app
- To exit the app, simply close the window

#### Running the Web App
- Open up a terminal and run the following commands
```
$ npm install       # install front end dependencies
$ npm run watch     # builds and updates static assets, keep this running
```
- Open up another terminal and run the following commands
```
$ mvn install                # install back end dependencies and packages app
$ java -jar target/*.jar     # starts the backend server
```
- Navigate to `https://localhost:8080/signup` to sign up/login, or
- Navigate to `https://localhost:8080` to use the app anonymously
- To exit the program after use, press `ctrl + C` in both terminals to terminate watching the front end and the server


<!-- Doesn't work atm, to be updated later

#### Running the Web App with Docker
If you are using Docker, you won't need to install anything else mentioned previously. We will use Docker desktop.

- Run the command:
```
# create a docker image
$ docker build -t <your-image-name> .
```
- In Docker desktop, find the image with the tag name you built and run it - in the modal that pops up:
    * Set port to `8080`
    * Set the enviornmnet variables from your .env file
- Navigate to `https://localhost:8080/signup` or `https://localhost:8080`
- To exit the program, you can stop the container by
    * Going to `images`
    * Locate the image that you ran
    * In `Actions`, click on "Show image actions" -> "View container usage"
    * Stop the container -->


## Usage
After you successfully ran the application (either swing or web), you can either sign up or log in. Once in the home page, you can see a list of posts that are posted by others. Click on them to see the posts individually.

You can also find a button to create post. The new post created will be updated in the home page. Additionally on the home page, you can filter posts by clicking on the topics on the left.

You can also delete the posts you created by going to your post and clicking the delete button.


## Feedback
For any suggestions or bugs, please submit on GitHub issues.


## Contributors
| GitHub username | GitHub account |
| --------------- | -------------- |
| cedric543 | https://github.com/cedric543 |
| ethanliu24 | https://github.com/ethanliu24 |
| ggoldsworthy | https://github.com/ggoldsworthy |
| izabellemarianne | https://github.com/izabellemarianne |
| Karththigan | https://github.com/Karththigan |
| LL333111 | https://github.com/LL333111 |


## Contributing
This project will not be actively developed and monitored, however, if you want to contribute, please
1. Fork the repository
2. Make your changes on a feature branch and sumbit a PR
3. Clean up your code to the style checker `mystyle.xml`
4. Keep PR titles and descriptions informative
5. Only keep changes related to your PR


## License
This project is licensed under the MIT License - see the LICENSE.md file for details
