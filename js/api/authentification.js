import api from './axios.config.js'

const REQ_HEADER = {
  Accept: "application/json",
  "Content-Type": "application/json;charset=UTF-8",
}

export function signUpUser(url, payload) {
  return api
    .post(url, payload, REQ_HEADER)
    .then(response => {
    })
    .catch(error => {
      window.alert(`ERROR: ${error.response.data}`)
    })
} 

export function logInUser(url, payload) {
  return api 
    .post(url, payload, REQ_HEADER)
    .then(response => {
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
