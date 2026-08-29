import { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import { getBooks } from "../../api/library";

import '../css/index.css';


function Library() {


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

            <title>Book List Page</title>

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
                                            <NavLink className="library-link" to={`/author/${book.author.id}`}>{book.author.name}</NavLink>
                                        </td>
                                        <td>
                                            <NavLink className="library-link" to={`/book/detail/${book.id}`}>{book.name}</NavLink>
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

export default Library;