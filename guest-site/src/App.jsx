import { Routes, Route } from 'react-router-dom';
import Navbar from "./components/Navbar";
import BookListPage from "./pages/BookListPage";
import BookDetailPage from './pages/BookDetailPage';
import AuthorDetailPage from './pages/AuthorDetailPage';


function App() {

    return (

        <>
            <Navbar />
            <Routes>
                <Route index element={<BookListPage />} />
                <Route path="/detail/:id" element={<BookDetailPage />} />
                <Route path="/author/:id" element={<AuthorDetailPage />} />
            </Routes>
        </>

    );

}


export default App;