<%@ page import="ajt.assign05.SearchBookServlet" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! SearchBookServlet searchBook = new SearchBookServlet(); %>
<html>
<head>
    <title>Search Book</title>
    <script>
        function searchBook() {
            var bookId = document.getElementById('bookId').value;
            fetch('search-book?bookId=' + bookId)
                .then(response => response.json())
                .then(data => {
                    var resultDiv = document.getElementById('result');
                    resultDiv.innerHTML = '';
                    var bookTable = document.createElement('table');
                    var headerRow = document.createElement('tr');

                    ['Book ID', 'Book Name', 'Author Name', 'Publication', 'Date of Publication', 'Book Price'].forEach(headerText => {
                        var headerCell = document.createElement('th');
                        headerCell.textContent = headerText;
                        headerRow.appendChild(headerCell);
                    });
                    bookTable.appendChild(headerRow);

                    data.forEach(book => {
                        var row = document.createElement('tr');

                        ['bookID', 'bookName', 'authorName', 'publication', 'dateofPublication', 'priceofBook'].forEach(property => {
                            var cell = document.createElement('td');
                            cell.textContent = book[property];
                            row.appendChild(cell);
                        });
                        bookTable.appendChild(row);
                    });
                    resultDiv.appendChild(bookTable);
                });
        }
    </script>
    <style>
        #container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            horiz-align: center;
        }

        #result {
            display: flex;
            justify-content: center;
            horiz-align: center;
            margin-top: 5%;
        }

        #searchBookForm {
            display: flex;
            justify-content: center;
            horiz-align: center;
            margin-top: 5%;
        }

        #btn {
            margin-left: 5%;
        }

        table, tr, td, th {
            border: 1px solid black;
            text-align: center;
        }

        td, th {
            padding: 5px;
        }
    </style>
</head>
<body>
<div id="container">
    <form id="searchBookForm" onsubmit="event.preventDefault();searchBook()">
        <label>
            Enter BookId: <input id="bookId" type="text">
        </label>
        <label>
            <input id="btn" type="submit" value="Search" onsubmit="searchBook();">
        </label>
    </form>
    <br/>
    <div id="result"></div>
</div>
</body>
</html>
