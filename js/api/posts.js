import api from './axios.config.js'
import { navigateTo, REQ_HEADER } from './utils.js'

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