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

                        <li className="nav-item active pe-3">
                            <a href="#" className="nav-link">Home</a>
                        </li>

                        <li className="nav-item active pe-3">
                            <a href="#" className="nav-link">Authos</a>
                        </li>

                        <li className="nav-item active pe-3">
                            <a href="#" className="nav-link">Books</a>
                        </li>

                    </ul>

                </div>

            </nav>
        </>
    )
}
export default Navbar;