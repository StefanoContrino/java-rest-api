import { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import { getBooks } from "../../api/library";

import '../css/BookListPage.css';


function BookListPage() {


    const [books, setBooks] = useState([]);


    useEffect(() => {

        getBooks()
            .then(response => {
                console.log("RISPOSTA COMPLETA:", response);       // Vedi la struttura intera
                console.log("RESPONSE.DATA:", response.data);

                setBooks(response.data);

            });

    }, []);

    return (

        <>

            <link rel="preconnect" href="https://fonts.googleapis.com" />
            <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" />
            <title>Index</title>

            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-8">
                        <table className="table table-hover text-center my-5">

                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Books</th>
                                    <th>Genre</th>
                                    <th>Price</th>
                                </tr>
                            </thead>

                            <tbody>
                                {books.slice(0, 20).map(book => (
                                    <tr>
                                        <td>
                                            <a className="author-link" href="">{book.author.name}</a>
                                        </td>
                                        <td>
                                            <NavLink className="book-link" to={`/detail/${book.id}`}>{book.name}</NavLink>
                                        </td>
                                        <td>
                                            {book.genre}
                                        </td>
                                        <td>
                                            {book.price}€
                                        </td>
                                    </tr>
                                ))}
                            </tbody>

                        </table>
                    </div>
                </div>
            </div>

        </>

    );

}

export default BookListPage;