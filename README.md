# Job-search
## Introduction
This project aims to provide a personalized job search web service. It can recommend the jobs near your location based on your favorite jobs in records.

## Technologies
* Frontend: CSS, HTML, Javascript, AJAX
* Backend: Java servlets, MySQL, content-based recommendation, Amazon EC2, Github API (to request for job info), MonkeyLearn API (to extract keywords from job decriptions)

## Launch
* This service is deployed in AWS
* Launch VM and Database
* Connect to VM and run docker container
```
$ chmod 600 YOUR_PRIVATE_KEY_LOCATION
$ ssh -i YOUR_PRIVATE_KEY_LOCATION ubuntu@YOUR_INSTANCE_IP
$ sudo docker run -d -p 80:8080 jupiter
```
