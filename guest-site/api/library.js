import axios from "axios";

const API_URL = "http://localhost:8080/api/library";

export function getBooks() {
    return axios.get(API_URL, {
        withCredentials: true // <--- AGGIUNGI QUESTO
    });
}

export function getBook(id) {
    return axios.get(`${API_URL}/${id}`, {
        withCredentials: true // <--- AGGIUNGI QUESTO
    });
}