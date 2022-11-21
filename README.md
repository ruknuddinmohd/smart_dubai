# smart_dubai 


Below are the steps as an input on how to build image and run application:


## Step 1:

Clone the project from GitHub.

    1.“git clone https://github.com/ruknuddinmohd/smart_dubai.git”

## Step 2:

Docker commands to build and run the project.

    1.“docker build -t smartdubai .” 
To build the docker images

    2.“docker images”
This command is used to verify the image.

    3.“docker run -d -p 9091:9091 smartdubai”
This command is used to run docker image

## Step 3:

Following are API end points details.

For API's Customers:

1.API to get search book by id.

    HTTP Method: GET
    URL: http://localhost:9091/app/books/<BookId>
    Response: {
	"id": 50,
	"name": "Fiction Book",
	"description": "Fiction Book description",
	"author": "Author123",
	"type": "FICTION",
	"price": 50.00,
	"isbn": "ISBN123"}

2.API to get the list of Books from store/site.

    HTTP Method: GET
    URL: http://localhost:9091/app/books/
    Response : [
    {
        "id": 50,
        "name": "Fiction Book",
        "description": "Fiction Book description",
        "author": "Author123",
        "type": "FICTION",
        "price": 50.00,
        "isbn": "ISBN123"
    },
    {
        "id": 51,
        "name": "COMICS Book",
        "description": "Comic Book description",
        "author": "Author456",
        "type": "COMIC",
        "price": 60.00,
        "isbn": "ISBN4567"
    }]


3.API for Checkout Books with or without Promo code

Note : If promotion code given as "PROMO CODE",10% of discount on fiction book is applied and 0% on comics as per the requirement.Else,discount is not applicable on any category of books if no promo code applied.

    HTTP Method: POST
    URL: http://localhost:9091/app/books/checkout
    Payload : {
	"books": [{
			"id": 50,
			"name": "Fiction Book"

		},
		{
			"id": 51,
			"name": "COMICS Book"

		}
	],
	"promotionCode": "PROMO CODE"
    }
    Response: Total Price after discount : 105.0

For API's Admin:

1.API for adding Book to store

    HTTP Method: POST
    URL: http://localhost:9091/app/books/
    Payload: {
	"name": "Book1",
	"description": "Description 1",
	"author": "Author 1",
	"type": "FICTION",
	"price": "70",
	"isbn": "isbn number 1"
     }
    Response : Book has been saved

2.API for updating the existing Book details

    HTTP Method: PUT
    URL: http://localhost:9091/app/books/
    Payload: {
    "id": <BookId>>,
    "name": "Book1",
    "description": "Description 1",
    "author": "Author 1",
    "type": "FICTION",
    "price": "70",
    "isbn": "isbn number 1"
    }
    Response : Book has been updated

3.API for deleting the existing book.

    HTTP Method: DELETE
    URL: http://localhost:9091/app/books/<BookId>
    Response : Book has been deleted, Book id:1


## Step 4:

CI/CD pipeline has been built through GitHub actions.This was achieved to demonstrate below feature :
If any of the test cases fail,then build gets failed.Though not an deliverable asked for this task,It was done additionaly to showcase CI/CD pipeline feature which is one of the best practices i usually follow:

1.  Github action workflow:

    <img src="./static/GitHub Actions.png"/>






## Tech Stack

**Stack:** Java, Spring boot, H2 DB, Docker, GitHub Actions




## 🚀 About Me
- Enthusiastic Learners...!!!


## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ruknuddinmohd/)

## Support

For support, email ruknuddinmohd@gmail.com or join our Slack channel.




