import { useEffect, useState, useRef } from "react"
import { navigateTo } from "../api/utils.js"
import { logOutUser } from "../api/authentification.js"
import api from "../api/axios.config.js"
import logo from "../assets/logo.png"

const GET_CURRENT_USER_URL = "/current-user"
const LOG_OUT_URL = "/logout"
const LOGIN_URL = "/login"

export default function NavBar(props) {
  const [username, setUsername] = useState("")
  const [handle, setHandle] = useState("")

  const searchBarRef = useRef(null)

  useEffect(() => {
    // fetch for current user
    api
      .get(GET_CURRENT_USER_URL)
      .then(response => {
        if (response.data === "") {
          setUsername("")
          setHandle("")
        } else {
          setUsername(response.data.username)
          setHandle(formatHandle(response.data.username))
        }
      })
      .catch(() => {
      })

    // binding key events for search bar
    const searchBar = searchBarRef.current

    if (searchBar) { searchBar.addEventListener("keydown", (e) => {
      if (e.key === "Enter") {
        e.preventDefault();
        window.alert("Search functionality not implemented yet")
        // search for posts
      }
    })}

    // Cleanup event listener on unmount
    return () => {
      if (searchBar) {
        searchBar.removeEventListener("keydown", handleKeyPress);
      }
    };
  }, [])

  const formatHandle = (username => {
    return `@${username.toLowerCase().replace(" ", "_")}`
  })

  const handleLogOut = () => {
    logOutUser(LOG_OUT_URL, {}, LOGIN_URL)
  }

  return (
    <div id="nav-bar-container">
      <div id="nav-bar-home-container" onClick={() => navigateTo("/")}>
        <img src={logo} alt="home logo" />
        <div>Connect Hub</div>
      </div>

      <div id="search-bar">
        <input type="text" id="search-content" placeholder="Search" ref={searchBarRef}></input>
      </div>

      <div id="nav-bar-user-container">
        <img src="" alt=" " />
        <div className="username">{username}</div>
        <div className="handle">{handle}</div>
        {username === "" ? null : <button id="log-out-btn" onClick={() => handleLogOut()}>Log out</button>}
      </div>
    </div>
  )
}