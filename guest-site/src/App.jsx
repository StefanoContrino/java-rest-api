import { Routes, Route } from 'react-router-dom';
import Navbar from "./components/Navbar";
import HomePage from './pages/HomePage';
import Library from "./pages/Library";
import BookDetailPage from './pages/BookDetailPage';
import AuthorDetailPage from './pages/AuthorDetailPage';
import BookListPage from './pages/BooksListPage';
import AuthorsListPage from './pages/AuthorsListPage';


function App() {

    return (

        <>
            <Navbar />
            <Routes>
                <Route index element={<HomePage />} />
                <Route path="/library" element={<Library />} />
                <Route path="book/detail/:id" element={<BookDetailPage />} />
                <Route path="/author/:id" element={<AuthorDetailPage />} />
                <Route path="/books-list" element={<BookListPage />} />
                <Route path="/authors-list" element={<AuthorsListPage />} />
            </Routes>
        </>

    );

}


export default App;