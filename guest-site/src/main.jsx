import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom';
import Login from './components/Login.jsx';
import './css/index.css'
import App from './App.jsx'
import '@fontsource-variable/lexend/wght.css';

createRoot(document.getElementById('root')).render(
  <>


    <StrictMode>
      <BrowserRouter>
        <Login />
      </BrowserRouter>
    </StrictMode>,
  </>

)
