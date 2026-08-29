import { NavLink } from "react-router-dom";

function Navbar() {

    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-light bg-light ps-3">
                <a className="navbar-brand" href="#">The Italian book library without theft</a>

                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">

                    <ul className="navbar-nav ms-auto">

                        <li className="nav-item pe-3">
                            <NavLink to={`/`} className="nav-link">Home</NavLink>
                        </li>

                        <li className="nav-item pe-3">
                            <NavLink to={`/library`} className="nav-link">Library</NavLink>
                        </li>

                        <li className="nav-item pe-3">
                            <NavLink to={`/authors-list`} className="nav-link">Authors</NavLink>
                        </li>

                        <li className="nav-item pe-3">
                            <NavLink to={`/books-list`} className="nav-link">Books</NavLink>
                        </li>

                    </ul>

                </div>

            </nav>
        </>
    )
}
export default Navbar;