import api from './axios.config.js'
import { navigateTo, REQ_HEADER } from './utils.js'

export function signUpUser(url, payload, destination) {
  return api
    .post(url, payload, REQ_HEADER)
    .then(response => {
      navigateTo(destination)
    })
    .catch(error => {
      window.alert(`ERROR: ${error.response.data}`)
    })
}

export function logInUser(url, payload, destination) {
  return api
    .post(url, payload, REQ_HEADER)
    .then(response => {
      navigateTo(destination)
    })
    .catch(error => {
      if (error.response) {
        window.alert(`Error: ${error.response.data}`);
      } else if (error.request) {
        window.alert('No response received from the server.');
      } else {
        window.alert('Error setting up the request: ' + error.message);
      }
    })
}

export function logOutUser(url, payload, destination) {
  return api
    .post(url, payload, REQ_HEADER)
    .then(response => {
      window.alert("Logged out")
      navigateTo(destination)
    })
    .catch(error => {
    })
}
