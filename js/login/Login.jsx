import { useRef } from "react"
import Entry from "../common/Entry.jsx"
import { logInUser } from "../api/authentification.js"
import { navigateTo } from "../api/utils.js"

const SIGN_UP_URL = "/signup"
const LOGIN_URL = "/login"
const HOME_URL = "/"

export default function Login() {
  return <Entry formType="login" form={<LoginForm />} navigateTo={navigateTo} signUpUrl={SIGN_UP_URL} loginUrl={LOGIN_URL} />
}

function LoginForm() {
  const emailRef = useRef(null)
  const passwordRef = useRef(null)
  
  const logIn = (e) => {
    e.preventDefault()

    const email = emailRef.current.value
    const password = passwordRef.current.value

    if (email === "" || password === "") {
      window.alert("Please fill in all data")
      return
    } 

    const loginData = {
      "email": email,
      "password": password
    }

    logInUser(LOGIN_URL, loginData)
    navigateTo(HOME_URL)
  }
    
  return (
    <form className="form">
      <div id="email-input" className="input-group">
        <label>Email: </label>
        <input name="email" id="email-input" placeholder="doctor.giggle.touch@gmail.com" ref={emailRef} />
      </div>

      <div id="password-input" className="input-group">
        <label>Password: </label>
        <input name="password" id="password-input" placeholder="" ref={passwordRef} />
      </div>

      <button onClick={logIn}>Log In</button>
    </form>
  )
}