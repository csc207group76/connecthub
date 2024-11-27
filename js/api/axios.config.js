import axios from 'axios'

export default axios.create({
  baseURL: process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/v1",
  headers: {"ngrok-skip-browser-warning": true}
})