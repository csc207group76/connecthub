import axios from 'axios'

export default axios.create({
  baseURL: process.env.REACT_APP_DEV_API_URL || "https://connecthub-i5rz.onrender.com/api/v1",
  headers: {"ngrok-skip-browser-warning": true}
})