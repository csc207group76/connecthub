import axios from 'axios'

export default axios.create({
  // TODO Since backend & frontend are hosted on the same domain and not much production differentiation,
  // this is fine. But it really should be done using env variables that differentiates development/producion/staging
  baseURL: window.location.protocol + '//' + window.location.hostname  + '/api/v1',
  headers: {"ngrok-skip-browser-warning": true}
})