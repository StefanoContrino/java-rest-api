import { useState } from 'react';
import App from '../App.jsx';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errore, setErrore] = useState('');
    const [componenteDaMostrare, setComponenteDaMostrare] = useState(null);

    const handleLogin = async (e) => {
        e.preventDefault();
        setErrore('');

        const dati = new URLSearchParams();
        dati.append('username', username);
        dati.append('password', password);

        try {
            const risposta = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: dati,
                credentials: 'include', // FONDAMENTALE: accetta i cookie di sessione
            });

            if (risposta.ok) {
                const datiRicevuti = await risposta.json();

                if (datiRicevuti.role === 'ADMIN') {
                    // ADMIN: Vai al sito Java
                    // Ora che Java ha creato la sessione, questo reindiramento funzionerà senza chiedere login
                    window.location.href = 'http://localhost:8080/home';
                } else {
                    // GUEST: Mostra l'App React
                    setComponenteDaMostrare(<App />);
                }
            } else {
                setErrore("Username o password errati.");
            }
        } catch (err) {
            console.error(err);
            setErrore("Errore di connessione: " + err.message);
        }
    };

    if (componenteDaMostrare) {
        return componenteDaMostrare;
    }

    return (
        <div style={{ maxWidth: '400px', margin: '50px auto', padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>
            <h2>Accedi al Sito</h2>
            <form onSubmit={handleLogin}>
                <div style={{ marginBottom: '15px' }}>
                    <label>Username:</label><br />
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} style={{ width: '100%', padding: '8px', marginTop: '5px' }} required />
                </div>
                <div style={{ marginBottom: '15px' }}>
                    <label>Password:</label><br />
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} style={{ width: '100%', padding: '8px', marginTop: '5px' }} required />
                </div>
                {errore && <p style={{ color: 'red' }}>{errore}</p>}
                <button type="submit" style={{ width: '100%', padding: '10px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>Accedi</button>
            </form>
        </div>
    );
}

export default Login;