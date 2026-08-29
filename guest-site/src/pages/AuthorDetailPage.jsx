import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { NavLink } from "react-router-dom";
import { getBooks } from "../../api/library";
import '../css/DetailPage.css';

function AuthorDetailPage() {

    const [book, setBook] = useState();
    const [books, setBooks] = useState([])
    const { id } = useParams();


    useEffect(() => {

        getBooks()
            .then(response => {
                const allBooks = response.data;
                setBooks(allBooks);

                // Modifica necessaria: trova un libro di questo autore per avere i dati della bio
                const authorBook = allBooks.find(b => String(b.author.id) === String(id));
                setBook(authorBook);

            });

    }, [id]);

    if (!book) {
        return "Loading...";
    }

    // Logica aggiunta: filtra i libri per questo autore
    const authorBooks = books.filter(b => String(b.author.id) === String(id));

    const uniqueAuthors = Array.from(
        new Map(books.map(book => [book.author.id, book.author])).values()
    );

    const currentIndex = uniqueAuthors.findIndex(author => String(author.id) === String(id));
    const prevAuthor = uniqueAuthors[currentIndex - 1];
    const nextAuthor = uniqueAuthors[currentIndex + 1];


    return <>

        <title>Author Detail Page</title>

        <div className="container col-lg-6">
            <div className="card text-center mt-4">
                <h1 className="card-text-color pt-4">
                    <strong>{book.author.name}</strong>
                </h1>
                <img src={book.author.imgUrl} className="card-img-top card-img-style py-3" alt="Author Image" />

                <div className="px-5">
                    <p className="pt-1 text-justify card-synopsis-font-size">
                        {book.author.biography}
                    </p>
                </div>

                <h2 className="card-text-color pt-3">
                    <strong>Available Books</strong>
                </h2>

                <div className="my-3">
                    {authorBooks.map(b => (
                        <div className="px-3 my-1">
                            <NavLink className="available-books btn w-100 text-center py-3 rounded-0" to={`/book/detail/${b.id}`}>{b.name}</NavLink>
                        </div>
                    ))}
                </div>

                <div className="d-flex justify-content-around pt-3 mb-3">
                    {prevAuthor &&
                        <NavLink className="card-btn btn" to={`/author/${prevAuthor.id}`}>Previous Author</NavLink>
                    }
                    {nextAuthor &&
                        <NavLink className="btn card-btn" to={`/author/${nextAuthor.id}`}>Next Author</NavLink>
                    }
                </div>

            </div>
        </div>

    </>
}

export default AuthorDetailPage;