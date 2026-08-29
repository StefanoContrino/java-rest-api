import { NavLink } from 'react-router-dom';
import '../css/index.css';

function HomePage() {
    return <>
        <div className="container col-lg-6">
            <div className="card text-center mt-4">
                <h1 className="card-text-color pt-4">
                    <strong>Welcome to our library</strong>
                </h1>

                <div className="card-body px-5">
                    <p className="pt-1 text-justify card-synopsis-font-size">We have many of the most popular and enjoyable
                        books from the world's best authors. For any inquiries, you can contact us through the channels
                        found in the "Contacts" section.</p>

                    <div className="d-flex justify-content-around">
                        <NavLink className="btn navlink-btn" to={`/books-list`}>Books</NavLink>
                        <NavLink className="btn navlink-btn" to={`/library`}>Library</NavLink>
                        <NavLink className="btn navlink-btn" to={`/authors-list`}>Authors</NavLink>
                    </div>
                </div>
            </div>
        </div>

    </>
}

export default HomePage;