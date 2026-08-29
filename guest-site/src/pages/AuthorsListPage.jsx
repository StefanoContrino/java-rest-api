import { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import { getBooks } from "../../api/library";

import '../css/index.css';


function AuthorListPage() {


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
                                    <th>Author Name</th>
                                </tr>
                            </thead>

                            <tbody>
                                {books.map(book => (
                                    <tr>
                                        <td>
                                            <NavLink className="library-link" to={`/author/${book.author.id}`}>{book.author.name}</NavLink>
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

export default AuthorListPage;