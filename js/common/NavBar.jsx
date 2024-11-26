import { useEffect, useState, useRef } from "react"
import { navigateTo } from "../api/utils.js"
import logo from "../assets/logo.png"

export default function NavBar(props) {
  const [username, setUsername] = useState("")
  const [handle, setHandle] = useState("") // = `@${username.toLowerCase().replace(" ", "_")}`

  const searchBarRef = useRef(null)

  useEffect(() => {
    // fetch for current user

    // binding key events for search bar
    const searchBar = searchBarRef.current

    // Add event listener
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
        <div className="username">{"Need Use Case"}</div>
        <div className="handle">{"@need_use_case"}</div>
      </div>
    </div>
  )
}