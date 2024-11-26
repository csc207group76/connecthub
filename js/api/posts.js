import api from './axios.config.js'
import { navigateTo } from './utils.js'

const REQ_HEADER = {
  Accept: "application/json",
  "Content-Type": "application/json;charset=UTF-8",
}

export function createPost(url, payload, destination) {
  return api
    .post(url, payload, REQ_HEADER)
    .then(response => {
      window.alert(response.data)
      navigateTo(destination)
    })
    .catch(error => {
      window.alert(`ERROR: ${error.response.data}`)
    })
}