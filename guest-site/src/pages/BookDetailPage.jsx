import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { NavLink } from "react-router-dom";
import { getBooks } from "../../api/library";
import '../css/DetailPage.css';

function BookDetailPage() {

    const [book, setBook] = useState();
    const [books, setBooks] = useState([])
    const { id } = useParams();


    useEffect(() => {

        getBooks()
            .then(response => {
                setBooks(response.data)
                const bookFound = response.data.find(book => String(book.id) === String(id))
                setBook(bookFound);

            });

    }, [id]);

    if (!book) {
        return "Loading...";
    }

    const index = books.findIndex(book => String(book.id) === String(id));
    const prevBook = books[index - 1];
    const nextBook = books[index + 1]

    return <>

        <title>Book Detail Page</title>

        <div className="container col-lg-6">
            <div className="card text-center mt-4">
                <h1 className="card-text-color pt-4">
                    {book.name} by {book.author.name}
                </h1>
                <div className="card-body px5">
                    <p className="pt-1 text-justify card-synopsis-font-size">{book.synopsis}</p>
                </div>

                <div className="row py-5 px-3">
                    <div className="col-8 text-start card-detail-font-size">
                        <p className="card-text mb-0">Genre: {book.genre}</p>
                        <p className="card-text mb-0">Language: {book.language}</p>
                        <p className="card-text mb-0">ISBN code: {book.ISBN}</p>
                        <p className="card-text mb-0">Published the: {book.publicationDate} by {book.publishingHouse}</p>
                    </div>
                    <div className="col-4 text-end">
                        <p className="card-text">Price: {book.price}€</p>
                        <a href="#" className="card-btn-color btn">Rent the book</a>
                    </div>
                </div>

                <div className="d-flex justify-content-around py-3">
                    {prevBook &&
                        <NavLink className="btn card-btn" to={`/detail/${prevBook.id}`}>
                            Previous Book
                        </NavLink>
                    }
                    {nextBook &&
                        <NavLink className="btn card-btn" to={`/detail/${nextBook.id}`}>
                            Next Book
                        </NavLink>
                    }
                </div>


            </div>
        </div>

    </>
}

export default BookDetailPage;